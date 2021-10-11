package ru.gressor.websockettest.api.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gressor.websockettest.api.HOST
import ru.gressor.websockettest.api.TOKEN_HEADER_CONTENT
import ru.gressor.websockettest.api.TOKEN_HEADER_NAME

class RetrofitServiceProvider {
    companion object {
        val service: RestApiService = Retrofit.Builder()
            .baseUrl(HOST)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val builder = chain.request().newBuilder()
                        chain.proceed(
                            builder.addHeader(TOKEN_HEADER_NAME, TOKEN_HEADER_CONTENT).build()
                        )
                    }
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RestApiService::class.java)
    }
}