package ro.example.android.data.remote;

import java.util.List;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import timber.log.Timber;

public class NewsWebService {

    private static final String API_KEY = "e414868ebc1941c28554f7404913cbf9";
    public static final String API_BASE_URL = "https://newsapi.org/v2/";

    private final NewsApi api;

    public NewsWebService() {
        // Prepare a logging interceptor to make sure the requests / responses are shown in logs
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(s -> Timber.d(s));
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Create the Retrofit API
        api = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                // Configure how to serialize / deserialize GSON
                .addConverterFactory(GsonConverterFactory.create())
                // Configure support for returning RxJava observables
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                // Setup a logging interceptor, for viewing requests / responses in logs
                .client(new OkHttpClient.Builder()
                        .addInterceptor(interceptor)
                        .build())
                .build()
                // Once everything is setup, build the API based on the defined interface
                .create(NewsApi.class);
    }


    public Single<List<Article>> fetchEverything(String query) {
        return api.fetchEverything(API_KEY, query)
                .map(articlesResponse -> articlesResponse.articles);
    }

    private interface NewsApi {

        @GET("everything")
        Single<ArticlesResponse> fetchEverything(@Query("apiKey") String apiKey,
                                                 @Query("q") String query);
    }
}
