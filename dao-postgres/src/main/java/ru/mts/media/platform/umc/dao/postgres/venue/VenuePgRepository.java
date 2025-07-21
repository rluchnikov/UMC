package ru.mts.media.platform.umc.dao.postgres.venue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.mts.media.platform.umc.dao.postgres.common.FullExternalIdPk;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VenuePgRepository extends JpaRepository<VenuePgEntity, FullExternalIdPk> {

    VenuePgEntity findByReferenceId(String referenceId);


    @Query("SELECT DISTINCT v FROM VenuePgEntity v LEFT JOIN FETCH v.event")
    List<VenuePgEntity> findAllWithEvents();

    @Query("SELECT v FROM VenuePgEntity v JOIN FETCH v.event e WHERE e.id = :eventId")
    List<VenuePgEntity> findAllByEventId(@Param("eventId") Long eventId);

    @Query("""
            SELECT DISTINCT v FROM VenuePgEntity v
            LEFT JOIN FETCH v.event e
            WHERE e.startTime >= :fromStartTime AND e.endTime <= :toEndTime
        """)
    List<VenuePgEntity> findAllWithEventsFiltered(LocalDateTime fromStartTime, LocalDateTime toEndTime);
}
