package util;

import java.util.List;
import java.util.Map;

public class SimilarityUtil {
    /**
     * 构建文章向量
     * @param entryList 文章的词和词频
     * @param mergeText 两篇文章的词
     * @return 文章的向量
     */
    public static double[] calculateVector(List<Map.Entry<String,Double>> entryList,String mergeText) {
       String[] mergeTextList = mergeText.split(" ");
        double[] textVector = new double[mergeTextList.length];
        double wordFrequency = 0.0;
        String word = "";

        for (Map.Entry<String, Double> wordEntry : entryList) {
            word = wordEntry.getKey();
            wordFrequency = wordEntry.getValue();
            int j = 0;
            while (j < mergeTextList.length) {
                if (word.equals(mergeTextList[j])) {
                    textVector[j] = wordFrequency;
                    break;
                } else {
                    j++;
                }
            }
        }
        return textVector;
    }


    /**
     * 向量余弦相似度计算 计算两个向量之间的余弦距离
     * @param v1 数组类型表示向量
     * @param v2 数组类型表示向量
     * @return 相似度精确到小数点后x位
     */
    public static double calculateVectorSimilarity(double[] v1, double[] v2) {
        int size = v1.length; //长度
        double num = 0; //两个向量之间的点积
        double den =1; //两个向量的模长之积
        double simVal = 0.0;

        double v1SquareSum = 0;
        double v2SquareSum = 0;

        for (int i = 0; i < size; i++) {
            double a = v1[i];
            double b = v2[i];
//        计算两个向量的乘积
            num = num + a * b;
//        向量中所有元素的平方和
            v1SquareSum = v1SquareSum +  Math.pow(a, 2);
            v2SquareSum = v2SquareSum +  Math.pow(b, 2);
        }
//        两个向量的模长的积
        double sqrtA =  Math.sqrt(v1SquareSum);
        double sqrtB =  Math.sqrt(v2SquareSum);
        den = sqrtA * sqrtB;
//        代入公式
        simVal = num / den;
        return simVal;
    }
}
