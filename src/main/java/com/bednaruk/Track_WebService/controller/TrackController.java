package com.bednaruk.Track_WebService.controller;


import com.bednaruk.Track_WebService.entity.TrackApp;
import com.bednaruk.Track_WebService.repository.UserRepo;
import com.bednaruk.Track_WebService.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class TrackController {

    TrackService trackService;
    UserRepo userRepo;

    @Autowired
    public TrackController(TrackService trackService, UserRepo userRepo){
        this.trackService = trackService;
        this.userRepo = userRepo;
    }

    @PostMapping("/track")
    public ResponseEntity<Object>  addTrack(@RequestBody TrackApp trackApp){
      return trackService.addTrack(trackApp);
    }

    @PutMapping("/track/{id}")
    public ResponseEntity<Object>  updateTrack(@RequestBody TrackApp trackApp,@PathVariable("id") String id){
        return trackService.updateTrack(trackApp,Long.parseLong(id));
    }

    @GetMapping("/track/{id}")
    public ResponseEntity<Object>  getSingleTrack(@PathVariable("id") String id) {
        return trackService.getSingleTrack(id);
    }

    @GetMapping("/tracks")
    public ResponseEntity<Object>  getAllTrack(){
        return trackService.getAllTrack();
    }

    @DeleteMapping("/track/{id}")
    public ResponseEntity<Object>  deleteSingleTrack(@PathVariable ("id") String id) {
        return trackService.deleteSingleTrack(id);
    }

    @DeleteMapping("/track")
    public ResponseEntity<Object>  deleteAllTrack() {
        return trackService.deleteAllTrack();
    }



}
