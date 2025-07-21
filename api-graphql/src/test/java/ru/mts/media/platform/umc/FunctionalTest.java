package ru.mts.media.platform.umc;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;


import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FunctionalTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private static final String GRAPHQL_ENDPOINT = "/graphql";

    @Test
    void testCreateEvent() {
        String mutation = """
            mutation {
              createEvent(
                venueReferenceId: "venue-123",
                name: "Concert",
                startTime: "2025-08-01T18:00:00",
                endTime: "2025-08-01T21:00:00"
              ) {
                id
                name
                startTime
                endTime
                venue {
                  id
                  name
                }
              }
            }
        """;

        Map<String, Object> response = performGraphQlRequest(mutation);

        Map<String, Object> data = (Map<String, Object>) response.get("data");
        Map<String, Object> createEvent = (Map<String, Object>) data.get("createEvent");

        assertThat(createEvent.get("name")).isEqualTo("Concert");

        List<Map<String, Object>> venues = (List<Map<String, Object>>) createEvent.get("venue");
        assertThat(venues).hasSize(1);
        assertThat(venues.get(0).get("name")).isEqualTo("Test Venue");
    }

    @Test
    void testEventsWithVenues() {
        testCreateEvent();

        String query = """
            query {
              eventsWithVenues {
                name
                startTime
                endTime
                venue {
                  name
                }
              }
            }
        """;

        Map<String, Object> response = performGraphQlRequest(query);

        Map<String, Object> data = (Map<String, Object>) response.get("data");
        List<Map<String, Object>> events = (List<Map<String, Object>>) data.get("eventsWithVenues");

        assertThat(events).hasSize(1);
        assertThat(events.get(0).get("name")).isEqualTo("Concert");

        List<Map<String, Object>> venues = (List<Map<String, Object>>) events.get(0).get("venue");
        assertThat(venues.get(0).get("name")).isEqualTo("Test Venue");
    }

    @Test
    void testVenuesWithFilteredEvents() {
        testCreateEvent();

        String query = """
            query {
              venuesWithFilteredEvents(filter: {
                fromStartTime: "2025-08-01T00:00:00",
                toEndTime: "2025-08-02T00:00:00"
              }) {
                name
                event {
                  name
                  startTime
                  endTime
                }
              }
            }
        """;

        Map<String, Object> response = performGraphQlRequest(query);

        Map<String, Object> data = (Map<String, Object>) response.get("data");
        List<Map<String, Object>> venues = (List<Map<String, Object>>) data.get("venuesWithFilteredEvents");

        assertThat(venues).hasSize(1);
        assertThat(venues.get(0).get("name")).isEqualTo("Test Venue");

        List<Map<String, Object>> events = (List<Map<String, Object>>) venues.get(0).get("event");
        assertThat(events).hasSize(1);
        assertThat(events.get(0).get("name")).isEqualTo("Concert");
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> performGraphQlRequest(String query) {
        Map<String, String> body = Map.of("query", query);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(GRAPHQL_ENDPOINT, request, Map.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        return response.getBody();
    }
}
