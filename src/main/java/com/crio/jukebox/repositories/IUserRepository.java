package com.crio.jukebox.repositories;

import java.util.Optional;
import com.crio.jukebox.entites.User;


public  interface IUserRepository extends CRUDRepository<User,String> {
    public Optional<User> findByName(String name); 

}
