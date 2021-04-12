package com.mobile.wisataapp.model

class ResponseServer {

    var status_code : Int? = null
    var message: String? = null
    var data: ArrayList<Wisata>? = null
}

class Wisata {
    var id : String? = null
    var nama_tempat : String? = null
    var lokasi : String? = null
    var deskripsi : String? = null
    var gambar : String? = null
}