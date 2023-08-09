package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entites.Playlist;

public class PlaylistRepository implements IPlaylistRepository {
    
    private final Map<String,Playlist> playlistMap;
    private Integer autoIncrement = 0;

    
    
    public PlaylistRepository() {
        playlistMap = new HashMap<String,Playlist>();
    }

    public PlaylistRepository(Map<String, Playlist> playlistMap) {
        this.playlistMap = playlistMap;
        this.autoIncrement = playlistMap.size();
    }
// Playlist(String id,String pLAYLIST_NAME, List<Song> pLAYLIST_SONGS, PlaylistStatus playlistStatus,
     
    @Override
    public Playlist save(Playlist entity) {
        if( entity.getId() == null ){
            autoIncrement++;
           Playlist c = new Playlist(Integer.toString(autoIncrement),entity.getPLAYLIST_NAME(),
           entity.getPLAYLIST_SONGS());

           playlistMap.put(c.getId(),c);
             return c;
        }
        playlistMap.put(entity.getId(),entity);
         return entity;
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find all the list of Contest Present in the Repository
    // Tip:- Use Java Streams

    @Override
    public List<Playlist> findAllPlaylists() {
     List<Playlist> qList=playlistMap.entrySet().stream().map(v->v.getValue()).collect(Collectors.toList());
     return qList;
    }

    @Override
    public Optional<Playlist> searchById(String id) {
        return Optional.ofNullable(playlistMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void delete(Playlist entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        playlistMap.remove(id);
        
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Playlist> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find all the list of Contest Present in the Repository provided Level
    // Tip:- Use Java Streams

    // @Override
    // public List<Contest> findAllContestLevelWise(Level level) {
    //     List<Contest> qList=findAll();
    //     if(level==null){
    //         return qList;
    //     }
    //     List<Contest> contestLevelWise=qList.stream().filter(l->l.getLevel().
    //          equals(level)).collect(Collectors.toList());
    //  return contestLevelWise;
    // }
}
