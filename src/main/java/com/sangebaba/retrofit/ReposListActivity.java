package com.sangebaba.retrofit;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ProgressBar;
import android.support.v7.widget.RecyclerView;

import com.sangebaba.adapter.MyListAdapter;
import com.sangebaba.base.BaseActivity;
import com.sangebaba.bean.Repo;
import com.sangebaba.service.GithubApiService;

import java.util.ArrayList;


import butterknife.Bind;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ReposListActivity extends BaseActivity {

    // TODO: 2016/12/20 使用butterknife 自动创建
    @Bind(R.id.pbLoading)
    ProgressBar pbLoading;
    @Bind(R.id.repos_rv_list)
    RecyclerView reposRvList;

    GithubApiService githubApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit build = builder.baseUrl("https://api.github.com")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        githubApiService = build.create(GithubApiService.class);

        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_repo_list;
    }


    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        reposRvList.setLayoutManager(manager);

        // TODO: 2016/12/20 创建适配器
        MyListAdapter adapter = new MyListAdapter();
        reposRvList.setAdapter(adapter);
        loadData(adapter);
    }

    private void loadData(final MyListAdapter adapter) {
        showLoading(true);
        githubApiService.getRepoData("bird1015")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<ArrayList<Repo>>() {
                    @Override
                    public void onNext(ArrayList<Repo> repos) {
                        showLoading(false);

                        adapter.setRepos(repos);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showLoading(false);
                    }
                });
    }

    public void showLoading(boolean loading) {
        pbLoading.setVisibility(loading ? View.VISIBLE : View.GONE);
    }
}
