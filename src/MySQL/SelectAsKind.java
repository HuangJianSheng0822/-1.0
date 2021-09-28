package MySQL;

import java.sql.*;
import java.util.ArrayList;

public class SelectAsKind {
    public static void main(String[] args) {
        SelectAsKind selectAsKind=new SelectAsKind();
        System.out.println(selectAsKind.getShoppingCart("6"));
    }
    public ArrayList<EveryGood> getShoppingCart(String kind){
        ArrayList<EveryGood> arr=new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url="jdbc:mysql://localhost:3306/people?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String username="root";
        String password="1234";
        Connection connection = null;
        Statement statement =null;
        ResultSet resultSet=null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            String sql="select * from commodity where commodity_kind="+"\""+kind+"\"";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                arr.add(new EveryGood(resultSet.getInt("commodity_id"),resultSet.getString("commodity_kind"),resultSet.getString("commodity_name"),resultSet.getString("commodity_price"),resultSet.getString("commodity_image"),resultSet.getString("commodity_detail"),resultSet.getString("commodity_store")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {

            try {
                resultSet.close();
                statement.close();
                connection.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return arr;
    }

}
