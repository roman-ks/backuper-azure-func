package com.mashkario.backuper.azure.func.service;

import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.models.CosmosItemRequestOptions;
import com.mashkario.backuper.azure.func.dto.SyncMdDto;

import javax.inject.Inject;
import java.util.List;

public class SyncPersistenceService {

    private final CosmosContainer cosmosContainer;
    private final SyncMdPersistenceMapper mapper;

    @Inject
    public SyncPersistenceService(CosmosContainer cosmosContainer, SyncMdPersistenceMapper mapper) {
        this.cosmosContainer = cosmosContainer;
        this.mapper = mapper;
    }

    public void persist(List<SyncMdDto> dtos) {
        for (SyncMdDto dto : dtos) {
            SyncMdPersistenceDto persistenceDto = mapper.map(dto);
            cosmosContainer.createItem(persistenceDto,
                    new CosmosItemRequestOptions());
        }
    }
}
