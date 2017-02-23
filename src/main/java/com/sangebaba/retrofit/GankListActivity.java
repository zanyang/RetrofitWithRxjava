package com.sangebaba.retrofit;

import android.os.Bundle;

import com.sangebaba.base.BaseActivity;
import com.sangebaba.bean.GankResultBean;
import com.sangebaba.bean.ResultsBean;
import com.sangebaba.service.GithubApiService;

import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lzy on 2016/12/21.
 */
public class GankListActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/data/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubApiService githubApiService = retrofit.create(GithubApiService.class);
        githubApiService.getAndroidData(1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<GankResultBean, List<ResultsBean>>() {
                    @Override
                    public List<ResultsBean> call(GankResultBean gankResultBean) {
                        return gankResultBean.getResults();
                    }
                })
                .flatMap(new Func1<List<ResultsBean>, Observable<?>>() {
                    @Override
                    public Observable<?> call(List<ResultsBean> resultsBeen) {
                        return Observable.from(resultsBeen);
                    }
                });


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gank_list;
    }
}
