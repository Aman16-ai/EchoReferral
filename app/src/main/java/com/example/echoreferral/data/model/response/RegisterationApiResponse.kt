package com.example.echoreferral.data.model.response

data class RegisterationApiResponse(
    val status:Int?,
    val Response:ResponseBody?
) {
    data class ResponseBody(
        val id:Int?,
        val first_name:String?,
        val last_name:String?,
        val email:String?,
        val password:String?,
        val username:String?
    )
}