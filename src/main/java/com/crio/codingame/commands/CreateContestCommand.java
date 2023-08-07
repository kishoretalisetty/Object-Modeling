package com.crio.codingame.commands;

import java.util.List;

import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.Level;
import com.crio.codingame.services.IContestService;

public class CreateContestCommand implements ICommand{

    private final IContestService contestService;

    public CreateContestCommand(IContestService contestService) {
        this.contestService = contestService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Exontestecute create method of ICService and print the result.
    // Also Handle Exceptions and print the error messsages if any.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["CREATE_CONTEST","CRIODO2_CONTEST","LOW" ," contestCreator=Monica","40"]
    // or
    // ["CREATE_CONTEST","CRIODO1_CONTEST","HIGH","Ross"]
    // Hint - Use Parameterized Exceptions in the Service class to match with the Unit Tests Output.

    @Override
    public void execute(List<String> tokens) {
        Integer numQuestion=null;
        if(tokens.size()==5) numQuestion=Integer.parseInt(tokens.get(4));
        //"0-CREATE-CONTEST","1-name","2-LOW","3-creator","4-2
        String contestName=tokens.get(1);
        String contestCreator=tokens.get(3);
        Level level=null;
        String temlevel=tokens.get(2);
        if(temlevel.equals("LOW")){
            level=Level.LOW;
        }else if(temlevel.equals("HIGH")){
            level=Level.HIGH;
        }else if(temlevel.equals("MEDIUM")){
            level=Level.MEDIUM;
        }

         try{
        //    System.out.println(tokens.get(1));
        //    System.out.println(level);
        //    System.out.println(tokens.get(3));
        //    System.out.println(numQuestion);
        //    System.out.println(tokens);
     Contest contest=contestService.create(contestName, level, contestCreator, numQuestion);
     System.out.println(contest);
         }
         catch(RuntimeException e){
            System.out.println(e.getMessage());
         }
    }
    
}
