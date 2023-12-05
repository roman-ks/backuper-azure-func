package com.mashkario.backuper.azure.func.service;

import com.mashkario.backuper.azure.func.dto.SyncMdDto;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SyncMdPersistenceMapper {

    public SyncMdPersistenceDto map(SyncMdDto mdDto) {
        String driveId = mdDto.driveId();
        String encodePath = URLEncoder.encode(mdDto.path(), StandardCharsets.UTF_8);

        return new SyncMdPersistenceDto(driveId + "^" + encodePath,
                mdDto.fileName(),
                mdDto.path(),
                driveId,
                mdDto.driveName(),
                mdDto.lastModified());
    }

}
