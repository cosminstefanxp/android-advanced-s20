package ro.example.android.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.threeten.bp.LocalTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import ro.example.android.App;
import ro.example.android.data.NewsRepository;
import timber.log.Timber;

public class ProfileDetailsViewModel extends ViewModel {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private MutableLiveData<String> descriptionLiveData = new MutableLiveData<>("My initial VM description");
    private String text;

    private NewsRepository newsRepository;
    Disposable listenDisposable;
    Disposable loadDataDisposable;

    public ProfileDetailsViewModel() {
        newsRepository = App.get().getRepository();

        // Start listening to article updates - we will be notified with a new list of articles
        // every time something is written into the database
        listenDisposable = newsRepository.listenToAllAndroidArticles()
                // When the results come back, make sure we switch to main thread to handle them
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(articles -> {
                    Timber.d("Received response: %s", articles);

                    // Just keep a list of article names
                    List<String> articleNames = articles.stream()
                            .map(articleEntity -> articleEntity.title.substring(0, 8))
                            .collect(Collectors.toList());

                    descriptionLiveData.setValue("Received " + articles.size() + " articles at " +
                            dateTimeFormatter.format(LocalTime.now()) + ": " + articleNames);
                }, throwable -> {
                    // Handle the error
                    Timber.e(throwable, "Received error while fetching articles:");
                    descriptionLiveData.setValue("An error has occurred: " + throwable.getMessage());
                });
    }

    public void onButtonClicked() {
        Timber.d("Button was clicked!");

        // Start fetching articles and listen to know if the update operation has succeeded or not
        // TODO: Expose synchronization status via a spinner
        loadDataDisposable = newsRepository.fetchArticles()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    Timber.d("Data fetch completed");
                }, throwable -> {
                    Timber.e(throwable, "Data fetch failed");
                });
    }

    public String getTitle() {
        return "My VM title";
    }

    public LiveData<String> getDescription() {
        return descriptionLiveData;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        Timber.d("Text updated: %s", text);
        this.text = text;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // Make sure that we cancel any subscription / operation so it doesn't run after the view
        // model is not visible and has been cleared
        // TODO: Replace with a single `CompositeDisposable`
        listenDisposable.dispose();
        loadDataDisposable.dispose();
    }
}
