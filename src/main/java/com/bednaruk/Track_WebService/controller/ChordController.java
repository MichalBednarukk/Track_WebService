package com.bednaruk.Track_WebService.controller;

import com.bednaruk.Track_WebService.entity.ChordApp;
import com.bednaruk.Track_WebService.entity.TrackApp;
import com.bednaruk.Track_WebService.repository.ChordRepo;
import com.bednaruk.Track_WebService.service.ChordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/track")
public class ChordController {
    ChordService chordService;
    ChordRepo chordRepo;

    @Autowired
    public ChordController(ChordService chordService, ChordRepo chordRepo) {
        this.chordService = chordService;
        this.chordRepo = chordRepo;
    }
    @PostMapping("/add")
    public ResponseEntity<?> addChord(@RequestBody ChordApp chordApp){
        return chordService.addChord(chordApp);
    }
}
