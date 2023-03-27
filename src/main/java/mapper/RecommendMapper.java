package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.Article;
import pojo.HotRecommend;
import pojo.PageQueryUtil;
import pojo.UserRecommend;

import java.util.List;

public interface RecommendMapper {

//    分页获取热门分类
    List<HotRecommend> getHotRecommendList(@Param("tag") String tag, @Param("start") int start, @Param("limit") int limit);

    int getTotalHotRecommend(PageQueryUtil pageUtil);

    //    分页获取个性化推荐
    List<UserRecommend> getPersonalRecommendList(@Param("userId") Long userId, @Param("start") int start, @Param("limit") int limit);

    int getTotalPersonalRecommend(PageQueryUtil pageUtil);

//    批量插入个性化推荐表
    int insertPersonalArticle(List<UserRecommend> recommend);

    //    批量插入热度推荐表
    int insertHotArticle(List<HotRecommend> recommend);

    //批量更新post状态
    int updatePersonalRecommend(List<Long> list);

    int updateHotRecommend(List<Long> list);


}
