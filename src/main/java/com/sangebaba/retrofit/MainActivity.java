package com.sangebaba.retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sangebaba.base.BaseActivity;
import com.sangebaba.util.DebugLog;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class MainActivity extends BaseActivity {

    @Bind(R.id.showButton)
    Button showButton;
    @Bind(R.id.btn_gank)
    Button btnGank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        RxView.clicks(showButton)
//                .throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
//            @Override
//            public void call(Void aVoid) {
//                DebugLog.i("点击了button");
//
//            }
//        });

        // TODO: 2016/12/20 rx 轮询定时操作
        Observable.interval(2, 2, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        DebugLog.i("重启一个线程每隔两秒执行一次");
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        DebugLog.i("每隔两秒执行一次");

                    }
                });

        Observable.timer(2, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                DebugLog.i("两秒钟后去执行某些操作");
            }
        });

        // TODO: 2016/12/20 rxpermissions
        final RxPermissions rxPermissions = new RxPermissions(this);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.showButton)
    public void onShowRepositoriesClick(View view) {
        DebugLog.i("onclick");
        Intent intent = new Intent(this, ReposListActivity.class);
        startActivity(intent);

    }
    @OnClick(R.id.btn_gank)
    public void onGankClick(View view) {
        Intent intent = new Intent(this, GankListActivity.class);
        startActivity(intent);

    }


}
