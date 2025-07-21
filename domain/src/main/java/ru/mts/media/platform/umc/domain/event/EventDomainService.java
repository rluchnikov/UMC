package ru.mts.media.platform.umc.domain.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.SaveEventInput;

import java.util.List;

/**
 * Сервис бизнес-логики для работы с событиями (Event).
 * Отвечает за создание, получение и обработку событий.
 */
@Service
@RequiredArgsConstructor
public class EventDomainService {
    private final EventSot sot;
    private final EventDomainServiceMapper mapper;
    private final ApplicationEventPublisher eventPublisher;

    /**
     * Сохранить событие на основе входных данных.
     * @param input входные данные для создания/обновления события
     * @return сохранённый Event
     */
    public EventSave create(SaveEventInput input) {
        var event = mapper.fromInput(input);
        EventSave eventSave = new EventSave(event);
        eventPublisher.publishEvent(eventSave);
        return eventSave;
    }


}