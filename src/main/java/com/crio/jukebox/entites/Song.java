package com.crio.jukebox.entites;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Song extends BaseEntity {

    private final String SONG_NAME;
    private final String GENRE;
    private final String ALBUM_NAME;
    private final String OWNER;
    private final String st;
    private final List<String> ALBUM_ARTIST;
    private SongStatus songStatus;
  
   
    public Song(Song song){
        this(song.id, song.SONG_NAME, song.GENRE, song.ALBUM_NAME,song.OWNER, song.st);
    }

    public Song(String id,String SONG_NAME, String GENRE, String ALBUM_NAME,String OWNER, String st) {
        this(SONG_NAME,GENRE,ALBUM_NAME,OWNER,st);
        this.id = id;
    }

    public Song(String SONG_NAME, String GENRE, String ALBUM_NAME,String OWNER ,String st) {
      this.SONG_NAME=SONG_NAME;
      this.GENRE=GENRE;
      this.ALBUM_NAME=ALBUM_NAME;
      this.OWNER=OWNER;
      this.st=st;
      String arr[]=st.split("#");
      this.ALBUM_ARTIST=new ArrayList<>(Arrays.asList(arr));
      this.songStatus=SongStatus.PAUSE;
    }

    // public Song(String SONG_NAME, String GENRE, String ALBUM_NAME,String OWNER,String st ) {
    //     this.SONG_NAME=SONG_NAME;
    //     this.GENRE=GENRE;
    //     this.ALBUM_NAME=ALBUM_NAME;
    //     this.OWNER=OWNER;
    //     this.ALBUM_ARTIST=new ArrayList<>();
    //   }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    public SongStatus getSongStatus() {
        return songStatus;
    }

    public void setSongStatus(SongStatus songStatus) {
        this.songStatus = songStatus;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Song other = (Song) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public String getSONG_NAME() {
        return SONG_NAME;
    }

    public String getGENRE() {
        return GENRE;
    }

    public String getALBUM_NAME() {
        return ALBUM_NAME;
    }

    public List<String> getALBUM_ARTIST() {
        return ALBUM_ARTIST;
    }

    public String getOWNER() {
        return OWNER;
    }

    public String getSt() {
        return st;
    }

    @Override
    public String toString() {
        return "Song [ALBUM_ARTIST=" + ALBUM_ARTIST + ", ALBUM_NAME=" + ALBUM_NAME + ", GENRE="
                + GENRE + ", OWNER=" + OWNER + ", SONG_NAME=" + SONG_NAME + "]";
    }
    

    
}
