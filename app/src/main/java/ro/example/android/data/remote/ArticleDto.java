package ro.example.android.data.remote;

import com.google.gson.annotations.SerializedName;

import ro.example.android.data.local.ArticleEntity;

public class ArticleDto {
    public final String title;
    @SerializedName("description")
    public final String description;
    public final String author;
    public final String url;
    public final Source source;

    public ArticleDto(String title, String description, String author, String url, Source source) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.url = url;
        this.source = source;
    }

    public static class Source {
        public final String id;
        public final String name;

        public Source(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public ArticleEntity toEntity() {
        return new ArticleEntity(title, description, author, url);
    }
}
