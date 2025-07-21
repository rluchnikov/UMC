package ru.mts.media.platform.umc.dao.postgres.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgEntity;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgMapper;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgRepository;
import ru.mts.media.platform.umc.domain.event.EventSave;
import ru.mts.media.platform.umc.domain.event.EventSot;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.SaveEventInput;
import ru.mts.media.platform.umc.domain.gql.types.Venue;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DAO для работы с сущностью Event в Postgres.
 * Отвечает за преобразование между JPA и DTO, ручное наполнение связанных Venue.
 * Используется в EventSot.
 */
@Component
@RequiredArgsConstructor
public class EventPgDao implements EventSot {
    private final EventPgRepository repository;
    private final EventPgMapper mapper;
    private final VenuePgRepository venueRepository;
    private final VenuePgMapper venueMapper;


    @Override
    public List<Event> findAllEventsWithVenues() {
        return repository.findAllEventsWithVenues().stream().map(mapper::asModel)
            .toList();
    }

    @EventListener
    public void handleEventCreatedEvent(EventSave event) {

        event.unwrap().ifPresent(e -> {
                  var venues = e.getVenues().stream()
                                    .map(v -> venueRepository.findByReferenceId(v.getId()))
                                    .filter(java.util.Objects::nonNull)
                                    .collect(Collectors.toSet());
                    var entity = mapper.asEntity(e);
                    entity.setVenues(venues);
                    repository.save(entity);
    });
    }

}
