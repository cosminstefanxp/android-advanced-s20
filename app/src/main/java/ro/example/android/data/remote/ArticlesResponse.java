package ro.example.android.data.remote;

import java.util.List;

public class ArticlesResponse {
    public final String status;
    public final int totalResults;
    public final List<ArticleDto> articles;

    public ArticlesResponse(String status, int totalResults, List<ArticleDto> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }
}
