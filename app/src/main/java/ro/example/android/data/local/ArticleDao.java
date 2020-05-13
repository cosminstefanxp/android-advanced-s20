package ro.example.android.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

@Dao
public interface ArticleDao {
    // Load articles and all subsequent updates - we will be notified with a new list of articles
    // every time something is written into the database. Under the hood, Room listens to any change
    // to the table and re-runs the SQL query below and emits the result (a full list of articles)
    // on the observable
    @Query("SELECT * FROM ArticleEntity")
    public Observable<List<ArticleEntity>> loadArticles();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertAllArticles(List<ArticleEntity> articleEntities);
}
