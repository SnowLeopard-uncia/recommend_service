package pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleVector {
    long articleId;
    double[] tfidfVector;

    public ArticleVector(long articleId) {
        this.articleId = articleId;
    }

    public ArticleVector(long articleId, double[] tfidfVector) {
        this.articleId = articleId;
        this.tfidfVector = tfidfVector;
    }
}
