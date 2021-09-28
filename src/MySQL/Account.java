package MySQL;

import java.sql.*;

public class Account {

    public static void main(String[] args)  {

        Account account=new Account();
//        try {
//            //System.out.println(account.testAccount("1","1"));
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }

    /**
     *查询账号
     * @param acc 账号
     * @param pass 密码
     * @return 账号xx
     */
    public int testAccount(String acc,String pass) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/people?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String username="root";
        String password="1234";
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        String sql="select * from users where user_account="+acc;

        /*statement执行sql语句*/
        ResultSet resultSet = statement.executeQuery(sql);
        String x="?";
        int id=-1;
        while (resultSet.next()){
             id= resultSet.getInt("user_id");
             x= (String) resultSet.getObject("user_password");
        }
        resultSet.close();
        statement.close();
        connection.close();
        if (id>0){//账号存在
            if (x.equals(pass)){//密码正确
                id=1;
            }else {//密码错误
                id=0;
            }
        }else {//账号不存在
            id=-1;
        }
        return id;
    }


    /**注册账号*/
    public void indexAccount(String acc,String pass) throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url="jdbc:mysql://localhost:3306/people?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String username="root";
        String password="1234";
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        String sql="insert into users(user_account,user_password) values ("+acc+","+pass+")";
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }
}
