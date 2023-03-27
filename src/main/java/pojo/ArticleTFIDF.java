package pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
@Data
@NoArgsConstructor
public class ArticleTFIDF {

    long articleId;
    List<Map.Entry<String, Double>> itemList;

    public ArticleTFIDF(long articleId, List<Map.Entry<String, Double>> itemList) {
        this.articleId = articleId;
        this.itemList = itemList;
    }
}
