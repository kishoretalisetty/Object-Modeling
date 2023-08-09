package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entites.Song;
import com.crio.jukebox.services.IPlaylistService;

public class PlayPlaylistCommand implements ICommand {

  private final IPlaylistService playlistService;
    public PlayPlaylistCommand( IPlaylistService playlistService){
        this.playlistService=playlistService;
    }

    //PLAY-PLAYLIST  { USER_ID } { Playlist-ID }
    /*
     * Current Song Playing
    Song - Save Your Tears(Remix)
Album -  After Hours 
Artists -  TheWeeknd,Ariana Grande

     */
    public void execute(List<String> tokens){
        try{
        Song song =playlistService.PlayingSongsInPlaylist(tokens.get(1),tokens.get(2));
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
