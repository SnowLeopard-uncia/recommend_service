package data;

import pojo.Article;
import pojo.History;
import pojo.HotRecommend;
import pojo.UserRecommend;

import java.util.List;

public class ConnectDataBase {

    private static ConnectDataBase instance;

    public static ConnectDataBase getInstance(){
        if (instance==null){
            instance=new ConnectDataBase();
        }
        return instance;
    }

    public List<Long> getAllUserId(){
        RecommendService.getInstance().init();
        List<Long> list = RecommendService.getInstance().getAllUserId();
        RecommendService.getInstance().destory();
        return list;
    }

    protected List<Article> getArticleLastNItemByTag(String tag, int n){
        RecommendService.getInstance().init();
        List<Article> list = RecommendService.getInstance().getArticleLastNItemByTag( tag,  n);
        RecommendService.getInstance().destory();
        return list;
    }

    protected List<Long> getUserHistoryLastNItem(int limit,long userId){
        RecommendService.getInstance().init();
        List<Long> list = RecommendService.getInstance().getUserHistoryLastNItem(limit, userId);
        RecommendService.getInstance().destory();
        return list;
    }

    protected List<Long> getUserStarLastNItem(int limit,long userId){
        RecommendService.getInstance().init();
        List<Long> list = RecommendService.getInstance().getUserStarLastNItem(limit, userId);
        RecommendService.getInstance().destory();
        return list;
    }

    protected List<Long> getUserLikeLastNItem(int limit,long userId){
        RecommendService.getInstance().init();
        List<Long> list = RecommendService.getInstance().getUserLikeLastNItem(limit, userId);
        RecommendService.getInstance().destory();
        return list;
    }


    protected void insertPersonalArticle(List<UserRecommend> recommends){
        RecommendService.getInstance().init();
        RecommendService.getInstance().insertPersonalArticle(recommends);
        RecommendService.getInstance().destory();
    }

    protected void insertHotArticle(List<HotRecommend> recommends){
        RecommendService.getInstance().init();
        RecommendService.getInstance().insertHotArticle(recommends);
        RecommendService.getInstance().destory();
    }

    protected List<Article> getAllArticle(){
        RecommendService.getInstance().init();
       List<Article> list= RecommendService.getInstance().getAllArticle();
        RecommendService.getInstance().destory();
        return list;
    }

    protected List<Article> selectByArticleIds(List<Long> ids){
        RecommendService.getInstance().init();
        List<Article> list= RecommendService.getInstance().selectByArticleIds(ids);
        RecommendService.getInstance().destory();
        return list;
    }

    public List<Article> selectRandom(int n){
        RecommendService.getInstance().init();
        List<Article> list= RecommendService.getInstance().selectRandom(n);
        RecommendService.getInstance().destory();
        return list;
    }

    public List<Article> selectTagRandom(String tag,int n){
        RecommendService.getInstance().init();
        List<Article> list= RecommendService.getInstance().selectTagRandom(tag,n);
        RecommendService.getInstance().destory();
        return list;
    }

    public List<Long> getAllHistory(Long userId){
        RecommendService.getInstance().init();
        List<Long> list= RecommendService.getInstance().getAllHistory(userId);
        RecommendService.getInstance().destory();
        return list;
    }
}
