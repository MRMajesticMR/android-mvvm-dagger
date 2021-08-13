package ru.arkasha.app_mvvm_dagger.api

import dagger.Module
import dagger.Provides
import ru.arkasha.cats_api.CatsApi
import ru.arkasha.cats_api.CatsApisProviderImpl

@Module
object ApiModule {

    @Provides
    fun provideCatsApi(): CatsApi =
        CatsApisProviderImpl().catsApi

}