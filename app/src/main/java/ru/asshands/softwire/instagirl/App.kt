package ru.asshands.softwire.instagirl

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    lateinit var api: Api

    private fun getOkHttpInstance(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    override fun onCreate() {
        super.onCreate()



        val retrofit = Retrofit.Builder()
         //   .baseUrl("http://instagirl.getsandbox.ru/")
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(getOkHttpInstance())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        api = retrofit.create(Api::class.java)
    }
}