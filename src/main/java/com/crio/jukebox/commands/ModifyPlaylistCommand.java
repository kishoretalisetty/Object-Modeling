package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entites.Playlist;
import com.crio.jukebox.entites.Song;
import com.crio.jukebox.services.IPlaylistService;

public class ModifyPlaylistCommand  implements ICommand{
    private final IPlaylistService playlistService;
    public ModifyPlaylistCommand( IPlaylistService playlistService){
        this.playlistService=playlistService;
    }

  //  MODIFY-PLAYLIST ADD-SONG { USER_ID } {Playlist-ID } { List of Song IDs }
  //  MODIFY-PLAYLIST DELETE-SONG { USER_ID } {Playlist-ID } { List of Song IDs }
  /*
   * "Playlist ID - 1\n"+
        "Playlist Name - MY_PLAYLIST_1\n"+
        "Song IDs - 1 4 5 6 7\n"+
        
   */

  public void execute(List<String> tokens){
    String action=tokens.get(1);
    String userId=tokens.get(2);
    String playlistId=tokens.get(3);
    List<String> listOfSongsId=new ArrayList<>();
    for(int i=4; i<tokens.size(); i++){
        listOfSongsId.add(tokens.get(i));
    }

    try{
     Playlist playlist= playlistService.ModifySongsInPlaylist(action, userId, playlistId, listOfSongsId);
     System.out.println("Playlist ID - "+playlist.getId());
     System.out.println("Playlist Name - "+playlist.getPLAYLIST_NAME());
     System.out.print("Song IDs -");
     List<Song> sList=playlist.getPLAYLIST_SONGS();
     for(int i=0; i<sList.size(); i++)
     System.out.print(" "+sList.get(i).getId());
     System.out.println();
    }
    catch(RuntimeException e){
     System.out.println(e.getMessage());
    }
  }
    
}
