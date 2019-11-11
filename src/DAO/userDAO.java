package DAO;

import Enity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userDAO extends DBconn {

     Connection conn;

    {
        try {
            conn = super.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public User login(String username, String password){
        String sql = "select * from user where username=? and password=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
           ps.setString(1,username);
           ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setStatus(rs.getInt("status"));
                user.setHead(rs.getString("head"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void regsiter(User user){
        try {
            int i = 0;
            PreparedStatement pst = null;
            String sql = "insert into user (username, password, name,status, mail,UUID)values(?, ?, ?, ?, ?, ?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getName());
            pst.setInt(4,0);
            pst.setString(5, user.getMail());
            pst.setString(6, user.getUUID());
            i = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean QueryUsernameById( String username){
        String sql = "select * from user where username = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,username);
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }public boolean QuerymailById( String username){
        String sql = "select * from user where mail = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,username);
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
    public boolean QueryUUID( String uuid){
        String sql = "select * from user where UUID=?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,uuid);
            rs = pst.executeQuery();
            if(rs.next()){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void changStatus(String uuid){
        String sql = "UPDATE user SET status =1 WHERE UUID =? ";
        PreparedStatement pst = null;
        int i = 0;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,uuid);
            i =pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteUUID(String uuid){
        String sql = "update user SET UUID =null WHERE UUID =? ";
        PreparedStatement pst = null;
        int i = 0;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,uuid);
            i =pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User Querymail(String mail){
        String sql = "select * from user where mail=?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,mail);
            rs = pst.executeQuery();
            if(rs.next()){
                User user = new User();
                user.setName(rs.getString("name"));
                user.setMail(rs.getString("mail"));
                user.setUsername(rs.getString("username"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void changUUID(User user){
        try {
            int i = 0;
            PreparedStatement pst = null;
            String sql = "update user SET UUID =? WHERE mail=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, user.getUUID());
            pst.setString(2, user.getMail());
            i = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int changPWbyId(String UUID,String pw){
        String sql = "update user SET password =? WHERE UUID =? ";
        PreparedStatement pst = null;
        int i = 0;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,pw);
            pst.setString(2,UUID);

            i =pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    public void deleteUUIDbyusername(String username){
        String sql = "update user SET UUID =null WHERE UUID =? ";
        PreparedStatement pst = null;
        int i = 0;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,username);
            i =pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int insert(String sql, boolean b, String s, String myid, String fileName) {
        return 0;
    }

    public void uphead(String username,String head) {
        String sql = "update user SET head =? WHERE username =? ";
        PreparedStatement pst = null;
        int i = 0;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,head);
            pst.setString(2,username);
            i =pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changValue(String name, String password, String username) {
        String sql = "update user SET password =? , name=? WHERE username =? ";
        PreparedStatement pst = null;
        int i = 0;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,password);
            pst.setString(2,name);
            pst.setString(3,username);
            i =pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}