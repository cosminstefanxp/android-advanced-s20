package ro.example.android.data.remote;

import java.util.List;

public class ArticlesResponse {
    public final String status;
    public final int totalResults;
    public final List<Article> articles;

    public ArticlesResponse(String status, int totalResults, List<Article> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }
}
