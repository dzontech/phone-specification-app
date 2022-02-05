package com.pma.specphone.base.data

import com.pma.specphone.base.functional.Either
import com.pma.specphone.base.model.PhoneResponse
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import org.mockito.MockitoAnnotations.openMocks
import retrofit2.Call
import retrofit2.Response

class PhonesDataSourceTests {

    @Mock
    lateinit var apiService: PhonesApiService
    @Mock
    lateinit var getPhonesCall: Call<PhoneResponse>

    lateinit var dataSource: PhonesDataSource

    @Before
    fun setUp() {
        openMocks(this)
        dataSource = PhonesDataSource(apiService)
    }

    @Test
    fun `test getPhones, has response, Success returned`() = runBlocking {

        val expectedPhones = PhoneResponse()
        val expectedResult = Either.Success(expectedPhones)

        `when`(apiService.getPhonesLatest()).thenReturn(getPhonesCall)
        `when`(getPhonesCall.execute()).thenReturn(Response.success(expectedPhones))

        val result = dataSource.getPhonesLatest()

        assertEquals(expectedResult, result)
    }

    @Test
    fun `test getPhones, has error, Error returned`() = runBlocking {

        val expectedResponseBody = ResponseBody.create(
            MediaType.parse("application/json"), ""
        )

        `when`(apiService.getPhonesLatest()).thenReturn(getPhonesCall)
        `when`(getPhonesCall.execute()).thenReturn(Response.error(400, expectedResponseBody))

        val result = dataSource.getPhonesLatest()

        assertTrue(result is Either.Error)
    }
}