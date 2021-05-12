package com.bednaruk.Track_WebService.controller;

import com.bednaruk.Track_WebService.entity.ChordApp;
import com.bednaruk.Track_WebService.service.ChordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chord")
public class ChordController {
    ChordService chordService;

    @Autowired
    public ChordController(ChordService chordService) {
        this.chordService = chordService;
    }
    @PostMapping("/add")
    public ResponseEntity<Object> addChord(@RequestBody ChordApp chordApp){
        return chordService.addChord(chordApp);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllChord(){
        return chordService.getAllChord();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getSingleChord(@PathVariable("id") String id) {
        return chordService.getSingleChord(id);
    }
}
