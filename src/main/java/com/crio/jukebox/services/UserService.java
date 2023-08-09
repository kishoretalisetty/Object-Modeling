package com.crio.jukebox.services;


import com.crio.jukebox.entites.User;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.IUserRepository;


public class UserService implements IUserService {
    
    private final IUserRepository userRepository;
    private final IPlaylistRepository playlistRepository;

    
    public UserService(IUserRepository userRepository, IPlaylistRepository playlistRepository) {
        this.userRepository = userRepository;
        this.playlistRepository = playlistRepository;
    }
    
    // TODO: CRIO_TASK_MODULE_SERVICES
    // Create and store User into the repository.
    @Override
    public User createUser(String name) {
    User entity=new User(name);
    User user=userRepository.save(entity);
    return user;
    }
}
