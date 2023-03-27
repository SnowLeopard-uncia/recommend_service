package pojo;


import java.util.List;

public class HotRecommend {

  private Long id;
  private String tag;
  private Long articleId;
  private Double hotValue;
  private java.sql.Timestamp createTime;
  private Boolean isPost;

  public HotRecommend() {
  }

  public HotRecommend(String tag, long articleId, double hotValue) {
    this.tag = tag;
    this.articleId = articleId;
    this.hotValue = hotValue;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }


  public long getArticleId() {
    return articleId;
  }

  public void setArticleId(long articleId) {
    this.articleId = articleId;
  }


  public double getHotValue() {
    return hotValue;
  }

  public void setHotValue(double hotValue) {
    this.hotValue = hotValue;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public void setId(Long id) {
    this.id = id;
  }

  public void setArticleId(Long articleId) {
    this.articleId = articleId;
  }

  public void setHotValue(Double hotValue) {
    this.hotValue = hotValue;
  }

  public Boolean getPost() {
    return isPost;
  }

  public void setPost(Boolean post) {
    isPost = post;
  }

  @Override
  public String toString() {
    return "HotRecommend{" +
            "id=" + id +
            ", tag='" + tag + '\'' +
            ", articleId=" + articleId +
            ", hotValue=" + hotValue +
            ", createTime=" + createTime +
            ", isPost=" + isPost +
            '}';
  }
}
