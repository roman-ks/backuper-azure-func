package com.mashkario.backuper.azure.func;

import com.mashkario.backuper.azure.func.module.SyncModule;
import com.mashkario.backuper.azure.func.service.SyncPersistenceService;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = SyncModule.class)
public interface ComponentFactory {

    HttpTriggerHandler createHttpTriggerHandler();

}
