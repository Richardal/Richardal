package cn.idcast.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private static DataSource ds;
    //没有static就不会先被实例化，也就不会被调用

    static{
        try{
//  1. 加载配置文件
            Properties pro = new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(is);


//   2.初始化连接池对象
            ds = DruidDataSourceFactory.createDataSource(pro);
        }catch(IOException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
//  3.获取连接池对象
    public static DataSource getDataSource(){
        return ds;
    }

//    4.获取connection对象
    public static Connection getConnection() throws SQLException{
        return ds.getConnection();
    }

}
