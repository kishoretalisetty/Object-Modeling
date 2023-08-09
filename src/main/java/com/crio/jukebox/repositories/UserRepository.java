package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entites.Playlist;
import com.crio.jukebox.entites.User;


public class UserRepository implements IUserRepository{
    
     private final Map<String,User> userMap;
    private Integer autoIncrement = 0;

    public UserRepository(){
        userMap = new HashMap<String,User>();
    }

    public UserRepository(Map<String, User> userMap) {
        this.userMap = userMap;
        this.autoIncrement = userMap.size();
    }


    @Override
    public User save(User entity) {
        if( entity.getId() == null ){
            autoIncrement++;
          User u = new User(Integer.toString(autoIncrement),entity.getUserName(),entity.getMapOfPlaylists());
            userMap.put(u.getId(),u);
            return u;
        }
        userMap.put(entity.getId(),entity);
        return entity;
    }


    @Override
    public List<User> findAll() {
        List<User> uList=userMap.entrySet().stream().map(v->v.getValue()).collect(Collectors.toList());
     return uList;
    }

    @Override
    public Optional<User> searchById(String id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void delete(User entity) {
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

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find the User Present in the Repository provided name
    // Tip:- Use Java Streams

    @Override
    public Optional<User> findByName(String name) {
        List<User> uList=findAll();
     Optional<User> user=uList.stream().filter(u->u.getUserName().equals(name)).findFirst();
     return user;
    }
     
    
}
