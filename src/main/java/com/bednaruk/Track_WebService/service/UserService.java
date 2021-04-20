package com.bednaruk.Track_WebService.service;

import com.bednaruk.Track_WebService.entity.UserApp;
import com.bednaruk.Track_WebService.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {
    UserRepo userRepo;


    public ResponseEntity userRegister(UserApp userApp) {
        Optional<UserApp> userFromDb = userRepo.findByUsername(userApp.getUsername());
        if (userFromDb.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        UserApp savedUserApp = userRepo.save(userApp);
        return ResponseEntity.ok(savedUserApp);
    }
}
