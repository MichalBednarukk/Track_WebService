package com.bednaruk.Track_WebService.entity;

import lombok.*;

import javax.persistence.*;

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
}
