package com.model;

import org.omg.CORBA.DATA_CONVERSION;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RunnableScheduledFuture;

public class BlahDAOjdbcImpl implements BlahDAO {
    private DataSource dataSource;

    public BlahDAOjdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Blah> getBlah(Blah blah) {
        Connection conn=null;
        PreparedStatement stmt=null;
        SQLException ex=null;
        List<Blah> blahs=null;
        try{
            conn=dataSource.getConnection();
            stmt=conn.prepareStatement("SELECT date,txt FROM blah WHERE name=?");
            stmt.setString(1,blah.getUsername());
            ResultSet rs=stmt.executeQuery();//获得查询结果
            blahs=new ArrayList<Blah>();
            while(rs.next()){
                blahs.add(new Blah(blah.getUsername(),rs.getString(2),rs.getTimestamp(1)));
            }
        }catch (SQLException e){
            ex=e;
        }finally {
            if(stmt!=null){
                try{
                    stmt.close();
                }catch (SQLException e){
                    if(ex==null){
                        ex=e;
                    }
                }
            }
            if(conn!=null){
                try{
                    conn.close();
                }catch (SQLException e){
                    if(ex==null){
                        ex=e;
                    }
                }
            }
            if(ex!=null){
                throw new RuntimeException(ex);
            }
            return blahs;
        }
    }

    @Override
    public void addBlah(Blah blah) {
        Connection conn=null;
        PreparedStatement stmt=null;
        SQLException ex=null;
        try{
            conn=dataSource.getConnection();
            stmt=conn.prepareStatement("INSERT INTO blah(name,date,txt) VALUES (?,?,?)");
            stmt.setString(1,blah.getUsername());
            stmt.setTimestamp(2,new Timestamp(blah.getDate().getTime()));
            stmt.setString(3,blah.getTxt());
            stmt.executeUpdate();//执行更新操作
        }catch (SQLException e){
            ex=e;
        }finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    if (ex != null) {
                        ex = e;
                    }
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    if (ex != null) {
                        ex = e;
                    }
                }
            }
            if (ex != null) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void deleteBlah(Blah blah) {
        Connection conn=null;
        PreparedStatement stmt=null;
        SQLException ex=null;
        try{
            conn=dataSource.getConnection();
            stmt=conn.prepareStatement("DELETE FROM blah WHERE date=?");
            stmt.setTimestamp(1,new Timestamp(blah.getDate().getTime()));
            stmt.executeUpdate();
        }catch (SQLException e){
            ex=e;
        }finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    if (ex != null) {
                        ex = e;
                    }
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    if (ex != null) {
                        ex = e;
                    }
                }
            }
            if (ex != null) {
                throw new RuntimeException(ex);
            }
        }
    }
}
