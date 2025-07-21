package ru.mts.media.platform.umc.dao.postgres.event;

import jakarta.persistence.*;
import lombok.Data;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgEntity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "venue")
public class EventPgEntity {

    @GeneratedValue
    private Long id;;

    @ManyToMany
    @JoinTable(name = "event_venue",
            joinColumns = @JoinColumn(name = "venue_id", referencedColumnName = "externalId"),
            inverseJoinColumns = @JoinColumn(name = "event_id",
                    referencedColumnName = "id"))
    private Set<VenuePgEntity> venues = new HashSet<>();

    private String name;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

}
