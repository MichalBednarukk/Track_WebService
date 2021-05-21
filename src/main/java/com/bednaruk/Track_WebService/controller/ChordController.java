package com.bednaruk.Track_WebService.controller;

import com.bednaruk.Track_WebService.entity.ChordApp;
import com.bednaruk.Track_WebService.service.ChordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChordController {
    ChordService chordService;

    @Autowired
    public ChordController(ChordService chordService) {
        this.chordService = chordService;
    }
    @PostMapping("/chord")
    public ResponseEntity<Object> addChord(@RequestBody ChordApp chordApp){
        return chordService.addChord(chordApp);
    }

    @GetMapping("/chords")
    public ResponseEntity<Object> getAllChord(){
        return chordService.getAllChord();
    }

    @GetMapping("/chord/{id}")
    public ResponseEntity<Object> getSingleChord(@PathVariable("id") String id) {
        return chordService.getSingleChord(id);
    }
    @GetMapping("/chords/track/{id}")
    public ResponseEntity<Object> getChordsByTrackID(@PathVariable("id") String id) {
        return chordService.getChordsByTrackID(id);
    }
}
