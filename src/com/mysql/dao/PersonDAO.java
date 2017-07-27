package com.mysql.dao;/**
 * Created by Coder on 2017/7/23.
 */

import com.mysql.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author
 * @create 2017-07-23 13:42
 **/

public class PersonDAO {

//    public boolean reduceMoney(Integer pid){
//        Connection conn = null;
//        PreparedStatement ps = null;
//        try{
//            conn = ConnectionManager.getInstance().getConnection();
//            //conn.setAutoCommit(false);
//            String sql = "UPDATE person SET money = money-1 WHERE id = ? AND money > 0;";
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1, pid);
//            ps.executeUpdate();
//            //conn.commit();
//            return true;
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally {
//            if(ps != null){
//                try {
//                    ps.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if(conn != null){
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return false;
//    }

    public boolean reduceMoney(Integer pid){
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        try{
            conn = ConnectionManager.getInstance().getConnection();
            conn.setAutoCommit(false);
            String sql1 = "SELECT * FROM person WHERE id = ? FOR UPDATE ;";
            ps1 = conn.prepareStatement(sql1);
            ps1.setInt(1, pid);
            ResultSet resultSet = ps1.executeQuery();
            float money = 0;
            if(resultSet.next()){
                money = resultSet.getFloat("money");
            }
            if(money>0){
                String sql2 = "UPDATE person SET money = ? WHERE id = ?;";
                ps2 = conn.prepareStatement(sql2);
                ps2.setFloat(1, money-1);
                ps2.setInt(2, 1);
                ps2.executeUpdate();
            }
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(ps1 != null){
                try {
                    ps1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps2 != null){
                try {
                    ps2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
