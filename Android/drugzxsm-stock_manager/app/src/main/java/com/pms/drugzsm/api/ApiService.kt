package com.pms.drugx.api


import com.google.gson.annotations.SerializedName
import com.pms.drugzsm.datamodels.api.*
import retrofit2.http.*


interface ApiService {

//    @GET("/products/{userId}")
//    suspend fun getProducts(
//        @Path("userId") userId: String
//    ): List<Product>

    @Headers("Content-Type: application/json")
    @GET("api/supplier-managment-service/supplier/searchSupplierName/{sName}")
    suspend fun searchSupplier( @Path("sName") sName:String, @Header("AUTH_TOKEN") token:String
    ): List<Suppliers>

    @Headers("Content-Type: application/json")
    @GET("api/product-managment-service/product/search/{pName}")
    suspend fun searchProduct( @Path("pName") pName:String,@Header("AUTH_TOKEN") token:String
    ): List<Products>

    @GET("api/supplier-managment-service/supplier/allSupplier/")
    suspend fun getSuppliers(@Header("AUTH_TOKEN") token:String
    ): List<Suppliers>



    @GET("api/product-managment-service/product/allproducts")
    suspend fun getProducts(@Header("AUTH_TOKEN") token:String
    ): List<Products>

    @Headers("Content-Type: application/json")
    @POST("api/user-managment-service/users/login")
    suspend fun getUser(@Body login:LoginPostData

    ):User

    @Headers("Content-Type: application/json")
    @POST("api/purchase-managment-service/purchaseOrder/addOrderProduct")
    suspend fun postOrder(@Body supplierOrder: SupplierOrder, @Header("AUTH_TOKEN") token:String

    ):OrdersList
    @Headers("Content-Type: application/json")
    @POST("api/supplier-managment-service/supplier/addSupplier")
    suspend fun addSupplier(@Body supplier:Supplier,@Header("AUTH_TOKEN") token:String
    )
    @Headers("Content-Type: application/json")
    @POST("api/product-managment-service/product/addproduct")
    suspend fun addProduct(@Body product: Product,@Header("AUTH_TOKEN") token:String

    )

    @Headers("Content-Type: application/json")
    @PUT("supplier-managment-service/supplier/updateSupplier/{supplierId}")
    suspend fun updateSupplier(  @Path("supplierId") supplierId: Int, @Body supplier:Suppliers,@Header("AUTH_TOKEN") token:String
    )
    @Headers("Content-Type: application/json")
    @PUT("product-managment-service/product/updateproduct/{productId}")
    suspend fun updateProduct(@Path("productId") productId: Int,@Body product: Products,@Header("AUTH_TOKEN") token:String

    )
    @Headers("Content-Type: application/json")
    @DELETE("supplier-managment-service/supplier/delete/{supplierId}")
    suspend fun deleteSupplier(@Path("supplierId") supplierId: Int,@Header("AUTH_TOKEN") token:String
    )
    @Headers("Content-Type: application/json")
    @DELETE("product-managment-service/product/deleteproduct/{productId}")
    suspend fun deleteProduct(@Path("productId") productId: Int,@Header("AUTH_TOKEN") token:String

    )

    data class LoginPostData(
        @SerializedName("userName") var userName: String,
        @SerializedName("password") var password: String
    )
}