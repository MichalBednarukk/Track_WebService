package com.bednaruk.Track_WebService.repository;

import com.bednaruk.Track_WebService.entity.TrackApp;
import com.bednaruk.Track_WebService.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;


@Repository
public interface UserRepo extends JpaRepository<UserApp, Long> {
    Optional<UserApp> findByUsername(String username);

}
