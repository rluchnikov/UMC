package ru.mts.media.platform.umc.dao.postgres;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.api.callback.Callback;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("migrator")
@RequiredArgsConstructor
public class FlywaySpringConfig {
    private final ConfigurableApplicationContext applicationContext;

    @Bean
    public Callback shutdownAfterMigrationCallback() {
        return new ShutdownAfterMigrationCallback(applicationContext);
    }
}
