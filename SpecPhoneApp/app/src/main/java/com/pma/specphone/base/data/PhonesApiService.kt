package com.pma.specphone.base.data



import com.pma.specphone.base.model.PhoneResponse
import com.pma.specphone.base.model.PhoneResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PhonesApiService {

    @GET("CPkP06/appphonespec")
    fun getPhonesLatest() : Call<PhoneResponse>


    @GET("CPkP06/appphonespec/{id}")
    fun getPhoneById(@Path("id") id: Int): Call<PhoneResponseItem>

    @GET("6o1pw5/specphone")
    fun getPhonesOld() : Call<PhoneResponse>

    @GET("6o1pw5/specphone/{id}")
    fun getOldPhoneById(@Path("id") id: Int): Call<PhoneResponseItem>

}