package ro.example.android.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import timber.log.Timber;

public class ProfileDetailsViewModel extends ViewModel {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private String text;

    //    private ObservableField<String> description = new ObservableField<>("My initial VM description");
    private MutableLiveData<String> descriptionLiveData = new MutableLiveData<>("My initial VM description");

    public String getTitle() {
        return "My VM title";
    }

    public LiveData<String> getDescription() {
        return descriptionLiveData;
    }

//    public ObservableField<String> getDescription() {
//        return description;
//    }

    public void onButtonClicked() {
        Timber.d("Button was clicked");
        descriptionLiveData.setValue("Button was clicked at: " + dateTimeFormatter.format(LocalDateTime.now()));
//        description.set("Button was clicked at: " + dateTimeFormatter.format(LocalDateTime.now()));
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        Timber.d("Text updated: %s", text);
        this.text = text;
    }
}
