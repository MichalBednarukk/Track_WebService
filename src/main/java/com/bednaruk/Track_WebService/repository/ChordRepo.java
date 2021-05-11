package com.bednaruk.Track_WebService.repository;

import com.bednaruk.Track_WebService.entity.ChordApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChordRepo extends JpaRepository<ChordApp, Long> {
    Optional<ChordApp> findById(Long id);
    Optional<ChordApp> findByChordName(String name);

}
