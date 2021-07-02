package ru.arkasha.app_mvvm_dagger.api

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.arkasha.app_mvvm_dagger.api.cats.CatsApi

interface ApisProvider {

    val catsApi: CatsApi

}

class ApisProviderImpl(
    private val catsBaseUrl: String = "https://cat-fact.herokuapp.com/",
    private val catsApiInterceptors: List<Interceptor> = emptyList(),
    private val catsApiCustomTypeAdapters: Map<Class<*>, Any> = emptyMap()
) : ApisProvider {

    override val catsApi: CatsApi =
        Retrofit.Builder()
            .baseUrl(catsBaseUrl)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .apply {
                            catsApiCustomTypeAdapters.forEach {
                                registerTypeAdapter(it.key, it.value)
                            }
                        }
                        .create()
                )
            )
            .client(
                OkHttpClient.Builder()
                    .apply {
                        catsApiInterceptors.forEach { addInterceptor(it) }
                    }
                    .build()
            )
            .build()
            .create(CatsApi::class.java)

}