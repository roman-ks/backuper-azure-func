package com.mashkario.backuper.azure.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashkario.backuper.azure.func.dto.SyncMdDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SyncMdDtoTest {

    @Test
    void serialize() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        var json = """
                {
                    "fileName":"f",
                    "path":"pat",
                    "driveId":"id",
                    "driveName":"name"
                }
                """;
        SyncMdDto syncMdDto = objectMapper.readValue(json, SyncMdDto.class);

        assertEquals("f", syncMdDto.fileName());
        assertEquals("pat", syncMdDto.path());
        assertEquals("id", syncMdDto.driveId());
        assertEquals("name", syncMdDto.driveName());
    }

}