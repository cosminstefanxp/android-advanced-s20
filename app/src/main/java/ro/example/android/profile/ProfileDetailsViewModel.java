package ro.example.android.profile;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.format.DateTimeFormatter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import ro.example.android.data.NewsRepository;
import timber.log.Timber;

public class ProfileDetailsViewModel extends ViewModel {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    //    private ObservableField<String> description = new ObservableField<>("My initial VM description");
    private MutableLiveData<String> descriptionLiveData = new MutableLiveData<>("My initial VM description");
    private String text;

    private NewsRepository newsRepository;

    public ProfileDetailsViewModel() {
        newsRepository = new NewsRepository();
    }

    @SuppressLint("CheckResult")
    public void onButtonClicked() {
        Timber.d("Button was clicked!");
        // Start loading articles
        newsRepository.fetchAllAndroidArticles()
                // When the results come back, make sure we switch to main thread to handle them
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(articles -> {
                    Timber.d("Received response: %s", articles);
                    descriptionLiveData.setValue("Received " + articles.size() + " articles at " +
                            dateTimeFormatter.format(LocalTime.now()));
                }, throwable -> {
                    Timber.e(throwable, "Received error while fetching articles:");
                });
    }

    public String getTitle() {
        return "My VM title";
    }

    public LiveData<String> getDescription() {
        return descriptionLiveData;
    }

//    public ObservableField<String> getDescription() {
//        return description;
//    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        Timber.d("Text updated: %s", text);
        this.text = text;
    }
}
