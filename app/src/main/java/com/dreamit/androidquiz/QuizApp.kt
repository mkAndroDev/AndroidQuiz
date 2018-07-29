package com.dreamit.androidquiz

import android.app.Application
import com.dreamit.androidquiz.di.components.AppComponent
import com.dreamit.androidquiz.di.components.DaggerAppComponent
import com.dreamit.androidquiz.di.components.DaggerNetComponent
import com.dreamit.androidquiz.di.components.NetComponent
import com.dreamit.androidquiz.di.modules.AppModule
import com.dreamit.androidquiz.di.modules.NetModule
import com.dreamit.androidquiz.net.ConfigEndpoints
import io.realm.Realm
import io.realm.RealmConfiguration

class QuizApp : Application() {

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var netComponent: NetComponent
    }

    lateinit var realm: Realm

    override fun onCreate() {
        super.onCreate()
        setupRealm()
        setupAppComponent()
        setupNetComponent()
    }

    private fun setupAppComponent() {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    private fun setupNetComponent() {
        netComponent = DaggerNetComponent
                .builder()
                .netModule(NetModule(ConfigEndpoints.BASE_URL))
                .build()
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