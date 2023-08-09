package com.crio.jukebox.appConfig;

import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.commands.CreatePlaylistCommand;
import com.crio.jukebox.commands.CreateUserCommand;
import com.crio.jukebox.commands.DeletePlaylistCommand;
import com.crio.jukebox.commands.LoadDataCommand;
import com.crio.jukebox.commands.ModifyPlaylistCommand;
import com.crio.jukebox.commands.PlayPlaylistCommand;
import com.crio.jukebox.commands.PlaySongCommand;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.IsongRepository;
import com.crio.jukebox.repositories.PlaylistRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;
import com.crio.jukebox.services.IPlaylistService;
import com.crio.jukebox.services.ISongService;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.PlaylistService;
import com.crio.jukebox.services.SongService;
import com.crio.jukebox.services.UserService;

public class ApplicationConfigure {
    IsongRepository songRepository=new SongRepository();
    IPlaylistRepository playlistRepository=new PlaylistRepository();
    IUserRepository userRepository=new UserRepository();

    ISongService songService=new SongService(songRepository);
    IPlaylistService playlistService=new PlaylistService(userRepository, songRepository, playlistRepository);
    IUserService userService=new UserService(userRepository, playlistRepository);

    LoadDataCommand loadDataCommand=new LoadDataCommand(songService);
    CreateUserCommand createUserCommand=new CreateUserCommand(userService);
    CreatePlaylistCommand createPlaylistCommand=new CreatePlaylistCommand(playlistService);
   
    DeletePlaylistCommand deletePlaylistCommand=new DeletePlaylistCommand(playlistService);
    ModifyPlaylistCommand modifyPlaylistCommand=new ModifyPlaylistCommand(playlistService);
    PlayPlaylistCommand playPlaylistCommand=new PlayPlaylistCommand(playlistService);
    PlaySongCommand playSongCommand=new PlaySongCommand(playlistService);


    private final CommandInvoker commandInvoker = new CommandInvoker();
     public CommandInvoker getCommandInvoker(){
        commandInvoker.register("LOAD-DATA",loadDataCommand);

        commandInvoker.register("CREATE-USER",createUserCommand);

        commandInvoker.register("CREATE-PLAYLIST",createPlaylistCommand);
        commandInvoker.register("CREATE-PLAYLIST",createPlaylistCommand);
        
        commandInvoker.register("DELETE-PLAYLIST",deletePlaylistCommand);

        commandInvoker.register("PLAY-PLAYLIST",playPlaylistCommand);

        commandInvoker.register("MODIFY-PLAYLIST",modifyPlaylistCommand);
        commandInvoker.register("MODIFY-PLAYLIST",modifyPlaylistCommand);
        
        commandInvoker.register("PLAY-SONG",playSongCommand);
        commandInvoker.register("PLAY-SONG",playSongCommand);
        commandInvoker.register("PLAY-SONG",playSongCommand);
        commandInvoker.register("PLAY-SONG",playSongCommand);
        commandInvoker.register("PLAY-SONG",playSongCommand);
        commandInvoker.register("PLAY-SONG",playSongCommand);

        return commandInvoker;
    }
}
