package com.bednaruk.Track_WebService;

import com.bednaruk.Track_WebService.entity.UserApp;
import com.bednaruk.Track_WebService.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepo userRepo;

    @Autowired
    public UserDetailsServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserApp> userFromDb = userRepo.findByUsername(username);
        return userFromDb.get();
    }
}
