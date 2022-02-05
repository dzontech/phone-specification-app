package com.pma.specphone.base.data

import com.pma.specphone.base.functional.Either
import com.pma.specphone.base.model.PhoneResponse
import com.pma.specphone.base.model.PhoneResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import java.lang.Exception

interface IPhonesDataSource {
    suspend fun getPhonesLatest(): Either<PhoneResponse>
    suspend fun getPhoneById(id: Int): Either<PhoneResponseItem>
    suspend fun getPhonesOld() : Either<PhoneResponse>
    suspend fun getOldPhoneById(id: Int) : Either<PhoneResponseItem>

}

class PhonesDataSource(private val apiService: PhonesApiService): IPhonesDataSource {


    override suspend fun getPhonesLatest(): Either<PhoneResponse> = handleCall(apiService.getPhonesLatest())


    override suspend fun getPhoneById(id: Int): Either<PhoneResponseItem> = handleCall(apiService.getPhoneById(id))
    override suspend fun getPhonesOld(): Either<PhoneResponse> = handleCall(apiService.getPhonesOld())
    override suspend fun getOldPhoneById(id: Int): Either<PhoneResponseItem> = handleCall(apiService.getOldPhoneById(id))


    private suspend fun <T> handleCall(call: Call<T>): Either<T> {

        return withContext(Dispatchers.IO) {
            val response = call.execute()

            if (response.isSuccessful) {
                Either.Success(response.body()!!)
            } else {
                Either.Error(Exception(response.message()))
            }
        }
    }






}