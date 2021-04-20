package com.bednaruk.Track_WebService.controller;


import com.bednaruk.Track_WebService.entity.TrackApp;
import com.bednaruk.Track_WebService.repository.UserRepo;
import com.bednaruk.Track_WebService.service.TrackService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/track")
public class TrackController {

    TrackService trackService;
    UserRepo userRepo;

    @Autowired
    public TrackController(TrackService trackService, UserRepo userRepo){
        this.trackService = trackService;
        this.userRepo = userRepo;
    }

    @PostMapping("/add")
    public ResponseEntity addTrack(@RequestBody TrackApp trackApp){
      return trackService.addTrack(trackApp);
    }


    @GetMapping("/get/{id}")
    public TrackApp getSingleTrack(@PathVariable("id") String id) {
        return trackService.getSingleTrack(id);
    }


}
