package com.sangebaba.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sangebaba.bean.Repo;
import com.sangebaba.retrofit.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.RepoViewHolder> {


    public static class RepoViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_iv_repo_name)
        TextView itemIvRepoName;
        @Bind(R.id.item_iv_repo_detail)
        TextView itemIvRepoDetail;

        public RepoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindTo(Repo repo) {
            itemIvRepoName.setText(repo.name);
            itemIvRepoDetail.setText(String.valueOf(repo.description + "(" + repo.language + ")"));
        }
    }

    private ArrayList<Repo> mRepos;

    public MyListAdapter() {
        mRepos = new ArrayList<>();
    }

    public void setRepos(ArrayList<Repo> repos) {
        mRepos = repos;
        notifyItemInserted(mRepos.size() - 1);
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_repo, parent, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        holder.bindTo(mRepos.get(position));
    }

    @Override
    public int getItemCount() {
        return mRepos.size();
    }

}
