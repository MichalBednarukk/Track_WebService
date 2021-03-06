package com.bednaruk.Track_WebService.service;

import com.bednaruk.Track_WebService.entity.TrackApp;
import com.bednaruk.Track_WebService.entity.UserApp;
import com.bednaruk.Track_WebService.repository.TrackRepo;
import com.bednaruk.Track_WebService.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class TrackService {
    private final TrackRepo trackRepo;
    private final UserRepo userRepo;

    public ResponseEntity<Object> addTrack(TrackApp trackApp){

        Optional<UserApp> userFromDb = userRepo.findByUsername(trackApp.getUsername());
        if (userFromDb.isEmpty()) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        if(trackApp.getBody().isEmpty() || trackApp.getName().isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        trackRepo.save(trackApp);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    public ResponseEntity<Object>  getSingleTrack(String id)  {
        Optional<TrackApp> trackFromDb = trackRepo.findById(Long.valueOf(id));
        if(trackFromDb.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(trackFromDb.get());
    }

    public ResponseEntity<Object>  getAllTrack() {
        Set<TrackApp> trackApps = trackRepo.getAll();
        return ResponseEntity.ok(trackApps);
    }

    public ResponseEntity<Object>  deleteSingleTrack(String id) {
        trackRepo.deleteById(Long.parseLong(id));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    public ResponseEntity<Object>  deleteAllTrack() {
        trackRepo.deleteAll();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    public ResponseEntity<Object>  updateTrack(TrackApp trackApp, Long id) {
        Optional<TrackApp> trackAppOptional  = trackRepo.findById(id);
        if (trackAppOptional.isEmpty())
            return ResponseEntity.notFound().build();
        trackApp.setId(id);
        trackRepo.save(trackApp);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
