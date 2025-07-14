package ru.mts.media.platform.umc.api.gql;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!migrator")
public class GraphqlSpringConfig {
}
