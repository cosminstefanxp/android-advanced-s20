package ro.example.android.articles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ro.example.android.R;
import timber.log.Timber;

public class ArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_RED = 0;
    private static final int VIEW_TYPE_WHITE = 1;

    private List<Article> articles;

    void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (articles.get(position).isRed())
            return VIEW_TYPE_RED;
        else
            return VIEW_TYPE_WHITE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_RED:
                return new MainRedViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.item_layout_red, parent, false));
            case VIEW_TYPE_WHITE:
                return new MainWhiteViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.item_layout_white, parent, false));
            default:
                throw new IllegalStateException("Unhandled view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case VIEW_TYPE_RED:
                ((MainRedViewHolder) holder).bind(articles.get(position));
                break;
            case VIEW_TYPE_WHITE:
                ((MainWhiteViewHolder) holder).bind(articles.get(position));
                break;
            default:
                throw new IllegalStateException("Unhandled view type");
        }
    }

    private void onItemClicked(Article article) {
        Timber.d("Clicked on article: %s", article);
    }

    class MainWhiteViewHolder extends RecyclerView.ViewHolder {

        private TextView nameView;
        private TextView categoryView;

        MainWhiteViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.name);
            categoryView = itemView.findViewById(R.id.category);

            itemView.setOnClickListener(v -> {
                Article clickedArticle = articles.get(getAdapterPosition());
                onItemClicked(clickedArticle);
            });
        }

        void bind(Article article) {
            nameView.setText(article.getName());
            categoryView.setText(article.getCategory());
        }
    }

    class MainRedViewHolder extends RecyclerView.ViewHolder {

        private TextView nameView;

        MainRedViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.name);
        }

        void bind(Article article) {
            nameView.setText(article.getName());
        }
    }
}
