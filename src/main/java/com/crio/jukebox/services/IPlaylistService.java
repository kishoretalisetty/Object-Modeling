package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entites.Playlist;
import com.crio.jukebox.entites.Song;

public interface IPlaylistService {
    Playlist CreatePlaylist(String userId, String playlistName, List<String> listOfSongs);
    void  DeletePlaylist(String userId, String playlistId);
    Playlist ModifySongsInPlaylist(String action, String userId, String playlistId, List<String> listOfSongsId);
    Playlist DeleteSongsInPlaylist( String userId, String playlistId, List<String> listOfSongsId);
    Playlist AddSongsInPlaylist(String userId, String playlistId, List<String> listOfSongsId);
    Song PlayingSongsInPlaylist(String userId,String playlistId);
    Song ChangeSongInPlaylist(String userId, String actionOrSongId);
    Song PlayNextSong(String userId);
    Song PlayPrevSong(String userid);
    Song PlaySongById_InPlaylist(String userId,String songId);
}
