package com.bednaruk.Track_WebService.entity;
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

    @NonNull
    private String author;

    @ManyToMany(mappedBy = "trackApps",fetch = FetchType.LAZY)

    private Set<ChordApp> chordApps;

    @Override
    public String toString() {
        return super.toString();
    }
}
