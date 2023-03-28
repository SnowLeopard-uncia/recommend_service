import api.RecommendAPI;
import data.ConnectDataBase;
import data.RecommendCalculate;
import pojo.Article;
import pojo.ArticleVector;
import pojo.PageResult;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static data.RecommendCalculate.generatePersonalRecommend;
import static data.RecommendCalculate.generateUserRecommend;

public class Test {
    public static void main(String[] args) {


//        String t1 =
// "互联网作为近儿年发展最快的行业，一直在爆炸式的随者时代进化发展，其形式\n" +
//         "载体也山个人 PC 发展到手机、手表、家居、出行工具上，这科形式的转变让互联网\n" +
//         "滲入到了每一个人的生活中，不止载体的变化，五联网本身也在变化得更加智能，AI 人工智能地发展，让互联网能够根据每一个人得的同需求制定不同服务，推荐系统便可以通过分析每个人的行为偏差，精确的为每一个人推荐他可能感兴趣的物品。\n" +
//         "而传统的推荐算法中存在一些弊端，如何针对这些弊端做出改进，提升电影推荐系统的推荐精确度，是本系统主要攻克的难点。\n" +
//         "本文通过协同过滤和内容过滤算法，实现一个电影推荐系统，为用户推荐其可能\n" +
//         "喜欢的电影。电影推荐系统是一个以高可用、高性能、高淮确性为月标的推荐系统。\n" +
//         "通过分析基于用户的协同过滤算法中存在的数据稀疏问题和冷启动问题导致的推荐\n" +
//         "精确度下降，基于内存的协同过泥算法存在的仲展性差等问题，本文提出了基于聚类模型的协同过滤算法。算法基于奇异值分解技术对用户-评分的高维稀疏矩阵逃行\n" +
//         "分解填充，然后通过 K-means 算法对用户进行聚类，然后通过聚类集群为目标用户\n" +
//         "查找近邻用广，本文提出的基于额型的协同过滤算法高效的解让了数据稀疏性问题，算法基于模型的离线的处理模式也增强了算法的可伸缩性。针对传统相似性算法仔\n" +
//         "在的维度单一的问题，本文提出通过用户以及电影属性特征两个维度水进行相似度\n" +
//         "计算的解决方案让用户之间的相似度更加精确。\n" +
//         "电影推荐系统可拓展性强，各个模块可独立拆分优化，使用独立的硬件资源，符合系统设计低轉合、标准化的设计思路。两种推荐方式满足用户的需求，保证了系统稳定的性能和淮确性，能够精淮的为用户做定向电影推荐，并做了电影之问的相似型分析，为用户提供了更精确的电影信息。"
//   ;
//        String t2 =
//"随着互联网的发展和移动终端的迅速普及，人们的生活质量得到了极大的提高，网络上供用广观看的电影数量龙大、类型多样，但是用户需要花费大量时间寻我感兴趣的电影，导致电影资源的利用率过低。个性化推荐技术可以从海量信息中挖摒出有价值信息，并向用户提供个性化服务，可以很好的解决电影资源利用率低的问题。\n" +
//        "在个性化推荐系统中，推荐算法的好坏直接影响推荐系统的质量，推荐算法\n" +
//        "一直是学术研究的重点和热点。协同过滤算法应用广泛，但是基于用户的协同过\n" +
//        "泥算法存在推荐质量不高的问题，其主要原因有两点：第一，算法没有充分利用用户的特征信息：第二，用户评分短阵存在数据稀疏性的问题。针对上述问题，提出了基于用户综合相似度的协同过滤算法，算法将用户特征引入到相似度计算\n" +
//        "之中，将用户的相似度分为评分相似度和特征相似度，并且利用相似度的传递原理降低数据稀疏对推荐效果的影响。最后利用标准数据集，设计实验，给出实验结果并对实验结果进行分析，实验结果表明基于用户综合相似度的协同过滤算法能够提高推荐的准确度。\n" +
//        "在算法研究的基础上，本文设计和实现了一个电影个性化推荐系统，系统基手B/S模式，采用 MVC 设计模式的三层架构。首先，对电影推荐系统的进行需求进行分析：其次，在需求分析的基础上对系统进行总休框架设计、功能模块设计、推荐算法设计和数据库设计，给出了系统实现的技术路线和开发环境：然后，开发实现电影个性化推荐系统，给出了系统关键技术的实现，其中包括个人信息管理、电影信息管理和推荐引擎：最后，给出了系统的运行效果，并对系统进行功能测试和性能测试。"
//             ;
//        System.out.println("t1:" + t1 + "\n t2:" + t2);
//        String splitA = TextPreProcess.SplitWord(t1);
//        String splitB = TextPreProcess.SplitWord(t2);
//        System.out.println("分词" + "t1:" + splitA + "\n t2:" + splitB);
//
//        List<Map.Entry<String,Integer>> wordEntryA = TextPreProcess.CountWord(splitA);
//        List<Map.Entry<String,Integer>> wordEntryB = TextPreProcess.CountWord(splitB);
//
//        System.out.println("词频统计1："+wordEntryA);
//        System.out.println("词频统计2："+wordEntryB);
//
//        String mergeText = TextPreProcess.mergeText(wordEntryA,wordEntryB);
//
//        System.out.println("合并："+mergeText);
//
//        System.out.println("计算向量：v1:" + Arrays.toString(SimilarityUtil.calculateVector(wordEntryA, mergeText)));
//        System.out.println("计算向量：v2:" + Arrays.toString(SimilarityUtil.calculateVector(wordEntryB, mergeText)));
//        int[] v1 = SimilarityUtil.calculateVector(wordEntryA, mergeText);
//        int[] v2 = SimilarityUtil.calculateVector(wordEntryB, mergeText);
//
//        System.out.println("余弦相似度："+SimilarityUtil.calculateSimilarity(v1,v2));

//        CalculateSimilarity calculateSimilarity = new CalculateSimilarity(t1,t2);
//        System.out.println("余弦相似度:"+calculateSimilarity.getSimilarity());

        //使用方法
//    PageResult pageResult = RecommendAPI.getInstance().hotRecommend(TagEnum.TECHNOLOGY.getTag(), 1,2);
//    System.out.println(pageResult.getList());

//        List<User> list = Recommend.getInstance().getAllUser();
//        System.out.println(list);

//        RecommendService.getInstance().init();
//        List<Integer> list = RecommendService.getInstance().getAllHistory(8);
//        System.out.println(list);
//        RecommendService.getInstance().destory();

        //插入用法
//        List<UserRecommend> recommends = new ArrayList<>();
//        UserRecommend r1 = new UserRecommend(8,1);
//        UserRecommend r2 = new UserRecommend(8,2);
//        recommends.add(r1);
//        recommends.add(r2);
//        ConnectDataBase.getInstance().insertPersonalArticle(recommends);

//                List<HotRecommend> recommends = new ArrayList<>();
//        HotRecommend r1 = new HotRecommend("科学",8,0.356);
//        HotRecommend r2 = new HotRecommend("科学",9,0.365);
//        recommends.add(r1);
//        recommends.add(r2);
//        ConnectDataBase.getInstance().insertHotArticle(recommends);
//        RecommendCalculate.generatePersonalRecommend();
//        System.out.println(ConnectDataBase.getInstance().getAllArticle());

//        RecommendCalculate.generateDictAndTFIDF();
//        RecommendCalculate.generateTFIDFMatrix();
//        RecommendCalculate.generateHotRecommend("科学");
//        PageResult<Article> pageResult = RecommendAPI.getInstance().hotRecommend("技术",1,10);
//        System.out.println(pageResult.getList());

//        DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date star = dft.parse(new Date().toString());//开始时间
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        System.out.println(new Date().getTime());
//        PageResult pageResult = RecommendAPI.getInstance().hotRecommend("科学",2,10);
//        System.out.println(pageResult.getList());
//        List<Long> list = new ArrayList<>();
//        list.add(1L);
//        list.add(2L);
//       List<Article> l = ConnectDataBase.getInstance().selectByArticleIds(list);
//        System.out.println(l);
//        generatePersonalRecommend();
//        generateUserRecommend(2L);
//        PageResult pageResult = RecommendAPI.getInstance().userRecommend(2L,1,10);
//        System.out.println( pageResult.getList());
        /*


        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        List<Integer> list1 = new ArrayList<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(4);
        list1.add(5);
        List<Integer> newList = new ArrayList<>();
        for (Integer integer : list) {
            if (list1.size() == 0) {
                newList.add(integer);
            } else {
                for (int j = 0; j < list1.size(); j++) {
                    if (Objects.equals(list1.get(j), integer)) {
                        list1.remove(j);
                        break;
                    } else if (j==list1.size()-1){
                        newList.add(integer);
                    }
                }
            }
        }
        System.out.println(newList);

         */
//      PageResult<Article> pageResult =   RecommendAPI.getInstance().hotRecommend("科学",1,10);
//      for (Article article: pageResult.getList()){
//          System.out.println("article="+article.getArticle_id());
//      }
//    }
//        generatePersonalRecommend();
//        List<Long> userIdList = ConnectDataBase.getInstance().getAllUserId();
//        System.out.println("userIdList:"+userIdList);
//        for (int i = 0; i < userIdList.size(); i++) {
//                generatePersonalRecommend();
//        }
//    }


//        List<Long> userIdList = ConnectDataBase.getInstance().getAllUserId();
//        System.out.println("userIdList:"+userIdList);
//        for (int i = 0; i < userIdList.size(); i++) {
////            generateUserRecommend(userIdList.get(i));
//            test(userIdList.get(i));
//            System.out.println();
//        }
//        generatePersonalRecommend();
//        PageResult<Article> pageResult = RecommendAPI.getInstance().userRecommend(7L,1,10);
//        System.out.println(pageResult.getArticleList());
    }

//    public static void test(Long userId){
//        System.out.println(userId);
//    }

    /**
     * 从数据库中获取文章的信息包括：标题、摘要、关键词，并组成文本
     * @return 组好的文本
     */
//    public static String getArticleContent(){
//
//    }

}
