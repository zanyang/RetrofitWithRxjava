package com.sangebaba.bean;


import java.util.List;

/**
 * Created by lzy on 2016/12/21.
 */
public class GankResultBean {
    public boolean error;
    public List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }


}
