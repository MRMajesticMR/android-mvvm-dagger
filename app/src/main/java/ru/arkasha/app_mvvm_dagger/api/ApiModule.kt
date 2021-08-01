package ru.arkasha.app_mvvm_dagger.api

import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import ru.arkasha.app_mvvm_dagger.api.cats.CatsApi

@Module
object ApiModule {

    @Provides
    fun provideCatsApi(
        apisProvider: ApisProvider
    ): CatsApi = apisProvider.catsApi

    @Provides
    fun provideApisProvider(): ApisProvider =
        ApisProviderImpl(
            catsApiInterceptors = listOf(
                HttpLoggingInterceptor().apply {
                    level = (HttpLoggingInterceptor.Level.BODY)
                }
            )
        )

}