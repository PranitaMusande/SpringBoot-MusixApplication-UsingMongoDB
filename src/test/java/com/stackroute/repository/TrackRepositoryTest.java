package com.stackroute.repository;


import com.stackroute.model.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TrackRepositoryTest
{
    @Autowired
    private TrackRepository trackRepository;
    private Track track;

    @Before
    public void setUp()
    {
        track = new Track();
        track.setTrackId(1);
        track.setTrackName("Tu hi re");
        track.setComments("Good");
    }

    @After
    public void tearDown(){

        trackRepository.deleteAll();
    }

    @Test
    public void testSaveTrack(){
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getTrackId()).get();
        Assert.assertEquals(1,fetchTrack.getTrackId());

    }

    @Test
    public void testSaveTrackFailure(){
        Track testTrack = new Track(1,"Tu hi re", "Good");
        trackRepository.save(track);
        //Track fetchTrack = trackRepository.findById(track.getTrackId()).get();
        Assert.assertNotSame(testTrack,track);
    }

    @Test
    public void testGetAllTrack() {
        Track u = new Track(2, "O piya re", "Nice");
        Track u1 = new Track(3, "Radha ", "Good");
        trackRepository.save(u);
        trackRepository.save(u1);

        List<Track> list = trackRepository.findAll();
        Assert.assertEquals("O piya re", list.get(0).getTrackName());

    }
    }
