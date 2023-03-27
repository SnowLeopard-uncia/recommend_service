package util;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


public class MybatisUtil {

   // 获取SqlSession
    public static SqlSession getSession(){
        SqlSession session=null;
        InputStream inputStream=null;
        try {
            //配置文件的路径
            String resource = "mybatis.xml";
            //加载配置文件，得到一个输入流
            inputStream = Resources.getResourceAsStream(resource);
            //获取MyBatis的Session工厂
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //通过session工厂获取到一个session
            session = sqlSessionFactory.openSession(true);  //true表示自动提交事务
            //调用session的查询集合方法
            return session;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

   //  关闭SqlSession
    public static void closeSession(SqlSession session){
        if(session!=null){
            session.close();
        }
    }
}
