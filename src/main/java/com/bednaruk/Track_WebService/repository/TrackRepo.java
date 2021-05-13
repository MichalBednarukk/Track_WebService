package com.bednaruk.Track_WebService.repository;

import com.bednaruk.Track_WebService.entity.ChordApp;
import com.bednaruk.Track_WebService.entity.TrackApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TrackRepo extends JpaRepository<TrackApp, Long> {
    @Query(value = "SELECT t FROM TrackApp t JOIN FETCH t.chordApps c")
    Set<TrackApp> getAll();

}
