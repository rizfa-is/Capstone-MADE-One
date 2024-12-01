package com.issog.capstonemadeone

import com.issog.capstonemadeone.core.utils.base.BaseApplication
import com.issog.capstonemadeone.di.useCaseModule
import com.issog.capstonemadeone.di.viewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.core.KoinApplication
import org.koin.core.module.Module

class MovieApplication: BaseApplication() {

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    override fun addModule(koin: KoinApplication, modules: ArrayList<Module>) {
        super.addModule(koin, modules)
        modules.add(useCaseModule)
        modules.add(viewModelModule)
        koin.modules(modules)
    }
}