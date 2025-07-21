CREATE TABLE if not exists event
(
    id        VARCHAR(255) NOT NULL,
    name     VARCHAR(255) NOT NULL,
    startTime  timestamp NOT NULL,
    endTime    timestamp NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS event_venue
(
    event_id        VARCHAR(255) REFERENCES events(id),
    venue_id        VARCHAR(255) REFERENCES venues(id),
    PRIMARY KEY (event_id, venue_id)
);