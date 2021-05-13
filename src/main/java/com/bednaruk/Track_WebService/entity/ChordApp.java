package com.bednaruk.Track_WebService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Track_Chords",
            joinColumns = { @JoinColumn(name = "Chord_ID") },
            inverseJoinColumns = { @JoinColumn(name = "Track_ID" ) })
    @JsonIgnore
    private Set<TrackApp> trackApps;

    public static Builder builder() {
        return new Builder();
    }
    public static final class Builder {
        private Long id;
        private String chordName;
        private String imageResources;
        private Set<TrackApp> trackApps;


        public Builder chordName(Long id) {
            this.id = id;
            return this;
        }
        public Builder chordName(String chordName) {
            this.chordName = chordName;
            return this;
        }
        public Builder imageResources(String imageResources) {
            this.imageResources = imageResources;
            return this;
        }
        public Builder trackApps(Set<TrackApp> trackApps) {
            this.trackApps = trackApps;
            return this;
        }
        public ChordApp build() {
            ChordApp chordApp = new ChordApp();
            chordApp.id = this.id;
            chordApp.chordName = this.chordName;
            chordApp.imageResources = this.imageResources;
            chordApp.trackApps = this.trackApps;
            return chordApp;
        }
    }
}
