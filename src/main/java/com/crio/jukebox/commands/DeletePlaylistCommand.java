package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.services.IPlaylistService;

public class DeletePlaylistCommand implements ICommand{
    private final IPlaylistService playlistService;

    public DeletePlaylistCommand(IPlaylistService playlistService){
        this.playlistService=playlistService;
    }

   // DELETE-PLAYLIST { USER_ID } { Playlist-ID }

   public void execute(List<String> tokens){
    try{
        playlistService.DeletePlaylist(tokens.get(1), tokens.get(2));
      System.out.println("Delete Successful");
    }catch(RuntimeException e){
        System.out.println(e.getMessage());
    }

   }
    
}
