package com.crio.jukebox.repositories;

import java.util.List;
import com.crio.jukebox.entites.Playlist;

public interface IPlaylistRepository  extends CRUDRepository<Playlist,String>{
    List<Playlist> findAllPlaylists();
}
