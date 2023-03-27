package data;

import pojo.*;
import util.CalculateHot;
import util.SimilarityUtil;
import util.TextPreProcess;

import java.text.DecimalFormat;
import java.util.*;

public class RecommendCalculate {

    static List<ArticleTFIDF> textList;
    static List<ArticleVector> tfidfVectorMatrix = new ArrayList<>();
    static DecimalFormat df = new DecimalFormat("#######0.00000");
    static List<String> dicList;

    /**
     * 生成用户的个性化推荐列表
     */
    public static void generatePersonalRecommend(){
        //构建字典和所有文章的TFIDF值
        generateDictAndTFIDF();
        //构建TFIDF矩阵
        generateTFIDFMatrix();

        List<Long> userIdList = ConnectDataBase.getInstance().getAllUserId();
        System.out.println("userIdList:"+userIdList);
        for (int i = 0; i < userIdList.size(); i++) {
            generateUserRecommend(userIdList.get(i));
        }
    }

    public static void generateUserRecommend(Long userId){
        //选取时间最近25条History的ID
        List<Long> articleIdList = ConnectDataBase.getInstance().getUserHistoryLastNItem(25,userId);
        List<Long> historyList = ConnectDataBase.getInstance().getAllHistory(userId);
        if (historyList.size()==0){
            return;
        }
        //选取时间最近25条某个用户的Like的ArticleId
        List<Long> likeLastNItem = ConnectDataBase.getInstance().getUserLikeLastNItem(25,userId);
        //选取时间最近25条某个用户的star的ArticleId
        List<Long> starLastNItem = ConnectDataBase.getInstance().getUserStarLastNItem(25,userId);
       int likeScore = 1;
       int starScore = 2;
       List<ArticleScore> scoreList = new ArrayList<>();
        for (int i = 0; i < historyList.size(); i++) {
            long scoreCount = 1L;
            ArticleScore score = new ArticleScore(historyList.get(i), scoreCount);
            if (likeLastNItem.contains(historyList.get(i))){
                scoreCount=scoreCount+likeScore;
            }
            if (starLastNItem.contains(historyList.get(i))){
                scoreCount=scoreCount+starScore;
            }
            score.setScore(scoreCount);
            scoreList.add(score);
        }
        //对历史文章排序
        Collections.sort(scoreList, new Comparator<ArticleScore>() {
            @Override
            public int compare(ArticleScore o1, ArticleScore o2) {
                if(o1.getScore()<o2.getScore()){
                    return 1;
                }else if (Objects.equals(o1.getScore(), o2.getScore())){
                    return 0;
                }else {
                    return -1;
                }
            }
        });
        List<Long> calculateList = new ArrayList<>();
        for (ArticleScore articleScore:scoreList) {
            calculateList.add(articleScore.getScore());
        }
        System.out.println("scoreList: = "+scoreList.toString());

        if (calculateList.size()>10){
            calculateList = calculateList.subList(0,10);
        }

        System.out.println("calculateList: = "+calculateList.toString());
 // 对历史记录的收藏喜欢进行计算排序选最高的十条来推荐，若没有收藏和喜欢，则选择时间最近十条× 随机选择十条
        List<UserRecommend> userRecommends = new ArrayList<>();
        //复制一个矩阵副本，去重历史记录(换了新的方法）
//        List<ArticleVector> tfidfVectorCopiedList = new ArrayList<ArticleVector>(Arrays.asList(new ArticleVector[tfidfVectorMatrix.size()]));
//        Collections.copy(tfidfVectorCopiedList, tfidfVectorMatrix);
//  根据历史记录进行去重得到新的计算矩阵
        List<ArticleVector> tfidfVectorCopiedList = new ArrayList<ArticleVector>();
        for (ArticleVector matrix : tfidfVectorMatrix) {
            if (calculateList.size() == 0) {
                tfidfVectorCopiedList.add(matrix);
            } else {
                for (int j = 0; j < calculateList.size(); j++) {
                    if (calculateList.get(j) == matrix.getArticleId()) {
                        calculateList.remove(j);
                        break;
                    } else if (j==calculateList.size()-1) {
                        tfidfVectorCopiedList.add(matrix);
                    }
                }
            }

        }
        for (Long articleId :
                articleIdList) {
//            根据id获取对应文章的TFIDF的矩阵的一项
            ArticleVector articleVector = new ArticleVector();
            for (ArticleVector vectorMatrix : tfidfVectorMatrix) {
                if (articleId == vectorMatrix.getArticleId()) {
                    articleVector = vectorMatrix;
                }
            }
            double[] vector = articleVector.getTfidfVector();
            System.out.println("vector"+vector.toString());
            //存储相似度
            List<ArticleSimilarity> similarityList = new ArrayList<>();
            //在去重之后的矩阵里面比较相似度
            for (ArticleVector value : tfidfVectorCopiedList) {
                double similarity = SimilarityUtil.calculateVectorSimilarity(vector,
                        value.getTfidfVector());
                similarityList.add(new ArticleSimilarity(value.getArticleId(), similarity));
            }
            System.out.println("getSimilarity: "+similarityList.get(0).getSimilarity()+"id:"+similarityList.get(0).getArticleId());

            Collections.sort(similarityList, new Comparator<ArticleSimilarity>() {
                @Override
                public int compare(ArticleSimilarity o1, ArticleSimilarity o2) {
                    return Double.compare(o2.getSimilarity(), o1.getSimilarity());
                }
            });
            //每篇文章取前20相似的(去掉第一个是1的）
            similarityList = similarityList.subList(1,21);

            for (int i = 0; i < similarityList.size(); i++) {
                UserRecommend userRecommend = new UserRecommend(userId,similarityList.get(i).getArticleId(),similarityList.get(i).getSimilarity());
                userRecommends.add(userRecommend);
            }
        }

//        Collections.sort(userRecommends, new Comparator<UserRecommend>() {
//            @Override
//            public int compare(UserRecommend o1, UserRecommend o2) {
//                return (int) (o2.getRecommendValue()-o1.getRecommendValue()); //降序
//            }
//        });
        ConnectDataBase.getInstance().insertPersonalArticle(userRecommends);
    }

