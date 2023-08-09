package com.crio.jukebox.commands;

import java.io.File;
import java.util.List;
import com.crio.jukebox.services.ISongService;

public class LoadDataCommand implements ICommand {

    private final ISongService songService;
    
    public LoadDataCommand( ISongService songService) {
        this.songService = songService;
    }

    @Override
    public void execute(List<String> tokens) {
       
        try{
        songService.LoadDataInSongs(tokens.get(1));
        System.out.println("Songs Loaded successfully");
        }
        catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
        
    }

    
}
