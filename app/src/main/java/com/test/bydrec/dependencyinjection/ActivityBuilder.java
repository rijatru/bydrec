package com.test.bydrec.dependencyinjection;

import com.test.bydrec.view.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
interface ActivityBuilder {

    @ContributesAndroidInjector(modules = ActivityModule.class)
    MainActivity bindMainActivity();
}
