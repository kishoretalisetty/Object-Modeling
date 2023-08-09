package com.crio.jukebox.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import com.crio.jukebox.entites.Song;
import com.crio.jukebox.exceptions.NoSuchCommandException;
import com.crio.jukebox.repositories.IsongRepository;

public class SongService implements ISongService {

    private final IsongRepository songRepository;

    public SongService(IsongRepository songRepository) {
        this.songRepository = songRepository;
    }


    public void LoadDataInSongs(String  file){
        String line = "";  
        String splitBy = ",";
        try {
            BufferedReader br=new BufferedReader(new FileReader(file));
            line = br.readLine();
            while ((line = br.readLine()) != null)   //returns a Boolean value  
            {  
            String[] songdata = line.split(splitBy);
            if(songdata.length==6){
           Song song=new Song(songdata[0],songdata[1],songdata[2],songdata[3],songdata[4],songdata[5]);
           songRepository.save(song);
            }
           else if(songdata.length==5){
            Song song=new Song(songdata[0],songdata[1],songdata[2],songdata[3],songdata[4]);
           songRepository.save(song);
           }
           
           }

        } catch (IOException | NoSuchCommandException e) {
            e.printStackTrace();
        }
    }
    
}
