package com.alexrotariu.jokesapp.di

import android.app.Application

class JokesApp : Application() {

    val appComponent: AppComponent = DaggerAppComponent.create()
}