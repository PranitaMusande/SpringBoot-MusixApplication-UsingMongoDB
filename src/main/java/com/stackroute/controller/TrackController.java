package com.stackroute.controller;


import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.model.Track;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TrackController
{
    @Autowired
    private TrackService trackService;

    public TrackService getTrackService() {
        return trackService;
    }

    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }

    //Add music
    @PostMapping("/music")
    public ResponseEntity<Track> addMusic(@RequestBody Track track)
    {
        ResponseEntity responseEntity = null;
        try {
            return new ResponseEntity<>(trackService.addMusic(track), HttpStatus.CREATED);
        }
        catch (TrackAlreadyExistsException ex)
        {
            responseEntity = new ResponseEntity <String>(ex.getMessage(),HttpStatus.CONFLICT);
            ex.getMessage();
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return  responseEntity;
    }

    //Update Music
    @PutMapping("/music/{id}")
    public ResponseEntity<Track> updateMusic(@RequestBody Track track,@PathVariable int id)
    {
        ResponseEntity responseEntity = null;
        try{
        track.setTrackId(id);
        return new ResponseEntity<>(trackService.addMusic(track), HttpStatus.CREATED);
         }
        catch (TrackAlreadyExistsException ex)
        {
            responseEntity = new ResponseEntity <String>(ex.getMessage(),HttpStatus.CONFLICT);
            ex.getMessage();
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return responseEntity;
    }

    //Get add musics
    @GetMapping("/music")
    public ResponseEntity<List<Track>> getAddMusic()
    {
        ResponseEntity responseEntity = null;
        try {
            return new ResponseEntity<>(trackService.getAllMusic(), HttpStatus.OK);
        }
        catch (TrackNotFoundException ex)
        {
            responseEntity = new ResponseEntity <String>(ex.getMessage(),HttpStatus.CONFLICT);
            ex.getMessage();
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return responseEntity;
    }

    //Delete music
    @DeleteMapping("/music/{id}")
    public ResponseEntity<String> deleteMusic(@PathVariable int id) {
        ResponseEntity responseEntity = null;
        try {
            trackService.deleteMusic(id);
            return new ResponseEntity<>("successs", HttpStatus.GONE);
        } catch (TrackNotFoundException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
            ex.getMessage();
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return responseEntity;
    }




}
