package pojo;

import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
public class ArticleText {
    long articleId;
    String text;

    public ArticleText(long articleId, String text) {
        this.articleId = articleId;
        this.text = text;
    }

}
