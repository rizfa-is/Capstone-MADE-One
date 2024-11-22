package com.issog.capstonemadeone

import com.issog.capstonemadeone.core.utils.base.BaseApplication
import com.issog.capstonemadeone.di.useCaseModule

class MovieApplication: BaseApplication() {

    override fun addModule(koin: org.koin.core.KoinApplication) {
        super.addModule(koin)
        koin.modules(
            useCaseModule
        )
    }
}