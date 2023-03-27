package mapper;


import org.apache.ibatis.annotations.Param;
import pojo.History;

import java.util.List;


public interface HistoryMapper {

//    //1：n联系，查询每个系的学生
//    public List<Department> getAllDept_Studs();
//    //1：n联系，查询一个系下面的学生
//    public Department getAllDept_StudsById(String dept);

    /**
     * 查询用户最近n条历史记录
     * @param n 天数
     * @return 文章列表id，表示用户最近几天阅读的文章
     */
    List<Long> getUserHistoryLastNItem(@Param("limit") int n,@Param("userId") long userId);

    /**
     * 获取用户所有历史记录（用于去重）
     * @return
     */
     List<Long> getAllHistory(@Param("userId")Long userId);

    /**
     * 查询用户最近n条收藏和喜欢，用于筛选历史记录来计算
     * @param n
     * @param userId
     * @return
     */
    List<Long> getUserStarLastNItem(@Param("limit") int n,@Param("userId") long userId);

    List<Long> getUserLikeLastNItem(@Param("limit") int n,@Param("userId") long userId);

}
