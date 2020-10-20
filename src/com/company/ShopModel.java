package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShopModel {
    private Connection connection;
    public ShopModel(){
        this.setConnection();/*
        try{
            Statement statement = connection.createStatement();
            String sqlQuery = "CREATE TABLE IF NOT EXISTS Orders" +
                    "(id INT UNSIGNED AUTO_INCREMENT, " +
                    "itemId INT, " +
                    "personId INT, " +
                    "howManyOrdered INT, " +
                    "howManyBought INT, " +
                    "PRIMARY KEY( id ))";
            statement.executeUpdate(sqlQuery);
            sqlQuery = "CREATE TABLE IF NOT EXISTS People" +
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
                    "VALUES (null,'Book',45,56,0)";
            statement.executeUpdate(sqlQuery);
            sqlQuery = "INSERT INTO Items "+
                    "VALUES (null,'TV',1000,10,0)";
            statement.executeUpdate(sqlQuery);
            sqlQuery = "INSERT INTO Items "+
                    "VALUES (null,'Paper',1,9999,0)";
            statement.executeUpdate(sqlQuery);
            connection.close();
        }catch (SQLException e){
            System.out.println(e);
        } */
    }
    public Object[][] getItemsData(){
        //this.setConnection();
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
            //connection.close();
        } catch (SQLException e) {
            System.out.println(e +"82");
        }
        Object[][] data2 = new Object[data.size()][];
        for (int i=0; i<data.size();i++) {
            List<Object> aList = data.get(i);
            data2[i] = aList.toArray(new Object[0]);
        }
        return data2;
    }
    public Object[][] getOrderData(int personId){
        //this.setConnection();
        ResultSet rs;
        String sqlQuery = "SELECT * FROM Orders WHERE personId = "+personId;
        List<List<Object>> data = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            rs= statement.executeQuery(sqlQuery);
            while(rs.next()){
                List<Object> row = new ArrayList();
                Item item = this.getItem(rs.getInt("itemId"));
                row.add(rs.getInt("id"));
                row.add(item.getName());
                row.add(item.getCost());
                row.add(rs.getInt("howManyOrdered"));
                row.add(rs.getInt("howManyBought"));
                data.add(row);
            }
            //connection.close();
        } catch (SQLException e) {
            System.out.println(e +"111");
        }
        Object[][] data2 = new Object[data.size()][];
        for (int i=0; i<data.size();i++) {
            List<Object> aList = data.get(i);
            data2[i] = aList.toArray(new Object[0]);
        }
        return data2;
    }
    public Person getPerson(String login , String password, String type){
        //this.setConnection();
        ResultSet rs;
        String sqlQuery = "SELECT id, firstName, lastName FROM People"+
                          " WHERE login = '"+login+"'"+
                          " AND password = '"+password+"'"+
                          " AND type = '"+type+"'";
        try{
            Statement statement = connection.createStatement();
            rs= statement.executeQuery(sqlQuery);
            if(rs.next()){
                Person person = new Person(rs.getInt("id"),rs.getString("firstName"),rs.getString("lastName"),type);
                //connection.close();
                return person;
            }
        } catch (SQLException e) {
            System.out.println(e +"136");
        }

        return null;
    }
    public Person getPerson(int id){
        //this.setConnection();
        ResultSet rs;
        String sqlQuery = "SELECT * FROM People"+
                " WHERE id = "+id;
        try{
            Statement statement = connection.createStatement();
            rs= statement.executeQuery(sqlQuery);
            if(rs.next()){
                Person person = new Person(rs.getInt("id"),rs.getString("firstName"),rs.getString("lastName"),rs.getString("type"));
                //connection.close();
                return person;
            }
        } catch (SQLException e) {
            System.out.println(e +"155");
        }

        return null;
    }
    public Item getItem(int id){
        //this.setConnection();
        ResultSet rs;
        String sqlQuery = "SELECT * FROM Items"+
                " WHERE id = "+id;
        try{
            Statement statement = connection.createStatement();
            rs= statement.executeQuery(sqlQuery);
            if(rs.next()){
                Item item =  new Item(rs.getInt("id"),rs.getString("name"),rs.getInt("cost"),rs.getInt("total"),rs.getInt("booked"));
                //connection.close();
                return  item;
            }
        } catch (SQLException e) {
            System.out.println(e +"174");
        }

        return null;
    }

    private void setConnection() {
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "ReytanPW99");
        } catch (ClassNotFoundException | SQLException e){
            System.out.println(e +"185");
        }
    }
    public boolean updateCellData(String tableName,String whatColumn, int changeToWhat, int whatRow ){
        //this.setConnection();
        String sqlQuery = "UPDATE " +tableName+ " SET "+whatColumn+" = '"+changeToWhat+"'"+
                " WHERE id = '"+whatRow+"'";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
            //connection.close();
        }catch (SQLException e){
            System.out.println(e +"197");
            return false;
        }
        return true;
    }
    public boolean changeOrderTable(int personId, int itemId  ,int howMuch)  {
        //this.setConnection();
        Order order = this.findOrder(personId , itemId);
        if(order==null){
            String sqlQuery = "INSERT INTO Orders (id, itemId, personId, howManyOrdered, howManyBought) "+
                    "VALUES ("+null+",'"+itemId+"','"+personId+"','"+howMuch+"','"+0+"');";
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(sqlQuery);
                //connection.close();
                return true;
            }catch (SQLException e){
                System.out.println(e +"213");
            }
            return false;
        }
        String sqlQuery = "UPDATE Orders SET howManyOrdered = '"+ (howMuch+order.getHowManyOrdered()) +"'"+
                " WHERE id = '"+order.getOrderId()+"'";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
            //connection.close();
        }catch (SQLException e){
            System.out.println(e +"225");
            return  false;
        }
        return true;
    }

    /**
     * ASD
     * @param personId qwerty
     * @param itemId asd
     * @return asd
     */
    private Order findOrder(int personId, int itemId){
        ResultSet rs;
        String sqlQuery = "SELECT * FROM Orders"+
                " WHERE personId = '"+personId+"'"+" AND itemId = '"+itemId+"'";
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sqlQuery);
            if(rs.next()){
                Item item = this.getItem(rs.getInt("itemId"));
                Person person = this.getPerson(personId);
                Order order = new Order(rs.getInt("id"),item, person,rs.getInt("howManyOrdered") , rs.getInt("howManyBought"));
                return order;
            }
        }catch (SQLException e){
            System.out.println(e +"244");
        }
        return null;
    }


}
