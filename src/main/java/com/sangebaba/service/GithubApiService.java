package com.sangebaba.service;

import com.sangebaba.bean.GankResultBean;
import com.sangebaba.bean.Repo;

import java.util.ArrayList;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by lzy on 2016/12/19.
 */

public interface GithubApiService {

    @GET("/users/{user}/repos")
    Observable<ArrayList<Repo>> getRepoData(@Path("user") String user);

    @GET("all/20/{page}")
    Observable<GankResultBean> getAndroidData(@Path("page") int page);
}