    /**
     * 科学或者技术的热门推荐列表
     * @param tag “科学”或者“技术”
     */
    public static void generateHotRecommend(String tag){
        List<Article> articleList = ConnectDataBase.getInstance().getArticleLastNItemByTag(tag,500);
        List<HotRecommend> hotRecommendList = new ArrayList<>();
        for (Article article :
                articleList) {

            System.out.println(article.toString());
            Date date = article.getPubTime();
            if (date==null){
                date=new Date();
            }
            int day = differentDaysByDate(new Date(),date);

            double hotValue=CalculateHot.calculateHot(article.getLikes(),article.getStars(),article.getViews(),day);
            HotRecommend hotRecommend = new HotRecommend(tag,article.getArticleId(),hotValue);
            hotRecommendList.add(hotRecommend);
        }
        //热度排序取前200
        Collections.sort(hotRecommendList, new Comparator<HotRecommend>() {
            @Override
            public int compare(HotRecommend o1, HotRecommend o2) {
                return (int) (o2.getHotValue()-o1.getHotValue()); //降序
            }
        });
        ConnectDataBase.getInstance().insertHotArticle(hotRecommendList.subList(0,200));
    }

    /**
     * 构建字典和所有文章的TFIDF值
     */
    protected static void generateDictAndTFIDF(){
        List<Article> articleList = ConnectDataBase.getInstance().getAllArticle();
        List<ArticleText> articleStringList = new ArrayList<>();
        for (Article article:articleList){ //把每篇文章标题关键词摘要都拼接在一起
            String text = article.getTitle()+article.getKeyword()+article.getSummary();
            articleStringList.add(new ArticleText(article.getArticleId(),text));
        }

        //构建字典
        List<String> dicTempList = new ArrayList<>();
        for (ArticleText text:articleStringList){
            //分词
            String splitWord = TextPreProcess.SplitWord(text.getText());
            String[] words = splitWord.split(" ");
            dicTempList.addAll(Arrays.asList(words));
        }
        //去重
        Set<String> dicSet = new HashSet<>(dicTempList);
         dicList = new ArrayList<>(dicSet);
//        System.out.println(dicList);
//        System.out.println(dicList.size());

         textList = new ArrayList<>(); //存储所有每一篇文章的词和词频
        for (ArticleText text:articleStringList){
            //分词
           String splitWord = TextPreProcess.SplitWord(text.getText());
            String[] words = splitWord.split(" ");
            //全文总词数
            double totalWordCount = words.length;
           //得到分词后每个词语和每个词语出现的次数
            List<Map.Entry<String, Double>> countWord = TextPreProcess.CountWord(splitWord);
            List<Map.Entry<String, Double>> itemList = new ArrayList<>(); //每篇文章的词和词频
            for (Map.Entry<String, Double> entry : countWord) {
                Double tf = entry.getValue() / totalWordCount;
                entry.setValue(tf);
                itemList.add(entry); //将map中的元素放入list中
            }
            textList.add(new ArticleTFIDF(text.getArticleId(),itemList));
        }
        //这是所有文章的TFIDF的值！
        for (ArticleTFIDF entry : textList) {
            for (Map.Entry<String, Double> each : entry.getItemList()) {
                String key = each.getKey();
                Double idf = calculateIDF(key, textList);
                Double tf_idf = each.getValue() * idf;
                each.setValue(Double.valueOf(df.format(tf_idf)));
            }
        }
//        for (int i = 0; i < 25; i++) {
//
//            List<Map.Entry<String,Double>> m = textList.get(i);
//            m.sort(new Comparator<Map.Entry<String, Double>>() {
//                @Override
//                public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
//                    return o2.getValue().compareTo(o1.getValue());
//                }
//            });
//            System.out.println(m);
//        }
    }

