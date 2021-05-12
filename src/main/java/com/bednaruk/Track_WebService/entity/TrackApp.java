package com.bednaruk.Track_WebService.entity;

import lombok.*;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;

import javax.persistence.*;
import java.util.List;

@Table
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class TrackApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String username;

    @NonNull
    private String name;

    @NonNull
    @Column(columnDefinition = "LONGTEXT")
    private String body;

    @ManyToMany(mappedBy = "trackApps")
    private List<ChordApp> chordApps;

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private String username;
        private String name;
        private String body;
        private List<ChordApp> chordApps;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        public Builder username(String username) {
            this.username = username;
            return this;
        }
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder body(String body) {
            this.body = body;
            return this;
        }
        public Builder chordApps(List<ChordApp> chordApps) {
            this.chordApps = chordApps;
            return this;
        }
        public TrackApp build() {
            TrackApp trackApp = new TrackApp();
            trackApp.id = this.id;
            trackApp.name = this.name;
            trackApp.username = this.username;
            trackApp.body = this.body;
            trackApp.chordApps = this.chordApps;
            return trackApp;
        }
    }
}
