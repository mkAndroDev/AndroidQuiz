package com.dreamit.androidquiz.di.components

import com.dreamit.androidquiz.QuizApp
import com.dreamit.androidquiz.di.modules.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {
    fun inject(app: QuizApp)
}