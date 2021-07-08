package ru.arkasha.app_mvvm_dagger.api

import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

val apiModule = module {

    single<ApisProvider> {
        ApisProviderImpl(
            catsApiInterceptors = listOf(
                HttpLoggingInterceptor().apply {
                    level = (HttpLoggingInterceptor.Level.BODY)
                }
            )
        )
    }

    single {
        get<ApisProvider>().catsApi
    }

}