package org.fundaciobit.pinbaladmin.back.utils;

/**
 * 
 * @author anadal
 *
 */
public class NormativaInfo {

  String normaLegal;
  String articles;
  String enllaz;

  /**
   * @param normaLegal
   * @param articles
   * @param enllaz
   */
  public NormativaInfo(String normaLegal, String articles, String enllaz) {
    super();
    this.normaLegal = normaLegal.trim();
    this.articles = articles.trim();
    this.enllaz = enllaz.trim();
  }

  public String getNormaLegal() {
    return normaLegal;
  }

  public String getArticles() {
    return articles;
  }

  public String getEnllaz() {
    return enllaz;
  }

  public void setNormaLegal(String normaLegal) {
    this.normaLegal = normaLegal;
  }

  public void setArticles(String articles) {
    this.articles = articles;
  }

  public void setEnllaz(String enllaz) {
    this.enllaz = enllaz;
  }

}
