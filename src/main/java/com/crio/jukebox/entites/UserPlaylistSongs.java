package com.crio.jukebox.entites;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserPlaylistSongs {
   private Map<Playlist,List<Song>> playlistSongsMap;

   public UserPlaylistSongs() {
    playlistSongsMap=new HashMap<Playlist,List<Song>>();
   }


   public UserPlaylistSongs(Map<Playlist, List<Song>> playlistSongsMap) {
    this.playlistSongsMap = playlistSongsMap;
   }


   public void addPlaylistSong(Playlist playlist, List<Song> sList){
      playlistSongsMap.put(playlist, sList);
   }
   
   public List<Song> getSongsByPlayList(Playlist playlist){
    return playlistSongsMap.get(playlist);
   }


@Override
public String toString() {
    return "UserPlaylistSongs [playlistSongsMap=" + playlistSongsMap + "]";
}
    
   
}
