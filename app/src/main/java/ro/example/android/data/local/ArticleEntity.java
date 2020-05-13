package ro.example.android.data.local;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ArticleEntity {
    public final String title;
    public final String description;
    public final String author;
    @PrimaryKey
    @NonNull
    public final String url;

    public ArticleEntity(String title, String description, String author, String url) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.url = url;
    }
}
