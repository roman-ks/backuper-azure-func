package com.mashkario.backuper.azure.func.service;

import com.mashkario.backuper.azure.func.dto.SyncMdDto;

public class SyncMdPersistenceMapper {

    public SyncMdPersistenceDto map(SyncMdDto mdDto){
        String driveId = mdDto.driveId();
        String path = mdDto.path();
        return new SyncMdPersistenceDto(driveId+"^"+path,
                driveId,
                mdDto.fileName(),
                mdDto.path(),
                driveId,
                mdDto.driveName());
    }

}
