package ro.example.android.profile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import ro.example.android.BR;
import ro.example.android.databinding.ActivityProfileDetailsBinding;
import timber.log.Timber;

public class ProfileDetailsActivity extends AppCompatActivity {

    private ActivityProfileDetailsBinding binding;
    private ProfileDetailsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the view, setup the binding and set it on the activity
        binding = ActivityProfileDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialise the View Model
        viewModel = new ViewModelProvider(this).get(ProfileDetailsViewModel.class);

        // Bind the View Model to the View
        binding.setVariable(BR.viewModel, viewModel);
        binding.setLifecycleOwner(this); // Only needed if using LiveData, to allow fo observing

        // Version without data-binding
        // binding.title.setText(viewModel.getTitle());
        // binding.button.setOnClickListener(v -> viewModel.onButtonClicked());
        //
        // viewModel.getDescription().observe(this, description -> {
        //     binding.description.setText(description);
        // });
    }
}