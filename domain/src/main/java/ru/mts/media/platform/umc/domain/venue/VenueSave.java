package ru.mts.media.platform.umc.domain.venue;

import ru.mts.media.platform.umc.domain.common.EntityEvent;
import ru.mts.media.platform.umc.domain.gql.types.Venue;

public class VenueSave extends EntityEvent<Venue> {
    public VenueSave(Venue entity) {
        super(entity);
    }
}
