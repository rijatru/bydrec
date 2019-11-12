package com.test.bydrec.dependencyinjection;

import android.app.Application;
import android.content.Context;

import com.test.business.fixtures.api.FixturesControllerFactory;
import com.test.business.results.api.ResultsControllerFactory;
import com.test.bydrec.adapter.PageAdapterFactory;
import com.test.bydrec.adapter.PageAdapterFactoryImpl;
import com.test.bydrec.view.ItemsListViewFactory;
import com.test.bydrec.view.ItemsListViewFactoryImpl;
import com.test.bydrec.viewmodel.providers.FixturesItemsProviderImpl;
import com.test.bydrec.viewmodel.providers.ResultsItemsProviderImpl;

import dagger.Module;
import dagger.Provides;

@Module
class AppModule {

    @Provides
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    PageAdapterFactory providePageAdapterFactory() {
        return new PageAdapterFactoryImpl();
    }

    @Provides
    FixturesItemsProviderImpl provideFixturesItemsProviderImpl() {
        return new FixturesItemsProviderImpl(FixturesControllerFactory.getFixturesController());
    }

    @Provides
    ResultsItemsProviderImpl provideResultsItemsProviderImpl() {
        return new ResultsItemsProviderImpl(ResultsControllerFactory.getResultsController());
    }

    @Provides
    ItemsListViewFactory provideItemsListViewFactory() {
        return new ItemsListViewFactoryImpl();
    }
}
