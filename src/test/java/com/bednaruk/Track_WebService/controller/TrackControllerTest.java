package com.bednaruk.Track_WebService.controller;

import com.bednaruk.Track_WebService.entity.TrackApp;
import com.bednaruk.Track_WebService.repository.TrackRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TrackControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TrackRepo trackRepo;

    @Test
    @Transactional
    void shouldGetSingleTrack() throws Exception {
        //given
        TrackApp newTrack = new TrackApp();
        newTrack.setName("name");
        newTrack.setBody("body");
        newTrack.setUsername("Michal");
        trackRepo.save(newTrack);
        //when
        MvcResult mvcResult = mockMvc.perform(get("/track/get/" + newTrack.getId()))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        //then
        TrackApp trackApp = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), TrackApp.class);
        assertThat(trackApp).isNotNull();
        assertThat(trackApp.getId()).isEqualTo(newTrack.getId());
        assertThat(trackApp.getName()).isEqualTo("name");
        assertThat(trackApp.getBody()).isEqualTo("body");
        assertThat(trackApp.getUsername()).isEqualTo("Michal");
    }

    @Transactional
    @Test
    void  shouldUpdateTrack() throws Exception {
        //given
        TrackApp newTrack = new TrackApp();
        newTrack.setName("name");
        newTrack.setBody("body");
        newTrack.setUsername("Michal");
        trackRepo.save(newTrack);

        TrackApp updateTrack = new TrackApp();
        updateTrack.setName("updateName");
        updateTrack.setBody("updateName");
        updateTrack.setUsername("Michal");
        updateTrack.setId(newTrack.getId());

        //when
        MvcResult mvcResult = mockMvc.perform(put("/track/update/" + newTrack.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.IF_MATCH, 3)
                .content(objectMapper.writeValueAsString(updateTrack)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        //then

    }




}