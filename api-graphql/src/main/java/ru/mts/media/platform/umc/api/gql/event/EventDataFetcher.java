package ru.mts.media.platform.umc.api.gql.event;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.Venue;
import ru.mts.media.platform.umc.domain.venue.VenueSot;

import java.util.List;

@DgsComponent
@RequiredArgsConstructor
public class EventDataFetcher {

    private final VenueSot venueSot;

    @DgsData(parentType = "Event", field = "venue")
    public List<Venue> getVenues(DgsDataFetchingEnvironment dfe) {
        Event event = dfe.getSource();
        Long eventId = Long.valueOf(event.getId());
        return venueSot.getVenuesByEventId(eventId);
    }
}
