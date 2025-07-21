package ru.mts.media.platform.umc.domain.event;

import ru.mts.media.platform.umc.domain.gql.types.SaveEventInput;
import ru.mts.media.platform.umc.domain.gql.types.Venue;
import ru.mts.media.platform.umc.domain.gql.types.Event;

import java.util.List;

public interface EventSot {

    List<Event> findAllEventsWithVenues();



}
