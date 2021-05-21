package com.bednaruk.Track_WebService.repository;

import com.bednaruk.Track_WebService.entity.ChordApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChordRepo extends JpaRepository<ChordApp, Long> {
    Optional<ChordApp> findById(Long id);
    Optional<ChordApp> findByChordName(String name);
    @Query(value = "SELECT c FROM ChordApp c JOIN FETCH c.trackApps t WHERE c.chordName = :chordName")
    ChordApp getChord(String chordName);
    @Query(value = "SELECT c FROM ChordApp c JOIN FETCH c.trackApps t WHERE t.id = :id")
    List<ChordApp> findChordsByTrackID(Long id);
}
