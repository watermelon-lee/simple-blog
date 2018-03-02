package com.model;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAOjdbcImpl implements AccountDAO {

    private DataSource dataSource;

    public AccountDAOjdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean isUserExisted(Account account) {
        Connection conn = null;
        PreparedStatement stmt = null;
        SQLException ex = null;
        boolean exist = false;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement("SELECT COUNT(1) FROM  account WHERE name=?");
            stmt.setString(1, account.getName());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                exist = (rs.getInt(1) == 1);//确认有无查询结果
            }
        } catch (SQLException e) {
            ex = e;
        } finally {//最后记得关闭连接
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    if (ex == null) {
                        ex = e;
                    }
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    if (ex == null) {
                        ex = e;
                    }
                }
            }
            if (ex != null) {
                throw new RuntimeException(ex);
            }
            return exist;
        }
    }

    @Override
    public void addAccount(Account account) {
        Connection conn = null;
        PreparedStatement stmt = null;
        SQLException ex = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement("INSERT INTO account VALUE (?,?,?)");
            stmt.setString(1, account.getName());//取得account中封装的信息更新数据库
            stmt.setString(2, account.getPassword());
            stmt.setString(3, account.getEmail());
            stmt.executeUpdate();//别忘了还要执行更新操作
        } catch (SQLException e) {
            ex = e;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    if (ex == null) {
                        ex = e;
                    }
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    if (ex == null) {
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
    public Account getAccount(Account account) {
        Connection conn = null;
        PreparedStatement stmt = null;
        SQLException ex = null;
        Account acc = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement("SELECT password,email FROM account WHERE name=?");
            stmt.setString(1, account.getName());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                acc = new Account(account.getName(), rs.getString(1), rs.getString(2));
            }
        } catch (SQLException e) {
            ex = e;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    if (ex == null) {
                        ex = e;
                    }
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    if (ex == null) {
                        ex = e;
                    }
                }
            }
            if(ex!=null){
                throw new RuntimeException(ex);
            }
        }
        return acc;
    }
}