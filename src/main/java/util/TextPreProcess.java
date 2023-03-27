package util;
import com.huaban.analysis.jieba.JiebaSegmenter;
import pojo.Word;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class TextPreProcess {

    /**
     * 文章分词 去除停用词
     *
     * @param content 待分词文章
     * @return 分词+去除停用词之后的文本
     */
    public static String SplitWord(String content) {
        JiebaSegmenter jiebaSegmenter = new JiebaSegmenter();
        List<String> resultList = jiebaSegmenter.sentenceProcess(content);
        String resulString = "";
        String finalString="";
        //加载停用词，存储在List集合中
        try {
            List<String> stopWord = FileUtil.loadTestFile("stopword.txt");
            resultList.removeAll(stopWord); //过滤停用词
            resulString = String.join(" ", resultList); //将List集合转为字符串，中间用空格隔开
            String regEx = "\\pP|\\pS|\\d";
//             finalString = Pattern.compile("[\\d]").matcher(resulString).replaceAll("");
            finalString = Pattern.compile(regEx).matcher(resulString).replaceAll("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalString;
    }

    /**
     * 关键词词频统计（去重）
     * @param content 待统计分词后的文本，空格分开
     * @return 词和对应频率 (不降序)
     */
    public static List<Map.Entry<String, Double>> CountWord(String content) {
        Map<String, Double> wordCount = new HashMap<>();
        String[] words = content.split(" ");
        for (String word : words) {
            if (!word.equals("") && wordCount.containsKey(word)) {
                double num = wordCount.get(word);
                wordCount.put(word, num + 1.0);
            } else {
                wordCount.put(word, 1.0);
            }
        }
        //降序
        List<Map.Entry<String, Double>> wordCountSorted = new ArrayList<>(wordCount.entrySet());
//        Collections.sort(wordCountSorted, new Comparator<Map.Entry<String, Integer>>() {
//            @Override
//            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
//                return o2.getValue().compareTo(o1.getValue());
//            }
//        });
        return wordCountSorted;
    }

    /**
     *
     * @param content 用SplitWord 的结果
     * @param list 用CountWord 的结果
     * @return
     */
    public static List<Word> calculateTF(String content,List<Map.Entry<String, Integer>> list){
            List<Word> wordList = new ArrayList<>();
            int size = list.size(); //词的总数
        for (Map.Entry<String, Integer> entry : list) {
            String key = entry.getKey();
            double count = entry.getValue();
            double tf = count / size;
            Word word = new Word(key, tf);
            wordList.add(word);
        }
        return wordList;
    }


    /**
     * 合并文档
     *
     * @param text1 去重后的键值对
     * @param text2 去重后的键值对
     * @return 合并后的文档
     */
    public static String mergeText(List<Map.Entry<String, Double>> text1, List<Map.Entry<String, Double>> text2) {
        List<String> textWordList = new LinkedList<>();
        for (Map.Entry<String, Double> textWord : text1) {
            textWordList.add(textWord.getKey());
        }
        for (Map.Entry<String, Double> textWord : text2) {
            if (!textWordList.contains(textWord.getKey())) {
                textWordList.add(textWord.getKey());
            }
        }
        return String.join(" ", textWordList);
    }
}
