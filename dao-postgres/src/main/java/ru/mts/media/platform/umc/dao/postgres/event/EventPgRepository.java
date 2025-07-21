package ru.mts.media.platform.umc.dao.postgres.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.mts.media.platform.umc.dao.postgres.common.FullExternalIdPk;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgEntity;

import java.util.List;

@Repository
public interface EventPgRepository extends JpaRepository<EventPgEntity, Long>  {
    @Query("SELECT DISTINCT e FROM EventPgEntity e LEFT JOIN FETCH e.venue")
    List<EventPgEntity> findAllEventsWithVenues();

    @Query("""
            select e from EventPgEntity e
            join e.venue v
            where  v.id.externalId = :externalId
        """)
    List<EventPgEntity> findAllByVenueExternalId(@Param("externalId") String externalId);



}
