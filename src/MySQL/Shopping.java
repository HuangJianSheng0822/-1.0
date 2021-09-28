package MySQL;

import java.sql.*;
import java.util.ArrayList;

public class Shopping {

    public static void main(String[] args) {
        Shopping shopping=new Shopping();
//        try {
//            System.out.println(Arrays.toString(shopping.getTradeNews(1,null,null)));
//            //System.out.println(Arrays.toString(shopping.getTradeNews(-1,"XX",null)));
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
        //shopping.addToCart("1","西游记",true);
        //shopping.getShoppingCart("1");
       // shopping.isHave("1","AAA");
       // shopping.updateShoppingCard("1","AA");
    }
    /**
     * 通过id name store 查询商品信息
     * @param id
     * @param name
     * @param store
     * @return objects[0]=id objects[1]=kind objects[2]=name objects[3]=price objects[4]=image objects[5]=detail objects[6]=store
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Object[] getTradeNews(int id,String name,String store) throws ClassNotFoundException, SQLException {
        Object[] objects=new Object[7];
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/people?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String user="root";
        String password="1234";
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String sql=null;
        if (id>0){
            sql="select *  from commodity where commodity_id="+id;
        }
        if (name!=null){
            sql="select *  from commodity where commodity_name=\""+name+"\"";
        }
        if (store!=null){
            sql="select *  from commodity where commodity_store=\""+store+"\"";
        }
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            objects[0]=resultSet.getInt("commodity_id");
            objects[1]=resultSet.getString("commodity_kind");
            objects[2]=resultSet.getString("commodity_name");
            objects[3]=resultSet.getDouble("commodity_price");
            objects[4]=resultSet.getString("commodity_image");
            objects[5]=resultSet.getString("commodity_detail");
            objects[6]=resultSet.getString("commodity_store");
            //System.out.println(resultSet.getInt("commodity_id"));
        }
        resultSet.close();
        statement.close();
        connection.close();
        return objects;
    }

    /**
     *加入购物车
     * @param acc 账号
     * @param name 物品名称
     * @param isAdd 增加还是减少
     */
    public void addToCart(String acc,String name,boolean isAdd){
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
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            String sql=null;
            if (isAdd) {
                if (isHave(acc,name)){
                    sql = "UPDATE shopping_trolley set commodity_quantity=commodity_quantity+1 WHERE user_account=" + acc + " and commodity_name=\"" + name + "\"";
                }else {
                    sql="insert into shopping_trolley(user_account,commodity_name,commodity_quantity) VALUES ("+acc+","+"\""+name+"\",1)";
                }
            }else {
                //先获得商品数量，如果商品数量是1，则直接删除

                 sql = "UPDATE shopping_trolley set commodity_quantity=commodity_quantity-1 WHERE user_account=" + acc + " and commodity_name=\"" + name + "\"";
            }
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


    /**
     * 通过account返回购物车商品
     * @param account 账号
     * @return 结果集
     */
    public ArrayList<AccGoods> getShoppingCart(String account){
        ArrayList<AccGoods> arr=new ArrayList<>();
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
            String sql="select s.commodity_name ,commodity_price ,commodity_image,commodity_quantity ,user_account  " +
                    "from shopping_trolley as s INNER join commodity as x on s.commodity_name=x.commodity_name where user_account="+account;
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                //resultSet.getString("commodity_image"), resultSet.getString("commodity_name"), String.valueOf(resultSet.getDouble("commodity_price")), (resultSet.getInt("commodity_quantity")+"")
                arr.add(new AccGoods(resultSet.getString("commodity_image"),
                        resultSet.getString("commodity_name"),
                        String.valueOf(resultSet.getDouble("commodity_price")),
                        resultSet.getInt("commodity_quantity")));
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

    /**判断商品是否存在*/
    public boolean isHave(String account,String name){
        boolean flag=false;//默认不存在
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
            String sql="select s.commodity_name ,commodity_price ,commodity_image,commodity_quantity ,user_account  " +
                    "from shopping_trolley as s INNER join commodity as x on s.commodity_name=x.commodity_name where user_account="+account;
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
//                System.out.println(resultSet.getString("commodity_name"));
//                System.out.println(resultSet.getDouble("commodity_price"));
//                System.out.println(resultSet.getString("commodity_quantity"));
//                System.out.println(resultSet.getString("commodity_image"));
                if (resultSet.getString("commodity_name").equals(name)){
                    flag=true;//存在
                   // System.out.println("存在");
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
        try {
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return flag;
    }

    /**删除商品，需改进*/
    public void delectShoppingCard(String acc,String name) {
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
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            String sql="delete from shopping_trolley where user_account="+acc+" and commodity_name=\""+name+"\"";

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

    /**商品不存在*/
//    public void updateShoppingCard(String account,String name){
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        String url="jdbc:mysql://localhost:3306/people?useUnicode=true&characterEncoding=utf8&useSSL=true";
//        String username="root";
//        String password="1234";
//        Connection connection = null;
//        Statement statement =null;
//        try {
//            connection = DriverManager.getConnection(url, username, password);
//            statement = connection.createStatement();
//            String sql="insert into shopping_trolley(user_account,commodity_name,commodity_quantity) VALUES ("+account+","+"\""+name+"\",1)";
//            statement.executeUpdate(sql);
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }finally {
//            try {
//                statement.close();
//                connection.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//
//        }
//    }

}
