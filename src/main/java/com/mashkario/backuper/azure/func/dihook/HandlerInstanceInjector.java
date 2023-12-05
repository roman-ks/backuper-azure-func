package com.mashkario.backuper.azure.func.dihook;

import com.mashkario.backuper.azure.func.DaggerComponentFactory;
import com.microsoft.azure.functions.spi.inject.FunctionInstanceInjector;

public class HandlerInstanceInjector implements FunctionInstanceInjector {

    @Override
    public <T> T getInstance(Class<T> functionClass) throws Exception {
        @SuppressWarnings("unchecked")
        T t = (T) DaggerComponentFactory.create().createHttpTriggerHandler();
        return t;
    }
}
