package com.bednaruk.Track_WebService.repository;

import com.bednaruk.Track_WebService.entity.TrackApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrackRepo extends JpaRepository<TrackApp, Long> {
    Optional<TrackApp> findById(Long id);
}
