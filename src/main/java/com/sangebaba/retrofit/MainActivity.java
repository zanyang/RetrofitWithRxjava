package com.sangebaba.retrofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sangebaba.base.BaseActivity;
import com.sangebaba.util.DebugLog;

import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
