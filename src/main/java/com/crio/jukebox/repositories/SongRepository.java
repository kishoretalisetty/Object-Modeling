package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entites.Song;

public class SongRepository implements IsongRepository {

    private final Map<String,Song> songMap;
    private Integer autoIncrement = 0;

    public SongRepository(){
        songMap = new HashMap<String,Song>();
    }

    public SongRepository(Map<String, Song> songMap) {
        this.songMap = songMap;
        this.autoIncrement = songMap.size();
    }

    // Song(String id,String SONG_NAME, String GENRE, String ALBUM_NAME,String OWNER, List<String> ALBUM_ARTIST) {
  
    @Override
    public Song save(Song entity) {
        if( entity.getId() == null ){
            autoIncrement++;
            Song q = new Song(Integer.toString(autoIncrement),entity.getSONG_NAME(),entity.getGENRE(),
                 entity.getALBUM_NAME(),entity.getOWNER(),entity.getSt());
            songMap.put(q.getId(),q);
            return q;
        }
        songMap.put(entity.getId(),entity);
        return entity;
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find all the list of Question Present in the Repository
    // Tip:- Use Java Streams

    @Override
    public List<Song> findAll() {
        List<Song> qList=songMap.entrySet().stream().map(v->v.getValue()).collect(Collectors.toList());
     return qList;
    }

    @Override
    public Optional<Song> searchById(String id) {
        return Optional.ofNullable(songMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void delete(Song entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Song> findAllSongsByOwner(String Owner){
        List<Song> qList=findAll();
        if(Owner==null)return qList;
        List<Song> qListWithLevelWise=qList.stream().filter(q->q.getOWNER().equals(Owner)).
                     collect(Collectors.toList());
     return qListWithLevelWise;
    }
    
}
 