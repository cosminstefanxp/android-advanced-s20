package ro.example.android.data;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import ro.example.android.data.remote.Article;
import ro.example.android.data.remote.NewsWebService;

public class NewsRepository {

    private NewsWebService webService;

    public NewsRepository() {
        webService = new NewsWebService();
    }

    public Single<List<Article>> fetchAllAndroidArticles() {
        return webService.fetchEverything("Android")
                // Make sure that the request above runs on a background thread, dedicated to I/O
                .subscribeOn(Schedulers.io());
    }
}
