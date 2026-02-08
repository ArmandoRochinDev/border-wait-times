package com.example.borderwaittimes

import com.armandorochin.borderwaittimes.data.remote.bwt.BwtApi
import com.armandorochin.borderwaittimes.data.remote.bwt.BwtRepository
import com.armandorochin.borderwaittimes.data.remote.bwt.dtos.BorderWaitTime
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Before
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import retrofit2.Call
import retrofit2.Response
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class BwtRepositoryUnitTest {
    private lateinit var repository: BwtRepository
    private val mockApi: BwtApi = mock()
    private val mockCall: Call<BorderWaitTime> = mock()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = BwtRepository(mockApi)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchBwtFromServer returns success when API call succeeds`() = runTest {
        // Given: Successful api call with bwt data in body
        val mockData = BorderWaitTime(
            lastUpdatedDate = "2026-02-04",
            lastUpdatedTime = "17:12:15",
            numberOfPorts = 81,
            ports = null
        )
        val mockResponse = Response.success(mockData)
        whenever(mockApi.getBwtDataFromServer()).thenReturn(mockCall)
        whenever(mockCall.execute()).thenReturn(mockResponse)

        // When: Trying fetch data from server
        val result = repository.fetchBwtFromServer()

        // Then: Must return result success with all the bwt data.
        assertTrue(result.isSuccess)
        assertEquals(mockData, result.getOrNull())
        assertEquals("2026-02-04", result.getOrNull()?.lastUpdatedDate)
        assertEquals(81, result.getOrNull()?.numberOfPorts)
    }

    @Test
    fun `fetchBwtFromServer returns failure when response is not successful`() = runTest {
        // Given: Error reponse code 404
        val mockResponse = Response.error<BorderWaitTime>(
            404,
            "Not Found".toResponseBody()
        )
        whenever(mockApi.getBwtDataFromServer()).thenReturn(mockCall)
        whenever(mockCall.execute()).thenReturn(mockResponse)

        // When: Trying fetch data from server
        val result = repository.fetchBwtFromServer()

        // Then: Must return result failure with error code
        assertTrue(result.isFailure)
        val exception = result.exceptionOrNull()
        assertNotNull(exception)
        assertTrue(exception.message?.contains("404") == true)
        assertTrue(exception.message?.contains("Response was not successful") == true)
    }

    @Test
    fun `fetchBwtFromServer returns failure when response body is null`() = runTest {
        // Given: success response with null body
        val mockResponse = Response.success<BorderWaitTime>(null)
        whenever(mockApi.getBwtDataFromServer()).thenReturn(mockCall)
        whenever(mockCall.execute()).thenReturn(mockResponse)

        // When: Trying fetch data from server
        val result = repository.fetchBwtFromServer()

        // Then: Must return result failure with message indicating that body is null
        assertTrue(result.isFailure)
        val exception = result.exceptionOrNull()
        assertNotNull(exception)
        assertEquals("Response body is null.", exception.message)
    }


    @Test
    fun `fetchBwtFromServer handles empty ports list successfully`() = runTest {
        // Given: Empty port list
        val mockData = BorderWaitTime(
            lastUpdatedDate = "2026-02-04",
            lastUpdatedTime = "17:12:15",
            numberOfPorts = 0,
            ports = emptyList()
        )
        val mockResponse = Response.success(mockData)
        whenever(mockApi.getBwtDataFromServer()).thenReturn(mockCall)
        whenever(mockCall.execute()).thenReturn(mockResponse)

        // When: Trying fetch data from server
        val result = repository.fetchBwtFromServer()

        // Then: Must return result failure with error code
        assertTrue(result.isSuccess)
        assertEquals(0, result.getOrNull()?.numberOfPorts)
        assertTrue(result.getOrNull()?.ports?.isEmpty() == true)
    }
}