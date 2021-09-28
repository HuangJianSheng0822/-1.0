package MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Goods {

    public void adds(String kind,String name,String price,String image,String detail,String store){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url="jdbc:mysql://localhost:3306/people?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String username="root";
        String password="1234";
        String sql= "insert into commodity(commodity_kind,commodity_name,commodity_price,commodity_image,commodity_detail,commodity_store) values("+"\""+kind+"\""+","+"\""+name+"\""+","+"\""+price+"\""+","+"\""+image+"\""+", "+"\""+detail+"\""+","+"\""+store+"\""+")";
        Connection connection = null;
        Statement statement =null;
        try {
             connection = DriverManager.getConnection(url, username, password);
             statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
}
