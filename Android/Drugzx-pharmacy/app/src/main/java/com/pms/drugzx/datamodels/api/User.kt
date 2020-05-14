package com.pms.drugzx.datamodels.api

data class User(
    val address: String,
    val contactNo: Long,
    val email: String,
    val password: String,
    val role: Role,
    val userId: Int,
    val userName: String,
    val token:String
)