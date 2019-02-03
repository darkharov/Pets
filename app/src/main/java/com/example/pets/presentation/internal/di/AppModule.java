package com.example.pets.presentation.internal.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

@Module
public class AppModule {

    @Provides
    @Singleton
    public Cicerone<Router> provideNavigationHolder() {
        return Cicerone.create();
    }

    @Provides
    @Singleton
    public NavigatorHolder provideNavigatorHolder(Cicerone<Router> cicerone) {
        return cicerone.getNavigatorHolder();
    }

    @Provides
    @Singleton
    public Router provideRouter(Cicerone<Router> cicerone) {
        return cicerone.getRouter();
    }
}
