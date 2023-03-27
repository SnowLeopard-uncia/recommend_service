package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.Article;

import java.util.List;

public interface ArticleMapper {
    /**
     * 选择xx分类以时间排序最近N条的文章（计算热度）
     * @param n 最近N条
     * @param tag xx分类
     * @return 列表
     */
    List<Article> getArticleLastNItemByTag(@Param("tag") String tag, @Param("count") int n);


//    根据ID在数据库中找到对应文章
    Article getArticleById(@Param("article_id") int n);

    List<Article> getAllArticle();

    List<Article> selectByArticleIds(@Param("ids") List<Long> ids);

    //某个tag的随机文章 n篇
   List<Article> selectTagRandom(@Param("tag")String tag,@Param("n")int n);

   //随机选取文章 n篇
   List<Article> selectRandom(@Param("n")int n);
}
