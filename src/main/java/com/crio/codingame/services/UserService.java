package com.crio.codingame.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.crio.codingame.dtos.UserRegistrationDto;
import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.ContestStatus;
import com.crio.codingame.entities.RegisterationStatus;
import com.crio.codingame.entities.ScoreOrder;
import com.crio.codingame.entities.User;
import com.crio.codingame.exceptions.ContestNotFoundException;
import com.crio.codingame.exceptions.InvalidOperationException;
import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.codingame.repositories.IContestRepository;
import com.crio.codingame.repositories.IUserRepository;

public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IContestRepository contestRepository;

    public UserService(IUserRepository userRepository, IContestRepository contestRepository) {
        this.userRepository = userRepository;
        this.contestRepository = contestRepository;
    }
    // TODO: CRIO_TASK_MODULE_SERVICES
    // Create and store User into the repository.
    @Override
    public User create(String name) {
    User entity=new User(name, 0);
    User user=userRepository.save(entity);
    return user;
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Get All Users in Ascending Order w.r.t scores if ScoreOrder ASC.
    // Or
    // Get All Users in Descending Order w.r.t scores if ScoreOrder DESC.

    @Override
    public List<User> getAllUserScoreOrderWise(ScoreOrder scoreOrder){
      List<User> qList=userRepository.findAll();
      if(scoreOrder.equals(ScoreOrder.ASC)){
        Collections.sort(qList,(a,b)->a.getScore()-b.getScore());
      }else if(scoreOrder.equals(ScoreOrder.DESC)){
        Collections.sort(qList,(a,b)->b.getScore()-a.getScore());
      }
      return qList;
    }

    @Override
    public UserRegistrationDto attendContest(String contestId, String userName) throws ContestNotFoundException, UserNotFoundException, InvalidOperationException {
        Contest contest = contestRepository.findById(contestId).orElseThrow(() -> new ContestNotFoundException("Cannot Attend Contest. Contest for given id:"+contestId+" not found!"));
        User user = userRepository.findByName(userName).orElseThrow(() -> new UserNotFoundException("Cannot Attend Contest. User for given name:"+ userName+" not found!"));
        if(contest.getContestStatus().equals(ContestStatus.IN_PROGRESS)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is in progress!");
        }
        if(contest.getContestStatus().equals(ContestStatus.ENDED)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is ended!");
        }
        if(user.checkIfContestExists(contest)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is already registered!");
        }
        user.addContest(contest);
        userRepository.save(user);
        return new UserRegistrationDto(contest.getName(), user.getName(),RegisterationStatus.REGISTERED);
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Withdraw the user from the contest
    // Hint :- Refer Unit Testcases withdrawContest method

    // @Override
    // public UserRegistrationDto withdrawContest(String contestId, String userName)
    //  throws ContestNotFoundException, UserNotFoundException, InvalidOperationException {
    //     Optional<Contest> Contestoptional=contestRepository.findById(contestId);
    //     Contest contest=Contestoptional.get();
    //     if(contest==null){
    //         throw new ContestNotFoundException();
    //     }
    //     Optional<User> userOptional=userRepository.findByName(userName);
    //     User user=userOptional.get();
    //     if(user==null){
    //         throw new UserNotFoundException();
    //     }

    //  //return null;
    // }
    @Override
    public UserRegistrationDto withdrawContest(String contestId, String userName) throws ContestNotFoundException, UserNotFoundException, InvalidOperationException {
        
        // Contest contest= contestRepository.findById(contestId).orElseThrow(()->  new ContestNotFoundException());

        // User user=userRepository.findByName(userName).orElseThrow(()-> new UserNotFoundException());
        Optional<Contest> Contestoptional=contestRepository.findById(contestId);
        if(Contestoptional.isEmpty()) throw new ContestNotFoundException();
            Contest contest=Contestoptional.get();
            
            Optional<User> userOptional=userRepository.findByName(userName);
            if(userOptional.isEmpty()) throw new UserNotFoundException(); 
            User user=userOptional.get();
            

        if(!user.checkIfContestExists(contest) || contest.getContestStatus()!=ContestStatus.NOT_STARTED){
            throw new InvalidOperationException();
        }

        user.deleteContest(contest);
        userRepository.save(user);


        
        
     return new UserRegistrationDto(contest.getName(), userName, RegisterationStatus.NOT_REGISTERED);

     
    }

    
}
