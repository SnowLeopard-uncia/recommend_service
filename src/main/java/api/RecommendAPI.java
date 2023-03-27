package api;

import data.ConnectDataBase;
import data.RecommendService;
import pojo.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 提供给后端
 */
public class RecommendAPI {
    private static RecommendAPI instance;

    public static RecommendAPI getInstance(){
        if (instance==null){
            instance=new RecommendAPI();
        }
        return instance;
    }

    /**
     * 初次推荐
     * @param tag
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageResult<Article> firstRecommend(String tag,int pageNum, int pageSize){
       List<Article> list =  ConnectDataBase.getInstance().selectTagRandom(tag,pageSize);
        return new PageResult(list,0,pageSize,0);
    }

    /**
     * 热门推荐
     * @param tag 科学或者技术
     * @param pageNum 第几页
     * @param pageSize 一页多大
     * @return
     */
    public PageResult<Article> hotRecommend(String tag, int pageNum, int pageSize){
        Map<String, Object> params = new HashMap<>(4);
        params.put("page", pageNum);
        params.put("limit", pageSize);
        RecommendService.getInstance().init();
        PageResult<HotRecommend> pageResult= RecommendService.getInstance().getHotRecommend(tag, new PageQueryUtil(params));
        if (pageResult.getArticleList().size()>0){
            List<Long> postId = new ArrayList<>();
//        System.out.println(pageResult.getList().get(0).getHotValue());
            for (int i = 0; i < pageResult.getArticleList().size(); i++) {
//            System.out.println(pageResult.getList().get(i).toString());
                postId.add(pageResult.getArticleList().get(i).getArticleId()) ;
            }
            System.out.println(postId);
//            RecommendService.getInstance().updateHotRecommend(postId);
            //根据id批量返回Article
            List<Article> resultList =RecommendService.getInstance().selectByArticleIds(postId);
            RecommendService.getInstance().destory();
            PageResult<Article> pageResultArticle = new PageResult<>();
            pageResultArticle.setArticleList(resultList);
            pageResultArticle.setCurrPage(pageResult.getCurrPage());
            pageResultArticle.setPageSize(pageResult.getPageSize());
            pageResultArticle.setTotalPage(pageResult.getTotalPage());
            pageResultArticle.setTotalCount(pageResult.getTotalCount());
            return pageResultArticle;
        }else {
           List<Article> list =  RecommendService.getInstance().selectTagRandom(tag, pageSize);
            RecommendService.getInstance().destory();
           return new PageResult(list,0,pageNum,0);
        }
    }

    /**
     * 用户个性化推荐
     * @param userId 用户id
     * @param pageNum 第几页
     * @param pageSize 一页多大
     * @return
     */
    public PageResult<Article> userRecommend(Long userId,int pageNum, int pageSize){
        Map<String, Object> params = new HashMap<>(4);
        params.put("page", pageNum);
        params.put("limit", pageSize);
        RecommendService.getInstance().init();
        PageResult<UserRecommend> pageResult= RecommendService.getInstance().getUserRecommend(userId, new PageQueryUtil(params));
        if (pageResult.getArticleList().size()>0){
            List<Long> postId = new ArrayList<>(); //根据articleid标记哪些已经发出去的还有根据id找文章
            for (int i = 0; i < pageResult.getArticleList().size(); i++) {
                postId.add(pageResult.getArticleList().get(i).getArticleId()) ;
            }
            //更新发送状态
            RecommendService.getInstance().updatePersonalRecommend(postId);
            //根据id批量返回Article
            List<Article> resultList =RecommendService.getInstance().selectByArticleIds(postId);
            RecommendService.getInstance().destory();
            PageResult<Article> pageResultArticle = new PageResult<>();
            pageResultArticle.setArticleList(resultList);
            pageResultArticle.setCurrPage(pageResult.getCurrPage());
            pageResultArticle.setPageSize(pageResult.getPageSize());
            pageResultArticle.setTotalPage(pageResult.getTotalPage());
            pageResultArticle.setTotalCount(pageResult.getTotalCount());
            return pageResultArticle;
        }else {
            List<Article> list =  RecommendService.getInstance().selectRandom(pageSize);
            RecommendService.getInstance().destory();
            return new PageResult<>(list,0,pageSize,0);
        }
    }

    /**
     * 每篇文章的相关推荐
     * @param articleId
     * @return
     */
//    public List<Article> articleRecommend(int articleId){
//        List<Article> articleList = new ArrayList<>();
//
//        return articleList;
//    }

}
