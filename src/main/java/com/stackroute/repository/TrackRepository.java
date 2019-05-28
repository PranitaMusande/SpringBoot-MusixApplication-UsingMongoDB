package com.stackroute.repository;

import com.stackroute.model.Track;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends MongoRepository<Track,Integer>
{
   // @Query(value = "db.track.find()")
//    public List<Track> getTrackByName(String trackName);
}
