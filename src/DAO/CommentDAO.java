package DAO;

import Enity.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentDAO extends DBconn {
    Connection conn;

    {
        try {
            conn = super.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addComment(Comment comment) {
        String sql = "insert into comment (writer,writerID,words,adress,UUID,date)values(?, ?, ?, ?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,comment.getWriter());
            preparedStatement.setString(2,comment.getWriterID());
            preparedStatement.setString(3,comment.getWords());
            preparedStatement.setString(4,comment.getAddress());
            preparedStatement.setString(5,comment.getUUID());
            preparedStatement.setString(6,comment.getData());
            int i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Comment> queryComment(Comment com) {
        ArrayList<Comment> diarylist = new ArrayList<>();
        try {
            Connection conn = super.getConnection();
            String sql = "SELECT * FROM comment where UUID=?";
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(sql);
            pst.setString(1,com.getUUID());
            rs = pst.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setWriter(rs.getString("writer"));
                comment.setData(rs.getString("date"));
                comment.setWords(rs.getString("words"));
                comment.setAddress(rs.getString("adress"));
                comment.setWriterID(rs.getString("writerID"));
                comment.setUUID(rs.getString("UUID"));
                diarylist.add(comment);
            }
            return diarylist;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diarylist;
    }
}
