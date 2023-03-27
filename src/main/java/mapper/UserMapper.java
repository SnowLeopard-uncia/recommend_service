package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.Article;
import pojo.User;

import java.util.List;

public interface UserMapper {

    List<Long> getAllUser();



}
