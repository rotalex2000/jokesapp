package com.alexrotariu.jokesapp.di

import android.content.Context
import com.alexrotariu.jokesapp.presentation.main.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
}