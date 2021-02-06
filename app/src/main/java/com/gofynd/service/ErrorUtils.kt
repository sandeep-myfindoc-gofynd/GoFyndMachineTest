package com.gofynd.service

import retrofit2.Response
import java.io.IOException

//To handle all non-sucess responses from server
object ErrorUtils {
    fun parseError(response: Response<*>): APIError? {
        val converter = RestClient.retrofit?.responseBodyConverter<APIError>(APIError::class.java, arrayOfNulls(0))
        val error: APIError
        error = try {
            converter?.convert(response.errorBody())!!
        } catch (e: IOException) {
            return APIError()
        }
        return error
    }
}