package ru.mts.media.platform.umc.dao.postgres;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.api.callback.Callback;
import org.flywaydb.core.api.callback.Context;
import org.flywaydb.core.api.callback.Event;
import org.springframework.context.ConfigurableApplicationContext;


@RequiredArgsConstructor
@Slf4j
public class ShutdownAfterMigrationCallback implements Callback {
    private final ConfigurableApplicationContext applicationContext;

    @Override
    public boolean supports(Event event, Context context) {
        return Event.AFTER_MIGRATE.equals(event);
    }

    @Override
    public void handle(Event event, Context context) {
        if (Event.AFTER_MIGRATE.equals(event)) {
            log.info("Flyway migrations completed successfully. Shutting down application...");
            applicationContext.close();
            System.exit(0);
        }
    }

    @Override
    public boolean canHandleInTransaction(Event event, Context context) {
        return false;
    }

    @Override
    public String getCallbackName() {
        return "ShutdownAfterMigrationCallback";
    }
}
