package com.pma.specphone.base.data

object ApiServiceProvider {
    val phonesApiService: PhonesApiService = RetrofitBuilder.retrofit.create(PhonesApiService::class.java)
}