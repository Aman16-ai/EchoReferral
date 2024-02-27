package com.example.echoreferral.data.model.response

data class LoginApiResponse(
    val status:Int?,
    val Response:ResponseBody?
) {
    data class ResponseBody(
        val refresh:String?,
        val access:String?,
    )
}