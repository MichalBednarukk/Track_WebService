package com.bednaruk.Track_WebService.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class ChordApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String chordName;

    @NonNull
    private String imageResources;

    @ManyToMany
    private List<TrackApp> trackApps;
}
