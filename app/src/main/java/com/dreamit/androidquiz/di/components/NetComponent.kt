package com.dreamit.androidquiz.di.components

import com.dreamit.androidquiz.QuizApp
import com.dreamit.androidquiz.di.modules.NetModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetModule::class)])
interface NetComponent {
    fun inject(app: QuizApp)
}