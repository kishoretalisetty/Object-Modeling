package com.crio.jukebox.entites;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class User extends BaseEntity{

    private final  String userName;
   private Map<String ,Playlist> mapOfPlaylists;

   public User(User user){
    this(user.id, user.userName);
    this.mapOfPlaylists=user.mapOfPlaylists;
   }
   

   public User(String id,String userName,Map<String,Playlist> mapOfPlaylists) {
    this(id,userName);
   this.mapOfPlaylists=mapOfPlaylists;
      
  }

    public User(String id,String userName) {
      this(userName);
        this.id=id;
    }

    public User(String userName) {
        this.userName = userName;
        this.mapOfPlaylists=new HashMap<>();
    }

    public String getUserName() {
        return userName;
    }

    public int getHowManyPlayLists(){
        return mapOfPlaylists.size();
    }

    // public List<Playlist> getListOfPlaylists() {
    //     return listOfPlaylists;
    // }

    public void addPlaylist(String playlistId,Playlist playlist){
        mapOfPlaylists.put(playlistId,playlist);
    }

    public void deletePlaylist(String playlistId){
        mapOfPlaylists.remove(playlistId);
    }

    public boolean checkIfPlayListExists(String playlistId){
        return mapOfPlaylists.containsKey(playlistId);
    }

    public Map<String, Playlist> getMapOfPlaylists() {
        return mapOfPlaylists;
    }

    public List<Playlist> getListOfPlaylists(){
        return mapOfPlaylists.entrySet().stream().map(v->v.getValue()).collect(Collectors.toList());
    }

    public Playlist getPlaylistById(String playlistId){
        return mapOfPlaylists.get(playlistId);
    }
   

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "User [mapOfPlaylists=" + mapOfPlaylists + ", userName=" + userName + "]";
    }


    
}
