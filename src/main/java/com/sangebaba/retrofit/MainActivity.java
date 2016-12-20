package com.sangebaba.retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jakewharton.rxbinding.view.RxView;
import com.sangebaba.base.BaseActivity;
import com.sangebaba.util.DebugLog;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;


public class MainActivity extends BaseActivity {

    @Bind(R.id.showButton)
    Button showButton;

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


}
