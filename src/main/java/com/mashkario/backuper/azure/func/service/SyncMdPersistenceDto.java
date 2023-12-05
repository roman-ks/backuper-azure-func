package com.mashkario.backuper.azure.func.service;

public record SyncMdPersistenceDto(String id,
                                   String fileName,
                                   String path,
                                   String driveId,
                                   String driveName,
                                   long lastModified) {
}
