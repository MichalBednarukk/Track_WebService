package com.bednaruk.Track_WebService.service;

import com.bednaruk.Track_WebService.entity.TrackApp;
import com.bednaruk.Track_WebService.entity.UserApp;
import com.bednaruk.Track_WebService.repository.TrackRepo;
import com.bednaruk.Track_WebService.repository.UserRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TrackService {
    private final TrackRepo trackRepo;
    private final UserRepo userRepo;
    private ObjectMapper objectMapper = new ObjectMapper();


    public ResponseEntity addTrack(TrackApp trackApp){
        Optional<UserApp> userFromDb = userRepo.findByUsername(trackApp.getUsername());
        if (userFromDb.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        trackRepo.save(trackApp);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    public TrackApp getSingleTrack(String id)  {
        Optional<TrackApp> trackFromDb = trackRepo.findById(Long.valueOf(id));
        return trackFromDb.get();
    }
}
