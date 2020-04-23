package com.github.formatc00.di.component

import android.app.Application
import com.github.formatc00.CryptoPriceApplication
import com.github.formatc00.di.module.ActivitiesModule
import com.github.formatc00.di.module.AppModule
import com.github.formatc00.di.module.ExecutionModule
import com.github.formatc00.di.module.FacadeModule
import com.github.formatc00.di.module.NetworkModule
import com.github.formatc00.di.module.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidSupportInjectionModule::class,
        ActivitiesModule::class,
        ExecutionModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        FacadeModule::class
    ]
)
interface AppComponent : AndroidInjector<CryptoPriceApplication> {
    
    @Component.Builder
    interface Builder {
        
        @BindsInstance
        fun application(application: Application): Builder
        
        fun build(): AppComponent
    }
}