package ru.mts.media.platform.umc.domain.common;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Data
@RequiredArgsConstructor
public abstract class EntityEvent<T> {
    private final T entity;

    public Optional<T> unwrap() {
        return Optional.ofNullable(entity);
    }
}
