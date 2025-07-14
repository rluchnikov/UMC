package ru.mts.media.platform.umc.domain.venue;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.mts.media.platform.umc.domain.gql.types.SaveVenueInput;
import ru.mts.media.platform.umc.domain.gql.types.Venue;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
interface VenueDomainServiceMapper {
    Venue patch(@MappingTarget Venue src, SaveVenueInput updates);

}
