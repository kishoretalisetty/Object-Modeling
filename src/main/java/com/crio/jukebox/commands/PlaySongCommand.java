package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entites.Song;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.services.IPlaylistService;

public class PlaySongCommand implements ICommand{
    private final IPlaylistService playlistService;

    public PlaySongCommand(IPlaylistService playlistService){
        this.playlistService=playlistService;
    }

    /*
     * Switch to play a previous song in the active playlist.
       PLAY-SONG { USER_ID }  BACK

       Switch to play the next song in the active playlist.
       PLAY-SONG  { USER_ID } NEXT

       Switch to the preferred song in the playlist.
       PLAY-SONG { USER_ID }  { Song ID }

       Output Format Schema:
        Switch to play a previous song in the active playlist.
        Current Song Playing
       Song - Save Your Tears(Remix)
       Album -  After Hours 
      Artists -  TheWeeknd,Ariana Grande

      
     */

     public void execute(List<String> tokens){
        try{
           Song song= playlistService.ChangeSongInPlaylist(tokens.get(1),tokens.get(2));
           System.out.println("Current Song Playing");
           System.out.println("Song - "+song.getSONG_NAME());
           System.out.println("Album - "+song.getALBUM_NAME());
           System.out.print("Artists - ");
           List<String> list=song.getALBUM_ARTIST();
           System.out.print(list.get(0));
           for(int i=1; i<list.size(); i++){
               System.out.print(","+list.get(i));
           }
           System.out.println();
           }catch(RuntimeException e){
               System.out.println(e.getMessage());
           }
     }
    
}
