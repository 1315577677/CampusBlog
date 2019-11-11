package DAO;

import Enity.Diary;
import Enity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DiaryDAO extends DBconn {
    Connection conn;
    Diary diary = new Diary();
    {
        try {
            conn = super.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addDiary(Diary diary){
        try {
            int i = 0;
            PreparedStatement pst = null;
            String sql = "insert into diary (diary, path, date,writer,UUID,writer_name,text,headpath)values(?, ?, ?, ?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, diary.getDiary());
            pst.setString(2, diary.getPath());
            pst.setString(3, diary.getDate());
            pst.setString(4, diary.getWriter());;
            pst.setString(5,diary.getUUID());
            pst.setString(6,diary.getWritername());
            pst.setString(7,diary.getText());
            pst.setString(8,diary.getHeadpath());

            i = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Diary> getDiary(String writer){
        ArrayList<Diary> diarylist = new ArrayList<>();
        try {
            Connection conn = super.getConnection();
            String sql = "SELECT * FROM diary where writer=?";
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1,writer);
            rs = pst.executeQuery();
            while (rs.next()) {
                Diary diary =new Diary();
                diary.setWriter(rs.getString("writer"));
                diary.setPath(rs.getString("path"));
                diary.setDate(rs.getString("date"));
                diary.setDiary(rs.getString("diary"));
                diary.setUUID(rs.getString("UUID"));
                diary.setText(rs.getString("text"));
                diarylist.add(diary);
            }
            return diarylist;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diarylist;
    }

    public void deleteDairy(String username, String date) {
        int i=0;
        PreparedStatement pst = null;
        String sql = "delete from diary where writer=? and date = ?";
        try {
            pst=conn.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2,date);
            i=pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Diary> getAllDiray() {
        ArrayList<Diary> alldiarylist = new ArrayList<>();
        try {
            Connection conn = super.getConnection();
            String sql = "SELECT * FROM diary ";
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Diary diary =new Diary();
                diary.setWriter(rs.getString("writer"));
                diary.setPath(rs.getString("path"));
                diary.setDate(rs.getString("date"));
                diary.setDiary(rs.getString("diary"));
                diary.setWritername(rs.getString("writer_name"));
                diary.setUUID(rs.getString("UUID"));
                diary.setText(rs.getString("text"));
                diary.setHeadpath(rs.getString("headpath"));
                alldiarylist.add(diary);
            }
            return alldiarylist;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alldiarylist;
    }

    public Diary showDiary(String uuid) {
        String sql = "select * from diary where UUID=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,uuid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Diary user = new Diary();
                diary.setWriter(rs.getString("writer"));
                diary.setPath(rs.getString("path"));
                diary.setDate(rs.getString("date"));
                diary.setDiary(rs.getString("diary"));
                diary.setWritername(rs.getString("writer_name"));
                diary.setUUID(rs.getString("UUID"));
                diary.setText(rs.getString("text"));
                diary.setHeadpath(rs.getString("headpath"));
                return diary;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void changheadpath(String username, String head) {
        String sql ="UPDATE diary SET headpath=?  WHERE writer =?";
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

    public void changname(User user) {
        String sql ="UPDATE diary SET writer_name=?  WHERE writer =?";
        PreparedStatement pst = null;
        int i = 0;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,user.getName());
            pst.setString(2,user.getUsername());
            i =pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
