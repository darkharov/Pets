package com.example.pets.presentation.internal.di;

import com.example.pets.data.internal.di.ApiModule;
import com.example.pets.presentation.Application;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                ApiModule.class,
                ImplsModule.class,
                ActivitiesModule.class,
                FragmentsModule.class,
                ViewModelsModule.class,
                AndroidSupportInjectionModule.class
        }
)
public interface AppComponent extends AndroidInjector<Application> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<Application> {}
}
