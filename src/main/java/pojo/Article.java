package pojo;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Article {

  private long articleId;
  private String classify;
  private String title;
  private String author;
  private java.sql.Date pubTime;
  private String keyword;
  private String summary;
  private String link;
  private long status;
  private long views;
  private long likes;
  private long stars;


  public long getArticleId() {
    return articleId;
  }

  public void setArticleId(long articleId) {
    this.articleId = articleId;
  }


  public String getClassify() {
    return classify;
  }

  public void setClassify(String classify) {
    this.classify = classify;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }


  public java.sql.Date getPubTime() {
    return pubTime;
  }

  public void setPubTime(java.sql.Date pubTime) {
    this.pubTime = pubTime;
  }


  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }


  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }


  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public long getViews() {
    return views;
  }

  public void setViews(long views) {
    this.views = views;
  }


  public long getLikes() {
    return likes;
  }

  public void setLikes(long likes) {
    this.likes = likes;
  }


  public long getStars() {
    return stars;
  }

  public void setStars(long stars) {
    this.stars = stars;
  }

  @Override
  public String toString() {
    return "Article{" +
            "articleId=" + articleId +
            ", classify='" + classify + '\'' +
            ", title='" + title + '\'' +
            ", author='" + author + '\'' +
            ", pubTime=" + pubTime +
            ", keyword='" + keyword + '\'' +
            ", summary='" + summary + '\'' +
            ", link='" + link + '\'' +
            ", status=" + status +
            ", views=" + views +
            ", likes=" + likes +
            ", stars=" + stars +
            '}';
  }
}
