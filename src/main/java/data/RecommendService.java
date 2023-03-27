package data;

import mapper.ArticleMapper;
import mapper.HistoryMapper;
import mapper.RecommendMapper;
import mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import pojo.*;
import util.MybatisUtil;

import java.util.List;

/**
 * 专门连数据库的操作
 */
public class RecommendService {

    private static RecommendService instance;
   private SqlSession session = null;
   private RecommendMapper recommendMapper = null;
   private HistoryMapper historyMapper=null;
   private ArticleMapper articleMapper =null;
    private UserMapper userMapper =null;

    public static RecommendService getInstance(){
        if (instance==null){
            instance=new RecommendService();
        }
        return instance;
    }

    public void init() {
        session = MybatisUtil.getSession();

        recommendMapper = session.getMapper(RecommendMapper.class);
        historyMapper=session.getMapper(HistoryMapper.class);
        articleMapper =session.getMapper(ArticleMapper.class);
        userMapper =session.getMapper(UserMapper.class);
    }

    /**
     * 获取热门推荐列表
     * @param tag
     * @param pageUtil
     * @return
     */
    public PageResult<HotRecommend> getHotRecommend(String tag,PageQueryUtil pageUtil) {
        List<HotRecommend> list = recommendMapper.getHotRecommendList(tag,(int)pageUtil.get("start"), pageUtil.getLimit());
        for (HotRecommend hotRecommend:list){
            System.out.println("articleID="+hotRecommend.getArticleId());
        }
        int total = recommendMapper.getTotalHotRecommend(pageUtil);
        PageResult<HotRecommend> pageResult = new PageResult<HotRecommend>(list, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    /**
     * 获取用户个性化推荐列表
     * @param userId
     * @param pageUtil
     * @return
     */
    public PageResult<UserRecommend> getUserRecommend(Long userId,PageQueryUtil pageUtil) {
        List<UserRecommend> list = recommendMapper.getPersonalRecommendList(userId,(int)pageUtil.get("start"), pageUtil.getLimit());
        int total = recommendMapper.getTotalPersonalRecommend(pageUtil);
        PageResult<UserRecommend> pageResult = new PageResult<UserRecommend>(list, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    public List<Article> getArticleLastNItemByTag(String tag, int n) {
        return articleMapper.getArticleLastNItemByTag(tag,n);
    }

    public List<Long> getAllUserId() {
        return userMapper.getAllUser();
    }
//
    public List<Long> getAllHistory(Long userID) {
        return historyMapper.getAllHistory(userID);
    }

    public List<Long> getUserHistoryLastNItem(int limit,long userID) {
        return historyMapper.getUserHistoryLastNItem(limit,userID);
    }
    public List<Long> getUserLikeLastNItem(int limit,long userID) {
        return historyMapper.getUserLikeLastNItem(limit,userID);
    }
    public List<Long> getUserStarLastNItem(int limit,long userID) {
        return historyMapper.getUserStarLastNItem(limit,userID);
    }

    public int insertPersonalArticle(List<UserRecommend> recommends) {
        return recommendMapper.insertPersonalArticle(recommends);
    }

    public int insertHotArticle(List<HotRecommend> recommends) {
        return recommendMapper.insertHotArticle(recommends);
    }

    public List<Article> getAllArticle(){
        return articleMapper.getAllArticle();
    }
    public void updateHotRecommend(List<Long> list){
       recommendMapper.updateHotRecommend(list);
    }

    public void updatePersonalRecommend(List<Long> list){
        recommendMapper.updatePersonalRecommend(list);
    }

    public List<Article> selectByArticleIds(List<Long> ids){
       return articleMapper.selectByArticleIds(ids);
    }

    public List<Article> selectRandom(int n){
        return articleMapper.selectRandom(n);
    }

    public List<Article> selectTagRandom(String tag,int n){
        return articleMapper.selectTagRandom(tag,n);
    }



    //    public List<Integer> getAllHistory(int userID) {
//        return historyMapper.getAllHistory(userID);
//    }

        /**
     * 从数据库中获取文章的信息包括：标题、摘要、关键词，并组成文本
     * @return 组好的文本
     */
//    public String getArticleContent(){
//        List<History> historyList = historyMapper.getHistoryLastNDay(7);
//
//    }

//for (String key : map.keySet()) {
//        if (list.contains(key)) {
//            map.put(key, map.get(key) + 1);
//        }
//    }


    public void destory () {
        MybatisUtil.closeSession(session);
    }
}
