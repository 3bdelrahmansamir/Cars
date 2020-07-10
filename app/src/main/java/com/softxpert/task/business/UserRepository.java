package com.softxpert.task.business;

import com.softxpert.task.models.Car;
import com.softxpert.task.models.Response;

import io.reactivex.Flowable;

public class UserRepository {

    WebServices webServices = WebServices.retrofit.create(WebServices.class);

    public Flowable<retrofit2.Response<Response<Car>>> getAllCarInfoByPage(int page) {
        return webServices.getAllCarInfoByPage(page);
    }
}