package com.renderson.booksmvvm.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiService {

    /**
     * CONFIGURAÇÃO DO RETROFIT - Singleton para instância do cliente HTTP
     * Configura base URL, converter factory e cria service
     */

    private fun initRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/books/v3/")
            .addConverterFactory(MoshiConverterFactory.create()) // Conversor Moshi para JSON
            .build()
    }
    // Instância singleton do service
    val service: NYTServices = initRetrofit().create(NYTServices::class.java)
}