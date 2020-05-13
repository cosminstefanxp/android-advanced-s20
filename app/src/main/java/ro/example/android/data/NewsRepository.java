package ro.example.android.data;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import ro.example.android.data.local.ArticleEntity;
import ro.example.android.data.local.NewsDatabase;
import ro.example.android.data.remote.ArticleDto;
import ro.example.android.data.remote.NewsWebService;

public class NewsRepository {

    private NewsWebService webService;
    private NewsDatabase database;

    public NewsRepository(NewsDatabase database) {
        this.database = database;
        webService = new NewsWebService();
    }

    public Observable<List<ArticleEntity>> listenToAllAndroidArticles() {
        return this.database.articleDao().loadArticles()
                // Make sure that the request above runs on a background thread, dedicated to I/O
                .subscribeOn(Schedulers.io());
    }

    public Completable fetchArticles() {
        // First fetch the articles from the webservice
        return webService.fetchEverything("Android")
                // ... then ...
                .flatMapCompletable((List<ArticleDto> articleDtos) -> {
                    // Convert models from DTO to Entity (can also be done using a for)
                    List<ArticleEntity> articleEntities = articleDtos.stream()
                            .map(articleDto -> articleDto.toEntity())
                            .collect(Collectors.toList());

                    // And save them into the database
                    return database.articleDao().insertAllArticles(articleEntities);
                })
                // Make sure that the request above runs on a background thread, dedicated to I/O
                .subscribeOn(Schedulers.io());
    }
}
