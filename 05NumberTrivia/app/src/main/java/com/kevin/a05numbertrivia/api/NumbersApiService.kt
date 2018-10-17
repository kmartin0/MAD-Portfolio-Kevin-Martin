package com.kevin.a05numbertrivia.api

import com.kevin.a05numbertrivia.model.Number
import io.reactivex.Observable
import retrofit2.http.GET

interface NumbersApiService {

    @GET("/random/trivia?json")
    fun getRandomNumberTrivia(): Observable<Number>

}