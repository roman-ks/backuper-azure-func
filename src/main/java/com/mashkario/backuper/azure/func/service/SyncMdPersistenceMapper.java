package com.mashkario.backuper.azure.func.service;

import com.mashkario.backuper.azure.func.dto.SyncMdDto;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SyncMdPersistenceMapper {

    public SyncMdPersistenceDto map(SyncMdDto mdDto) {
        String driveId = mdDto.driveId();
        String path = mdDto.path();
        String rawId = driveId + "^" + path;
        // base64 encode as path can contain invalid chars
        String base64Id = Base64.getEncoder()
                .encodeToString(rawId.getBytes(StandardCharsets.UTF_8));

        return new SyncMdPersistenceDto(base64Id,
                mdDto.fileName(),
                mdDto.path(),
                driveId,
                mdDto.driveName(),
                mdDto.lastModified());
    }


}
