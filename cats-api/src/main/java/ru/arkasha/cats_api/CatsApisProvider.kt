package ru.arkasha.cats_api

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface CatsApisProvider {

    val catsApi: CatsApi

}

class CatsApisProviderImpl(
    private val catsBaseUrl: String = "https://cat-fact.herokuapp.com/",
    private val catsApiInterceptors: List<Interceptor> = listOf(
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    ),
    private val catsApiCustomTypeAdapters: Map<Class<*>, Any> = emptyMap()
) : CatsApisProvider {

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