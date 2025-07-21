package ru.mts.media.platform.umc.domain.event;

import ru.mts.media.platform.umc.domain.common.EntityEvent;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.Venue;

public class EventSave extends EntityEvent<Event> {
    public EventSave(Event entity) {
        super(entity);
    }
}
