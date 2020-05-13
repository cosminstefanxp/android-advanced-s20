package ro.example.android;

import android.app.Application;

import androidx.room.Room;

import com.jakewharton.threetenabp.AndroidThreeTen;

import ro.example.android.data.NewsRepository;
import ro.example.android.data.local.NewsDatabase;
import timber.log.Timber;

public class App extends Application {

    private static App INSTANCE;

    private NewsDatabase database;
    private NewsRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();

        // Prepare the instance
        INSTANCE = this;

        // General app config
        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());

        AndroidThreeTen.init(this);

        // Build singleton components, once, so they can be shared between components
        database = Room.databaseBuilder(this, NewsDatabase.class, "news")
                .build();
        repository = new NewsRepository(database);

        Timber.d("App has initialized...");
    }

    public static App get() {
        return INSTANCE;
    }

    public NewsDatabase getDatabase() {
        return database;
    }

    public NewsRepository getRepository() {
        return repository;
    }
}
