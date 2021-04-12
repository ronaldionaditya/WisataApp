package com.mobile.wisataapp.network

import com.mobile.wisataapp.model.ResponseServer
import retrofit2.Call
import retrofit2.http.GET

interface WisataService {

    @GET("?action=findAll")
    fun getDataWisata():Call<ResponseServer>
}