package com.mashkario.backuper.azure.func.service;

public record SyncMdPersistenceDto(String id,
                                   String partitionKey,
                                   String fileName,
                                   String path,
                                   String driveId,
                                   String driveName) {
}
