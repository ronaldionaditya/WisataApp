package com.mobile.wisataapp.view

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mobile.wisataapp.R
import com.mobile.wisataapp.adapter.WisataAdapter
import com.mobile.wisataapp.model.ResponseServer
import com.mobile.wisataapp.model.Wisata
import com.mobile.wisataapp.network.ConfigNetwork
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var listWisata: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //pengecekan internet
        if(isConnect()) {

                listWisata = findViewById<RecyclerView>(R.id.listWisata)

            ConfigNetwork.getRetrofit().getDataWisata().enqueue(object : Callback<ResponseServer> {
                override fun onResponse(
                    call: Call<ResponseServer>,
                    response: Response<ResponseServer>
                ) {
                    Log.d("response server", response.message())

                    //check response server
                    if (response.isSuccessful) {
                        progress.visibility = View.GONE
                        Toast.makeText(applicationContext, "success", Toast.LENGTH_SHORT).show()
                        val status = response.body()?.status_code

                        if (status == 200) {

                            val data = response.body()?.data

                            showData(data)
                        }

                    }
                }

                override fun onFailure(call: Call<ResponseServer>, t: Throwable) {
                    progress.visibility = View.GONE
                    t.message?.let { Log.d("error server", it) }
                    Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            })
        }else{

            progress.visibility = View.GONE

            Toast.makeText(this,"device tidak connect dengan internet",Toast.LENGTH_SHORT).show()
        }
    }

    //Check Internet
    fun isConnect() : Boolean{
        //untuk pengecekan konek apa tidaknya, kita membutuhi konektif manajer
        //dan membutuhkan cast
        val connect : ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        //
        return connect.activeNetworkInfo != null && connect.activeNetworkInfo!!.isConnected
    }

    private fun showData(data: List<Wisata>?) {

        listWisata?.adapter = WisataAdapter(data)

    }
}
