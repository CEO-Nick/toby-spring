package com.study.tobyspring.user.dao;

import com.study.tobyspring.user.domain.User;

import java.sql.*;
import javax.sql.DataSource;

public class UserDao {

//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        UserDao dao = new UserDao();
//
//        User user = new User();
//        user.setId("jiseon");
//        user.setName("하지선");
//        user.setPassword("gkwltjs");
//
//        dao.add(user);
//
//        System.out.println(user.getId() + " 등록 성공!!");
//
//        User user2 = dao.get(user.getId());
//        System.out.println(user2.getName());
//        System.out.println(user2.getPassword());
//
//        System.out.println(user2.getId() + " 조회 성공!!");
//    }

//    private SimpleConnectionMaker simpleConnectionMaker;    // UserDAO가 너무 특정 클래스에 의존적임

    private DataSource dataSource;

//    public UserDao(DataSource dataSource) {
////        simpleConnectionMaker = new SimpleConnectionMaker();
//        this.dataSource = dataSource;
//    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public void add(User user) throws ClassNotFoundException, SQLException {
//        Connection c = getConnection();
        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values (?, ?, ?)"
        );
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
//        Connection c = getConnection();
        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?"
        );
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }

//    private Connection getConnection() throws ClassNotFoundException, SQLException {
//        Class.forName("org.h2.Driver");
//        return DriverManager.getConnection(
//                "jdbc:h2:tcp://localhost/~/toby_spring", "sa", ""
//        );
//    }
}
