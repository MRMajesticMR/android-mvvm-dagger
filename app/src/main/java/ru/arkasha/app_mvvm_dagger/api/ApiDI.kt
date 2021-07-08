package ru.arkasha.app_mvvm_dagger.api

import org.koin.dsl.module

val apiModule = module {

    single<ApisProvider> {
        ApisProviderImpl()
    }

    single {
        get<ApisProvider>().catsApi
    }

}