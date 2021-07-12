package co.tiagoaguiar.netflixremake.model;

import java.util.List;

/**
 * Julho, 19 2019
 *
 * @author suporte@moonjava.com.br (Tiago Aguiar).
 */
public class MovieDetail {

  private final Movie movie;
  private final List<Movie> moviesSimilar;

  public MovieDetail(Movie movie, List<Movie> moviesSimilar) {
    this.movie = movie;
    this.moviesSimilar = moviesSimilar;
  }

  public Movie getMovie() {
    return movie;
  }

  public List<Movie> getMoviesSimilar() {
    return moviesSimilar;
  }

}
