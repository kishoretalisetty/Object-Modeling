package com.crio.jukebox.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entites.Playlist;
import com.crio.jukebox.entites.PlaylistStatus;
import com.crio.jukebox.entites.Song;
import com.crio.jukebox.entites.SongStatus;
import com.crio.jukebox.entites.User;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.IsongRepository;

public class PlaylistService implements IPlaylistService{

    IUserRepository userRepository;
    IsongRepository songRepository;
    IPlaylistRepository playlistRepository;

    public PlaylistService(IUserRepository userRepository, IsongRepository songRepository, IPlaylistRepository playlistRepository){
       this.userRepository=userRepository;
     this.songRepository=songRepository;
      this.playlistRepository=playlistRepository;  
    }

    public Playlist CreatePlaylist(String userId, String playlistName, List<String> listOfSongs){
     
          User user=userRepository.searchById(userId).orElseThrow(()-> new UserNotFoundException());
         List<Song> sList=new ArrayList<>();
         for(int i=0; i<listOfSongs.size(); i++){
            Song song=songRepository.searchById(listOfSongs.get(i)).orElseThrow(
            ()->new SongNotFoundException("Some Requested Songs Not Available. Please try again."));

            sList.add(song);
         }

         Playlist playlist=new Playlist(playlistName,sList);
         Playlist entity=playlistRepository.save(playlist);
         user.addPlaylist(entity.getId(),entity);
         return entity;
    }
    
    public void  DeletePlaylist(String userId, String playlistId){
        User user=userRepository.searchById(userId)
        .orElseThrow(()-> new UserNotFoundException());
        Playlist playlist=playlistRepository.searchById(playlistId)
        .orElseThrow(()->new PlaylistNotFoundException("Playlist Not Found"));
        user.deletePlaylist(playlist.getId());
        playlistRepository.deleteById(playlist.getId());
    
    }

    public Playlist ModifySongsInPlaylist(String action, String userId, String playlistId, List<String> listOfSongsId){
        
        if(action.equals("ADD-SONG")){
            return AddSongsInPlaylist(userId, playlistId, listOfSongsId);
        }else{
            return DeleteSongsInPlaylist(userId, playlistId, listOfSongsId);
        }
    }

    //// Add songs in playList
   public  Playlist AddSongsInPlaylist( String userId, String playlistId, List<String> listOfSongsId){

    User user=userRepository.searchById(userId)
    .orElseThrow(()-> new UserNotFoundException());

    Playlist playlist=playlistRepository.searchById(playlistId)
    .orElseThrow(()->new PlaylistNotFoundException("Playlist Not Found"));

    List<Song> sList=new ArrayList<>();
         for(int i=0; i<listOfSongsId.size(); i++){
            Song song=songRepository.searchById(listOfSongsId.get(i)).orElseThrow(
            ()->new SongNotFoundException("Some Requested Songs Not Available. Please try again."));

            sList.add(song);
         }

         Playlist Userplaylist=user.getPlaylistById(playlist.getId());
         List<Song>  playlistSongsList=Userplaylist.getPLAYLIST_SONGS();

         for(int s=0; s<sList.size(); s++){
            Song song=sList.get(s);
            if(!playlistSongsList.contains(song)){
               playlistSongsList.add(song);
            }
         }

         Playlist entity=playlistRepository.save(Userplaylist);
         return entity;
    }

     //// Delete songs in playList
   public  Playlist DeleteSongsInPlaylist( String userId, String playlistId, List<String> listOfSongsId){

    User user=userRepository.searchById(userId)
    .orElseThrow(()-> new UserNotFoundException());

    Playlist playlist=playlistRepository.searchById(playlistId)
    .orElseThrow(()->new PlaylistNotFoundException("Playlist Not Found"));

    List<Song> sList=new ArrayList<>();
         for(int i=0; i<listOfSongsId.size(); i++){
            Song song=songRepository.searchById(listOfSongsId.get(i)).orElseThrow(
            ()->new SongNotFoundException("Some Requested Songs Not Available. Please try again."));

            sList.add(song);
         }

         Playlist Userplaylist=user.getPlaylistById(playlist.getId());
         List<Song>  userPlaylistSongsList=Userplaylist.getPLAYLIST_SONGS();
         for(int i=0; i<sList.size(); i++){
            Song song=sList.get(i);
            if(userPlaylistSongsList.contains(song)){
            userPlaylistSongsList.remove(song);
            }
            else{
                throw new SongNotFoundException("Some Requested Songs for Deletion are not present in the playlist. Please try again");
            }

         }

         Playlist entity=playlistRepository.save(Userplaylist);
         return entity;

    }
    
    // Playing songs in PlayList

