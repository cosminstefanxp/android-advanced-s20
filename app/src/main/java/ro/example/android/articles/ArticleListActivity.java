package ro.example.android.articles;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ro.example.android.R;
import ro.example.android.core.BaseActivity;
import ro.example.android.databinding.ActivityArticlesBinding;

public class ArticleListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        ActivityArticlesBinding viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_articles);

        RecyclerView recyclerView = viewBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArticleAdapter adapter = new ArticleAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setArticles(ArticleKt.getTestArticles());
    }
}
