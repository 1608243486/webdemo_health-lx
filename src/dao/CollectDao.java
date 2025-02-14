package dao;
import java.sql.*;
import utils.*;
import java.util.ArrayList;

public class CollectDao {
    private Connection conn=null;
    public void addCollect(String username,String articleName,String favorite){
                /* 在collect_table表中添加收藏关系*/
        String sql="INSERT INTO collect_table(USERNAME,ARTICLENAME,FAVORITE)values(?,?,?)";
        DbUtil dbUtil=new DbUtil();
        try {
            conn= dbUtil.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,articleName);
            preparedStatement.setString(3,favorite);
            preparedStatement.executeUpdate();
            System.out.println("收藏添加成功");
            preparedStatement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("收藏添加失败");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("收藏添加失败");
            throw new RuntimeException(e);
        }

    }/* 在collect_table表中添加收藏关系*/
    public void deleteCollection(String username,String articleName){
                /*在collect_table表中删除收藏关系*/
        DbUtil dbUtil=new DbUtil();
        String sql="delete from collect_table where USERNAME=? AND ARTICLENAME=?";
        try {
            conn= dbUtil.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,articleName);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("收藏删除失败");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("收藏删除失败");
            throw new RuntimeException(e);
        }
    }/*在collect_table表中删除收藏关系*/
    public boolean is_collect(String username,String articleName){
                    /* 收藏关系是否存在*/
        boolean is_collect_exist=false;
        DbUtil dbUtil=new DbUtil();
        try {
            conn= dbUtil.getConnection();
            String sql="select * from collect_table where USERNAME=username";
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()){
                String dataUSERNAME=resultSet.getString(1);
                String dataARTICLENAME=resultSet.getString(2);
                if(dataUSERNAME.equals(username)&&dataARTICLENAME.equals(articleName)){
                    is_collect_exist=true;
                    break;
                }
            }
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("收藏查找失败");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("收藏查找失败");
            throw new RuntimeException(e);
        }
        return is_collect_exist;
    }/* 收藏关系是否存在*/
    public ArrayList userCollections(String username,String favorite){
                    /*用户收藏列表查询*/
        ArrayList user_collections=new ArrayList();
        DbUtil dbUtil=new DbUtil();
        try {
            conn= dbUtil.getConnection();
            String sql="select * from collect_table where USERNAME=? and FAVORITE=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,favorite);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                user_collections.add(resultSet.getString("ARTICLENAME"));
            }
            preparedStatement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("用户收藏列表查询失败");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("用户收藏列表查询失败");
            throw new RuntimeException(e);
        }
        return user_collections;
    }/*用户收藏列表查询*/
    public ArrayList articleCollected(String articleName){
                    /*文章收藏列表查询*/
        ArrayList article_collections=new ArrayList();
        DbUtil dbUtil=new DbUtil();
        try {
            conn= dbUtil.getConnection();
            String sql="select * from collect_table where ARTICLENAME=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,articleName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                article_collections.add(resultSet.getString("USERNAME"));
            }
            preparedStatement.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("文章收藏列表查询失败");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("文章收藏列表查询失败");
            throw new RuntimeException(e);
        }
        return article_collections;
    }/*文章收藏列表查询*/
}
