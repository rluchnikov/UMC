package ru.mts.media.platform.umc.dao.postgres.event;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.SaveEventInput;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface EventPgMapper {
    /**
     * Преобразовать JPA-сущность EventPgEntity в DTO Event.
     * @param entity JPA-сущность
     * @return DTO Event
     */
    @Mapping(target = "venues", ignore = true) // обработка связи отдельно
    Event asModel(EventPgEntity entity);

    /**
     * Преобразовать DTO Event в JPA-сущность EventPgEntity.
     * @param event DTO Event
     * @return JPA-сущность
     */
    @Mapping(target = "venues", ignore = true)
    EventPgEntity asEntity(Event event);

}
