package com.creativa.mytodoapp.api;

import java.util.List;

public class Response {

    private String more;
    private List<ListElement> results;

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public List<ListElement> getResults() {
        return results;
    }

    public void setResults(List<ListElement> results) {
        this.results = results;
    }
}
