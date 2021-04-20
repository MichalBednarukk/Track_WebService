package com.bednaruk.Track_WebService.service;

import com.bednaruk.Track_WebService.repository.UserRepo;
import com.bednaruk.Track_WebService.entity.UserApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserService {

    UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }


    public ResponseEntity userRegister(UserApp userApp) {
        Optional<UserApp> userFromDb = userRepo.findByUsername(userApp.getUsername());
        if (userFromDb.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        UserApp savedUserApp = userRepo.save(userApp);
        return ResponseEntity.ok(savedUserApp);
    }
}
