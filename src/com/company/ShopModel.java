package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShopModel {
    private Connection connection;
    public ShopModel(){/*
        this.setConnection();
        try{
            Statement statement = connection.createStatement();
            String sqlQuery = "CREATE TABLE IF NOT EXISTS Orders" +
                    "(id INT UNSIGNED AUTO_INCREMENT, " +
                    "itemId INT, " +
                    "personId INT, " +
                    "howManyOrdered INT, " +
                    "PRIMARY KEY( id ))";
            statement.executeUpdate(sqlQuery);
        }catch (SQLException e){
            System.out.println(e);
        } */
        /*
        try{
            Statement statement = connection.createStatement();
            String sqlQuery = "CREATE TABLE IF NOT EXISTS People" +
                    "(id INT UNSIGNED AUTO_INCREMENT, " +
                    "firstName varchar(255), " +
                    "lastName varchar(255), " +
                    "type varchar(255), " +
                    "login varchar(255), " +
                    "password varchar(255), " +
                    "PRIMARY KEY( id ))";
            statement.executeUpdate(sqlQuery);
            sqlQuery = "INSERT INTO People "+
                    "VALUES (null,'Maria','Zawadzka','Staff','login','haslo')";
            statement.executeUpdate(sqlQuery);
            sqlQuery = "INSERT INTO People "+
                    "VALUES (null,'Jan','Kowalski','Customer','login','haslo')";
            statement.executeUpdate(sqlQuery);

            sqlQuery = "CREATE TABLE IF NOT EXISTS Items" +
                    "(id INT UNSIGNED AUTO_INCREMENT, " +
                    "name varchar(255), " +
                    "cost INT, " +
                    "total INT, " +
                    "booked INT, " +
                    "PRIMARY KEY( id ))";
            statement.executeUpdate(sqlQuery);
            sqlQuery = "INSERT INTO Items "+
                    "VALUES (null,'Banana',5,990,0)";
            statement.executeUpdate(sqlQuery);
            sqlQuery = "INSERT INTO Items "+
                    "VALUES (null,'Book',45,56,10)";
            statement.executeUpdate(sqlQuery);
            sqlQuery = "INSERT INTO Items "+
                    "VALUES (null,'TV',1000,10,0)";
            statement.executeUpdate(sqlQuery);
            sqlQuery = "INSERT INTO Items "+
                    "VALUES (null,'Paper',1,9999,15)";
            statement.executeUpdate(sqlQuery);
            connection.close();
        }catch (SQLException e){
            System.out.println(e);
        }*/
    }
    Object[][] getData(){
        this.setConnection();
        ResultSet rs;
        String sqlQuery = "SELECT * FROM Items";
        List<List<Object>> data = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            rs= statement.executeQuery(sqlQuery);
            while(rs.next()){
                List<Object> row = new ArrayList();
                row.add(rs.getInt("id"));
                row.add(rs.getString("name"));
                row.add(rs.getInt("cost"));
                row.add(rs.getInt("total"));
                row.add(rs.getInt("booked"));
                data.add(row);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        Object[][] data2 = new Object[data.size()][];
        for (int i=0; i<data.size();i++) {
            List<Object> aList = data.get(i);
            data2[i] = aList.toArray(new Object[0]);
        }
        return data2;
    }
    Person getPerson(String login , String password, String type){
        this.setConnection();
        ResultSet rs;
        String sqlQuery = "SELECT firstName, lastName FROM People"+
                          " WHERE login = '"+login+"'"+
                          " AND password = '"+password+"'"+
                          " AND type = '"+type+"'";
        try{
            Statement statement = connection.createStatement();
            rs= statement.executeQuery(sqlQuery);
            if(rs.next()){
                return new Person(rs.getString("firstName"),rs.getString("lastName"),type);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }
    Item getItem(String name){
        this.setConnection();
        ResultSet rs;
        String sqlQuery = "SELECT * FROM Items"+
                " WHERE name = '"+name+"'";
        try{
            Statement statement = connection.createStatement();
            rs= statement.executeQuery(sqlQuery);
            if(rs.next()){
                return new Item(rs.getString("name"),rs.getInt("cost"),rs.getInt("total"),rs.getInt("booked"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }
    private void setConnection() {
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "ReytanPW99");
        } catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }
    public boolean changeCellData(int id, int booked ){
        this.setConnection();
        String sqlQuery = "UPDATE Items SET booked = '"+booked+"'"+
                " WHERE id = '"+id+"'";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
        }catch (SQLException e){
            return false;
        }
        return true;
    }


}
