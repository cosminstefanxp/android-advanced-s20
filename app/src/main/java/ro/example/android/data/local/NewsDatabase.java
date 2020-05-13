package ro.example.android.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ArticleEntity.class}, version = 1)
public abstract class NewsDatabase extends RoomDatabase {
    public abstract ArticleDao articleDao();
}
