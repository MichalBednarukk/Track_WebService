package com.bednaruk.Track_WebService.service;

import com.bednaruk.Track_WebService.entity.TrackApp;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrackServiceTest {

    @Autowired
    private TrackService trackService;

    @Test
    void shouldgetSingleTrack() {
        TrackApp singleTrack = trackService.getSingleTrack("1");
        assertThat(singleTrack).isNotNull();
        assertThat(singleTrack.getId()).isEqualTo(1L);
    }
}