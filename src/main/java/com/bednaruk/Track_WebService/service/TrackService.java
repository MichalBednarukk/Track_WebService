package com.bednaruk.Track_WebService.service;

import com.bednaruk.Track_WebService.entity.TrackApp;
import com.bednaruk.Track_WebService.entity.UserApp;
import com.bednaruk.Track_WebService.repository.TrackRepo;
import com.bednaruk.Track_WebService.repository.UserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TrackService {
    private final TrackRepo trackRepo;
    private final UserRepo userRepo;
    private ObjectMapper objectMapper = new ObjectMapper();
    private HttpSecurity httpSecurity;


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

    public List<TrackApp> getAllTrack() {
        List<TrackApp> trackApps = trackRepo.findAll();
        return trackApps;
    }

    public ResponseEntity deleteSingleTrack(String id) {
        trackRepo.deleteById(Long.parseLong(id));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    public ResponseEntity deleteAllTrack() {
        trackRepo.deleteAll();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    public ResponseEntity updateTrack(TrackApp trackApp, Long id) {
        Optional<TrackApp> trackAppOptional  = trackRepo.findById(id);
        if (!trackAppOptional.isPresent())
            return ResponseEntity.notFound().build();
        trackApp.setId(id);
        trackRepo.save(trackApp);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
