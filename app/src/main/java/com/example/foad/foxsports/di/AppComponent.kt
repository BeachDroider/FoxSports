package com.example.foad.foxsports.di

import android.app.Application
import com.example.foad.foxsports.matchstats.MatchStatsActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder


    }


    fun inject(matchStatsActivity: MatchStatsActivity)

}