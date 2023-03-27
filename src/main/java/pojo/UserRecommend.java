package pojo;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserRecommend {

  private long id;
  private long userId;
  private long articleId;
  private java.sql.Timestamp createTime;
  private long isPost;
  private double recommendValue;

  public UserRecommend(long userId, long articleId, double recommendValue) {
    this.userId = userId;
    this.articleId = articleId;
    this.recommendValue = recommendValue;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getArticleId() {
    return articleId;
  }

  public void setArticleId(long articleId) {
    this.articleId = articleId;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public long getIsPost() {
    return isPost;
  }

  public void setIsPost(long isPost) {
    this.isPost = isPost;
  }


  public double getRecommendValue() {
    return recommendValue;
  }

  public void setRecommendValue(double recommendValue) {
    this.recommendValue = recommendValue;
  }

}
