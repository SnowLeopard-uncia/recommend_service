package util;


public class CalculateHot {

    //热度公式
    public static double calculateHot(long likesNum, long starsNum, long viewsNum, int day){
        return (likesNum * 0.6 + starsNum * 0.3 + viewsNum * 0.1) * 10 / (1 + day / 72);
    }
}
