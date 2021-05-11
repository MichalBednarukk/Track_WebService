package com.bednaruk.Track_WebService.service;

import com.bednaruk.Track_WebService.entity.ChordApp;
import com.bednaruk.Track_WebService.repository.ChordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ChordService {
    ChordRepo chordRepo;

    @Autowired
    public ChordService(ChordRepo chordRepo) {
        this.chordRepo = chordRepo;
    }

    public ResponseEntity<?> addChord(ChordApp chordApp) {

        if(chordApp.getChordName().isEmpty() || chordApp.getImageResources().isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        chordRepo.save(chordApp);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
