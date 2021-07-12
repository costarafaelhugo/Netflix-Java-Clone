package co.tiagoaguiar.netflixremake.model;

/**
 * Julho, 02 2019
 *
 * @author suporte@moonjava.com.br (Tiago Aguiar).
 */
public class Movie {

  private int id;
  private String coverUrl;
  private String title;
  private String desc;
  private String cast;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getCast() {
    return cast;
  }

  public void setCast(String cast) {
    this.cast = cast;
  }

  public String getCoverUrl() {
    return coverUrl;
  }

  public void setCoverUrl(String coverUrl) {
    this.coverUrl = coverUrl;
  }

}
