package co.tiagoaguiar.netflixremake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.tiagoaguiar.netflixremake.model.Category;
import co.tiagoaguiar.netflixremake.model.Movie;
import co.tiagoaguiar.netflixremake.util.CategoryTask;
import co.tiagoaguiar.netflixremake.util.ImageDownloaderTask;

public class MainActivity extends AppCompatActivity implements CategoryTask.CategoryLoader {

  private MainAdapter mainAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    RecyclerView recyclerView = findViewById(R.id.recycler_view_main);

    List<Category> categories = new ArrayList<>();

    mainAdapter = new MainAdapter(categories);
    recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    recyclerView.setAdapter(mainAdapter);

    CategoryTask categoryTask = new CategoryTask(this);
    categoryTask.setCategoryLoader(this);
    categoryTask.execute("https://tiagoaguiar.co/api/netflix/home");
  }

  @Override
  public void onResult(List<Category> categories) {
    mainAdapter.setCategories(categories);
    mainAdapter.notifyDataSetChanged();
  }

  private static class MovieHolder extends RecyclerView.ViewHolder {

    final ImageView imageViewCover;

    public MovieHolder(@NonNull View itemView, final OnItemClickListener onItemClickListener) {
      super(itemView);
      imageViewCover = itemView.findViewById(R.id.image_view_cover);
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          onItemClickListener.onClick(getAdapterPosition());
        }
      });
    }

  }

  private static class CategoryHolder extends RecyclerView.ViewHolder {

    TextView textViewTitle;
    RecyclerView recyclerViewMovie;

    public CategoryHolder(@NonNull View itemView) {
      super(itemView);
      textViewTitle = itemView.findViewById(R.id.text_view_title);
      recyclerViewMovie = itemView.findViewById(R.id.recycler_view_movie);
    }

  }

  private class MainAdapter extends RecyclerView.Adapter<CategoryHolder> {

    private List<Category> categories;

    private MainAdapter(List<Category> categories) {
      this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      return new CategoryHolder(getLayoutInflater().inflate(R.layout.category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
      Category category = categories.get(position);
      holder.textViewTitle.setText(category.getName());
      holder.recyclerViewMovie.setAdapter(new MovieAdapter(category.getMovies()));
      holder.recyclerViewMovie.setLayoutManager(new LinearLayoutManager(getBaseContext(), RecyclerView.HORIZONTAL, false));
    }

    @Override
    public int getItemCount() {
      return categories.size();
    }

    void setCategories(List<Category> categories) {
      this.categories.clear();
      this.categories.addAll(categories);
    }

  }

  private class MovieAdapter extends RecyclerView.Adapter<MovieHolder> implements OnItemClickListener {

    private final List<Movie> movies;

    private MovieAdapter(List<Movie> movies) {
      this.movies = movies;
    }

    @Override
    public void onClick(int position) {
      if (movies.get(position).getId() <= 3) {
        Intent intent = new Intent(MainActivity.this, MovieActivity.class);
        intent.putExtra("id", movies.get(position).getId());
        startActivity(intent);
      }
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = getLayoutInflater().inflate(R.layout.movie_item, parent, false);
      return new MovieHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
      Movie movie = movies.get(position);
      new ImageDownloaderTask(holder.imageViewCover).execute(movie.getCoverUrl());
    }

    @Override
    public int getItemCount() {
      return movies.size();
    }

  }

  interface OnItemClickListener {
    void onClick(int position);
  }

}
