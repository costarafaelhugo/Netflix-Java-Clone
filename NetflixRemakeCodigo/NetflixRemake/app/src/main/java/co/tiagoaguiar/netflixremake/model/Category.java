package co.tiagoaguiar.netflixremake.model;

import java.util.List;

/**
 * Julho, 02 2019
 *
 * @author suporte@moonjava.com.br (Tiago Aguiar).
 */
public class Category {

  private String name;
  private List<Movie> movies;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Movie> getMovies() {
    return movies;
  }

  public void setMovies(List<Movie> movies) {
    this.movies = movies;
  }

}