    /**
     * 计算单篇文章的TFIDF的值（或许可以直接拿？反正可以用id索引了）
     * @param article
     */
    protected static void calculateSingleArticleTFIDF(Article article){
        String text = article.getTitle()+article.getKeyword()+article.getSummary();
        String splitWord = TextPreProcess.SplitWord(text);
        String[] words = splitWord.split(" ");
        //全文总词数
        double totalWordCount = words.length;
        //得到分词后每个词语和每个词语出现的次数
        List<Map.Entry<String, Double>> countWord = TextPreProcess.CountWord(splitWord);
        for (Map.Entry<String, Double> entry : countWord) {
            Double tf = entry.getValue() / totalWordCount;
            Double idf = calculateIDF(entry.getKey(), textList);
            entry.setValue(tf*idf);
        }
    }

    //计算IDF值
    protected static double calculateIDF(String key, List<ArticleTFIDF> textList) {
        try {
            double count = 0.0; //计算词在全部文档里面出现过在几个文档

            for (ArticleTFIDF entry : textList) {
                for (Map.Entry<String, Double> item : entry.getItemList()) {
                    if (key.equals(item.getKey())) {
                        count++;
                        break;
                    }
                }
            }
            double idf = Math.log(textList.size() / (count + 1));
            return idf;
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    /**
     * 构建TFIDF矩阵
     */
    protected static void generateTFIDFMatrix(){
        int size = dicList.size();
        for (ArticleTFIDF item:textList){
            double[] vector = new double[size];
            //把词搞出来
            Map<String, Double> map = new HashMap<>();
            for (Map.Entry<String, Double> entry : item.getItemList()) {
                map.put(entry.getKey(), entry.getValue());
            }
            Set<String> keySet = map.keySet();

            for (int i = 0; i < dicList.size(); i++) {
                if (keySet.contains(dicList.get(i))){
                    vector[i] = map.get(dicList.get(i));
                }
                else {
                    vector[i]=0.0;
                }
            }
            tfidfVectorMatrix.add(new ArticleVector(item.getArticleId(),vector));
        }
//        System.out.println("横向向量："+tfidfVectorMatrix.get(0));
//        System.out.println("纵向："+tfidfVectorMatrix.size());
//        System.out.println("横向："+tfidfVectorMatrix.get(0).getTfidfVector().length);

    }

    /**
     * 计算天数
     * @param date1
     * @param date2
     * @return
     */
    protected static int differentDaysByDate(Date date1,Date date2)
    {
      return (int) ((date1.getTime() - date2.getTime()) / (1000*3600*24));
    }
}
