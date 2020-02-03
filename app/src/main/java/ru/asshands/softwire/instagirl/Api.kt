package ru.asshands.softwire.instagirl

import io.reactivex.Observable
import retrofit2.http.GET

interface Api {

//    @GET ("/girls")
    @GET ("/users")
    fun girls(): Observable<List<Girl>>
}