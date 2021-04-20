package com.bednaruk.Track_WebService.service;


import com.bednaruk.Track_WebService.entity.TrackApp;
import com.bednaruk.Track_WebService.entity.UserApp;
import com.bednaruk.Track_WebService.repository.TrackRepo;
import com.bednaruk.Track_WebService.repository.UserRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/track")
public class TrackService {

    TrackRepo trackRepo;
    UserRepo userRepo;
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public TrackService(TrackRepo trackRepo, UserRepo userRepo){
        this.trackRepo = trackRepo;
        this.userRepo = userRepo;
    }

    @PostMapping("/add")
    public ResponseEntity addTrack(@RequestBody TrackApp trackApp){
        Optional<UserApp> userFromDb = userRepo.findByUsername(trackApp.getUsername());

        if (userFromDb.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
       }
        TrackApp savedTrackApp = trackRepo.save(trackApp);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @GetMapping("/get/{id}")
    public String getTrack(@PathVariable("id") String id) throws JsonProcessingException {
        Optional<TrackApp> trackFromDb = trackRepo.findById(Long.valueOf(id));
        return objectMapper.writeValueAsString(trackFromDb.get());

    }


}
