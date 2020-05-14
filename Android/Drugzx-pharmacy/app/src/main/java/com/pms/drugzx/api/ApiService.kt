package com.pms.drugx.api


import androidx.lifecycle.LiveData
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import com.pms.drugzx.datamodels.Login
import com.pms.drugzx.datamodels.api.CustomerOrder
import com.pms.drugzx.datamodels.api.Products
import com.pms.drugzx.datamodels.api.User
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*


interface ApiService {


    @Headers("Content-Type: application/json")
    @GET("api/product-managment-service/product/search/{pName}")
    suspend fun searchProduct( @Path("pName") pName:String, @Header("AUTH_TOKEN") token:String
    ): List<Products>

    @GET("api/product-managment-service/product/allproducts")
    suspend fun getProducts(@Header("AUTH_TOKEN") token:String
    ): List<Products>

    @Headers("Content-Type: application/json")
    @POST("api/user-managment-service/users/login")
    suspend fun getUser(@Body login:LoginPostData

        ):User

    @Headers("Content-Type: application/json")
    @POST("api/sales-managment-service/order/addorder")
    suspend fun postOrder(@Body customerOrder: CustomerOrder, @Header("AUTH_TOKEN") token:String

    )


    data class LoginPostData(
        @SerializedName("userName") var userName: String,
        @SerializedName("password") var password: String
    )
}