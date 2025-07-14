package ru.mts.media.platform.umc.dao.postgres.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class FullExternalIdPk implements Serializable {
    private String brand;
    private String provider;
    private String externalId;
}
