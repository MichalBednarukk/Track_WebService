package com.bednaruk.Track_WebService.service;

import com.bednaruk.Track_WebService.entity.ChordApp;
import com.bednaruk.Track_WebService.entity.TrackApp;
import com.bednaruk.Track_WebService.entity.UserApp;
import com.bednaruk.Track_WebService.repository.ChordRepo;
import com.bednaruk.Track_WebService.repository.TrackRepo;
import com.bednaruk.Track_WebService.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class TrackService {
    private final TrackRepo trackRepo;
    private final UserRepo userRepo;
    private final ChordRepo chordRepo;


    public ResponseEntity<Object> addTrack(TrackApp trackApp){

        Optional<UserApp> userFromDb = userRepo.findByUsername(trackApp.getUsername());
        if (userFromDb.isEmpty()) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        if(trackApp.getBody().isEmpty() || trackApp.getName().isEmpty()|| trackApp.getChordApps().isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        trackRepo.save(trackApp);
        for (ChordApp chordApp:trackApp.getChordApps()) {
            if(chordRepo.getChord(chordApp.getChordName()) == null){
                chordApp.setTrackApps(new HashSet<>());
            }
            else{
                chordApp = chordRepo.getChord(chordApp.getChordName());
            }
            chordApp.getTrackApps().add(trackApp);
            chordRepo.save(chordApp);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    public ResponseEntity<Object>  getSingleTrack(String id)  {
        Optional<TrackApp> trackFromDb = trackRepo.findById(Long.valueOf(id));
        if(trackFromDb.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
        if(trackApp.getBody().isEmpty() || trackApp.getName().isEmpty()|| trackApp.getChordApps().isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        trackApp.setId(id);
        trackRepo.save(trackApp);
        for (ChordApp chordApp:trackApp.getChordApps()
        ) {
            chordApp = chordRepo.getChord(chordApp.getChordName());//
            chordApp.getTrackApps().add(trackApp);
            chordRepo.save(chordApp);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