    public Song PlayingSongsInPlaylist(String userId,String playlistId){

      User user=userRepository.searchById(userId)
    .orElseThrow(()-> new UserNotFoundException());

    Playlist playlist=playlistRepository.searchById(playlistId)
    .orElseThrow(()->new PlaylistNotFoundException("Playlist Not Found"));

    List<Playlist> playlistsList=user.getListOfPlaylists();
    
    for(int i=0; i<playlistsList.size(); i++){
      Playlist p=playlistsList.get(i);
      if(p.getPlaylistStatus().equals(PlaylistStatus.IN_PROGRESS)){
         Playlist tem=user.getPlaylistById(p.getId());
         tem.setPlaylistStatus(PlaylistStatus.ENDED);
         playlistRepository.save(tem);
      }
    }

    Playlist userPlaylist=user.getPlaylistById(playlistId);
    userPlaylist.setPlaylistStatus(PlaylistStatus.IN_PROGRESS);
    List<Song> sList=userPlaylist.getPLAYLIST_SONGS();
    if(sList.isEmpty()){
      throw new PlaylistNotFoundException("Playlist is empty");
    }
    playlistRepository.save(userPlaylist);
    Song song=sList.get(0);
    song.setSongStatus(SongStatus.PLAYING);
    return sList.get(0);

    }

    //Change Songs in PlayList

    public Song ChangeSongInPlaylist(String userId, String actionOrSongId){
      if(actionOrSongId.equals("NEXT")){
         return PlayNextSong(userId);
      }
      else if(actionOrSongId.equals("BACK")){
         return PlayPrevSong(userId);
      }
      else{
         return PlaySongById_InPlaylist(userId,actionOrSongId);
      }
    }

    // Next song
    public Song PlayNextSong(String userId){

      User user=userRepository.searchById(userId)
      .orElseThrow(()-> new UserNotFoundException());

      List<Playlist> playlistsList=user.getListOfPlaylists();
    
      Playlist playlist=null;
    for(int i=0; i<playlistsList.size(); i++){
      Playlist p=playlistsList.get(i);
      if(p.getPlaylistStatus().equals(PlaylistStatus.IN_PROGRESS)){
         playlist=user.getPlaylistById(p.getId());
      }
    }
    if(playlist==null){
      throw new PlaylistNotFoundException();
    }
    List<Song> sList=playlist.getPLAYLIST_SONGS();
    int temInd=-1;
    for(int i=0; i<sList.size(); i++){
      if(sList.get(i).getSongStatus().equals(SongStatus.PLAYING)){
         temInd=i;
      }
    }
    sList.get(temInd).setSongStatus(SongStatus.PAUSE);
    temInd++;
    if(temInd==sList.size()){
      temInd=0;
    }
    sList.get(temInd).setSongStatus(SongStatus.PLAYING);
    return sList.get(temInd);
    }

   // Prev song
    public Song PlayPrevSong(String userId){
      User user=userRepository.searchById(userId)
      .orElseThrow(()-> new UserNotFoundException());

      List<Playlist> playlistsList=user.getListOfPlaylists();
    
      Playlist playlist=null;
    for(int i=0; i<playlistsList.size(); i++){
      Playlist p=playlistsList.get(i);
      if(p.getPlaylistStatus().equals(PlaylistStatus.IN_PROGRESS)){
         playlist=user.getPlaylistById(p.getId());
      }
    }
    if(playlist==null){
      throw new PlaylistNotFoundException();
    }
    List<Song> sList=playlist.getPLAYLIST_SONGS();
    int temInd=-1;
    for(int i=0; i<sList.size(); i++){
      if(sList.get(i).getSongStatus().equals(SongStatus.PLAYING)){
         temInd=i;
         break;
      }
    }
    sList.get(temInd).setSongStatus(SongStatus.PAUSE);
    temInd--;
    if(temInd<0){
      temInd=sList.size()-1;
    }
    sList.get(temInd).setSongStatus(SongStatus.PLAYING);
    return sList.get(temInd);

    }

    // change the song by song Id in the playlist

    public Song PlaySongById_InPlaylist(String userId,String songId){
      User user=userRepository.searchById(userId)
      .orElseThrow(()-> new UserNotFoundException());

      List<Playlist> playlistsList=user.getListOfPlaylists();
    
      Playlist playlist=null;
    for(int i=0; i<playlistsList.size(); i++){
      Playlist p=playlistsList.get(i);
      if(p.getPlaylistStatus().equals(PlaylistStatus.IN_PROGRESS)){
         playlist=user.getPlaylistById(p.getId());
      }
    }
    if(playlist==null){
      throw new PlaylistNotFoundException();
    }
    List<Song> sList=playlist.getPLAYLIST_SONGS();
    int temInd=-1;
    for(int i=0; i<sList.size(); i++){
      if(sList.get(i).getSongStatus().equals(SongStatus.PLAYING)){
         temInd=i;
         break;
      }
    }
    sList.get(temInd).setSongStatus(SongStatus.PAUSE);

    for(int i=0; i<sList.size(); i++){
      if(sList.get(i).getId().equals(songId)){
         sList.get(i).setSongStatus(SongStatus.PLAYING);
         return sList.get(i);
      }
    }
    throw new PlaylistNotFoundException("Given song id is not a part of the active playlist");
    }

}
