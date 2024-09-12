/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.User;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class UserDAO extends DAO {

    public UserDAO() {
        super();
    }

    public boolean checkLogin(User user) {
        
        String sql = "SELECT * FROM tbl_user WHERE username = ? AND password = ?;";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    if(rs.getString("username").equals(user.getUsername()) && rs.getString("password").equals(user.getPassword())) {
                        user.setPosition(rs.getString("position"));
                        user.setID(rs.getInt("ID"));
                        user.setFullname(rs.getString("fullname"));
                        return true; // Đăng nhập thành công
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // In ra thông tin lỗi để gỡ lỗi
        }
        return false; // Đăng nhập thất bại do lỗi hoặc không tìm thấy người dùng
    }

//    public boolean checkLogin(User user) {
//        try {
//            PreparedStatement ps = con.prepareStatement("Select * from tbluser WHERE username = ? and password = ?;");
//            ps.setString(1, user.getUsername());
//            ps.setString(2, user.getPassword());
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                user.setPosition(rs.getString("position"));
//                user.setID(rs.getInt("ID"));
//                user.setFullname(rs.getString("fullname"));
//                return true;
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return false;
//        }
//        return false;
//    }
}
