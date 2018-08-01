package com.dreamit.androidquiz

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class QuizApp : Application() {

    lateinit var realm: Realm

    override fun onCreate() {
        super.onCreate()
        setupRealm()
    }

    private fun setupRealm() {
        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .name("quiz.realm")
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .build()
        Realm.setDefaultConfiguration(config)
        realm = Realm.getDefaultInstance()
    }

}