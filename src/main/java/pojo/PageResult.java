package pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@NoArgsConstructor
public class PageResult<T> implements Serializable {
  //总记录数
    private int totalCount;
//    每页记录数
    private int pageSize;
//    总页数
    private int totalPage;
//    当前页数
    private int currPage;
//    列表数据
    private List<T> articleList;

    /**
     * 分页
     *
     * @param list       列表数据
     * @param totalCount 总记录数
     * @param pageSize   每页记录数
     * @param currPage   当前页数
     */
    public PageResult(List<T> list, int totalCount, int pageSize, int currPage) {
        this.articleList = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }
}