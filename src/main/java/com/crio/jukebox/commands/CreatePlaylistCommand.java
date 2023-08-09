package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entites.Playlist;
import com.crio.jukebox.services.IPlaylistService;

public class CreatePlaylistCommand implements ICommand{
    
    private final IPlaylistService playlistService;
   public  CreatePlaylistCommand( IPlaylistService playlistService){
    this.playlistService=playlistService;
   }

   // "CREATE-PLAYLIST" "1"  "MY_PLAYLIST_1"  "1" "2" "3" "4" "5"

   public void execute(List<String> tokens){
      String  userId=tokens.get(1);
      String  playlistName=tokens.get(2);
      List<String> listOfSongs=new ArrayList<>();
      for(int i=3; i<tokens.size(); i++){
        listOfSongs.add(tokens.get(i));
      }
      try{
        Playlist playlist=playlistService.CreatePlaylist(userId, playlistName, listOfSongs);
        System.out.println("Playlist ID - "+playlist.getId());
      }catch(RuntimeException e){
        System.out.println(e.getMessage());
      }

   }

}
