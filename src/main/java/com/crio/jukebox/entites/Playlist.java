package com.crio.jukebox.entites;

import java.util.List;

public class Playlist extends BaseEntity {

     private final String PLAYLIST_NAME;
     private final List<Song> PLAYLIST_SONGS;
     private PlaylistStatus playlistStatus;

     public Playlist(Playlist playlist){
        this(playlist.PLAYLIST_NAME, playlist.PLAYLIST_SONGS);
        
     }

     public Playlist(String id,String PLAYLIST_NAME, List<Song> PLAYLIST_SONGS) {
                this(PLAYLIST_NAME, PLAYLIST_SONGS);
              this.id=id;
            }

    public Playlist(String PLAYLIST_NAME, List<Song> PLAYLIST_SONGS) {
        this.PLAYLIST_NAME = PLAYLIST_NAME;
        this.PLAYLIST_SONGS = PLAYLIST_SONGS;
        this.playlistStatus = PlaylistStatus.NOT_STARTED;
    }

    public void setPlaylistStatus(PlaylistStatus playlistStatus) {
        this.playlistStatus = playlistStatus;
    }

    public String getPLAYLIST_NAME() {
        return PLAYLIST_NAME;
    }

    public List<Song> getPLAYLIST_SONGS() {
        return PLAYLIST_SONGS;
    }

    public PlaylistStatus getPlaylistStatus() {
        return playlistStatus;
    }

    public boolean isSongExist(Song song){
    return PLAYLIST_SONGS.contains(song);
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
        Playlist other = (Playlist) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Playlist [PLAYLIST_NAME=" + PLAYLIST_NAME + ", PLAYLIST_SONGS=" + PLAYLIST_SONGS
                + ", playlistStatus=" + playlistStatus + "]";
    }

   
    
}
