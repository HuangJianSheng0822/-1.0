package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

/**
 * 录入5000条商品,禁止启动
 */
public class T1 {
    Random random=new Random();
    public void setRandom(){
        for (int i = 0; i < 5000; i++) {
            Random random=new Random();
            String strs[]=new String[6];
            T1 t1=new T1();
            t1.adds(t1.getKind(), t1.getName(random.nextInt(6)+6), t1.getPrice(), t1.getImage(), t1.getDetail(), t1.getStore());
        }
    }
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

    /**
     *
     * @param length
     * @return随机名称
     */
    public  String getName(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
    /**
     *
     * @param
     * @return随机类型
     */
    public  String getKind(){
        String[] str={"图书","手机","水果","男鞋","男装","食品","美妆护肤","玩具"};
        return str[random.nextInt(8)];
    }

    /**
     *
     * @param
     * @return随机价格
     */
    public  String getPrice(){
        return String.valueOf(String.format("%.2f",random.nextDouble()*100));
    }

    /**
     *
     * @return随机图片地址
     */
    public  String getImage(){
        return "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbenyouhuifile.it168.com%2Fforum%2F201212%2F01%2F175404ununn8c6nfu2ff3u.jpg&refer=http%3A%2F%2Fbenyouhuifile.it168.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1634639322&t=0622a07d47b00832c80e481845df795d";
    }

    /**
     *
     * @return随机详情
     */
    public  String getDetail(){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<random.nextInt(100)+10;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public  String getStore(){
        String[] str={"京东超市","天猫超市","苏宁易购","拼多多","识货","唯品会","咸鱼","得物"};
        return str[random.nextInt(8)];
    }

}
