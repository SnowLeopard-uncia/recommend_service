package util;

import lombok.Data;


import java.util.List;
import java.util.Map;

@Data
public class CalculateSimilarity {

    public static double calculateSimilarity(String text1, String text2){
        //分词
        String splitA = TextPreProcess.SplitWord(text1);
        String splitB = TextPreProcess.SplitWord(text2);
        //统计词频
        List<Map.Entry<String,Double>> wordEntryA = TextPreProcess.CountWord(splitA);
        List<Map.Entry<String,Double>> wordEntryB = TextPreProcess.CountWord(splitB);
        //合并文本文档
        String mergeText = TextPreProcess.mergeText(wordEntryA,wordEntryB);
        //计算文档向量
        double[] v1 = SimilarityUtil.calculateVector(wordEntryA, mergeText);
        double[] v2 = SimilarityUtil.calculateVector(wordEntryB, mergeText);
        return SimilarityUtil.calculateVectorSimilarity(v1,v2);
    }
}
