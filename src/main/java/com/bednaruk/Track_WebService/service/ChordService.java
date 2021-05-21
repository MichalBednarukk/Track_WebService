package com.bednaruk.Track_WebService.service;

import com.bednaruk.Track_WebService.entity.ChordApp;
import com.bednaruk.Track_WebService.repository.ChordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChordService {
    ChordRepo chordRepo;

    @Autowired
    public ChordService(ChordRepo chordRepo) {
        this.chordRepo = chordRepo;
    }

    public ResponseEntity<Object>  addChord(ChordApp chordApp) {

        if(chordApp.getChordName().isEmpty() || chordApp.getImageResources().isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        chordRepo.save(chordApp);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    public ResponseEntity<Object> getAllChord() {
        List<ChordApp> chordApps = chordRepo.findAll();
        return ResponseEntity.ok(chordApps);
    }

    public ResponseEntity<Object> getSingleChord(String id) {
        Optional<ChordApp> chordApp = chordRepo.findById(Long.valueOf(id));
        if(chordApp.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(chordApp.get());
    }
    public ResponseEntity<Object> getChordsByTrackID(String id){
        List<ChordApp> chordApps = chordRepo.findChordsByTrackID(Long.valueOf(id));
        return ResponseEntity.ok(chordApps);
    }
}
