package com.mashkario.backuper.azure.func;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashkario.backuper.azure.func.dto.SyncMdDto;
import com.mashkario.backuper.azure.func.service.SyncPersistenceService;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class HttpTriggerHandler {

    private final SyncPersistenceService persistenceService;
    private final ObjectMapper objectMapper;

    @Inject
    public HttpTriggerHandler(SyncPersistenceService persistenceService, ObjectMapper objectMapper) {
        this.persistenceService = persistenceService;
        this.objectMapper = objectMapper;
    }


    /**
     * This function listens at endpoint "/api/HttpTriggerJava". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpTriggerJava
     * 2. curl {your host}/api/HttpTriggerJava?name=HTTP%20Query
     */
    @FunctionName("sync")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req",
                    methods = {HttpMethod.GET, HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processing a request.");

        Optional<String> body = request.getBody();
        List<SyncMdDto> syncMdDtos;
        if (body.isPresent()) {
            try {
                syncMdDtos = objectMapper.readValue(body.get(), new TypeReference<>() {
                });
            } catch (JsonProcessingException e) {
                context.getLogger().warning("Error parsing body: " + e.getMessage());

                return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                        .body("Can't parse request body")
                        .build();
            }
        } else {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).build();
        }

        persistenceService.persist(syncMdDtos);
        context.getLogger().info("Persisted");
        return request.createResponseBuilder(HttpStatus.OK).build();
    }

}
