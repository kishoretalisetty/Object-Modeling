package com.crio.codingame.commands;

import java.util.List;
import com.crio.codingame.entities.Level;
import com.crio.codingame.services.IContestService;

public class ListContestCommand implements ICommand{

    private final IContestService contestService;
    
    public ListContestCommand(IContestService contestService) {
        this.contestService = contestService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute getAllContestLevelWise method of IContestService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["LIST_CONTEST","HIGH"]
    // or
    // ["LIST_CONTEST"]

    @Override
    public void execute(List<String> tokens) {
        Level level=null;
        String temlevel=null;
        if(tokens.size()==2){
         temlevel=tokens.get(1);

        if(temlevel.equals("LOW")){
            level=Level.LOW;
        }else if(temlevel.equals("HIGH")){
            level=Level.HIGH;
        }else if(temlevel.equals("MEDIUM")){
            level=Level.MEDIUM;
        }
       // System.out.println(contestService.getAllContestLevelWise(level));
        }
        System.out.println(contestService.getAllContestLevelWise(level));
    
       
        
      
    }
    
}
