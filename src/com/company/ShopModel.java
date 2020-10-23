package com.company;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * takes care of all of the data in shop app
 * @author Daniel
 */
public class ShopModel {
    private Connection connection;

    public ShopModel(){
        this.setConnection(); /*
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
                    "howManyLeft INT, " +
                    "PRIMARY KEY( id ))";
            statement.executeUpdate(sqlQuery);
            sqlQuery = "INSERT INTO Items "+
                    "VALUES (null,'Banana',5,990)";
            statement.executeUpdate(sqlQuery);
            sqlQuery = "INSERT INTO Items "+
                    "VALUES (null,'Book',45,56)";
            statement.executeUpdate(sqlQuery);
            sqlQuery = "INSERT INTO Items "+
                    "VALUES (null,'TV',1000,10)";
            statement.executeUpdate(sqlQuery);
            sqlQuery = "INSERT INTO Items "+
                    "VALUES (null,'Paper',1,9999)";
            statement.executeUpdate(sqlQuery);
            //connection.close();
        }catch (SQLException e){
            System.out.println(e +"63");
        } */
    }

    /**
     * uses sql connection
     * @return all of the data from Items table as a 2D table
     */
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
                row.add(rs.getInt("howManyLeft"));
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

    /**
     * uses sql connection
     * @param personId person id that we want to get orders from
     * @return  data from Orders table, but only ones that were ordered by person known from personId, as a 2D table
     */
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

    /**
     * uses sql connection
     * @param login login that was typed in
     * @param password password that was typed in
     * @param type if a person trying to log in is customer of staff
     * @return Person in database that login and password match or null
     */
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

    /**
     * uses sql connection
     * @param id id of a person we search for
     * @return Person that id match or null
     */
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

    /**
     * uses sql connection
     * @param id id of an item we search for
     * @return Item from database or null
     */
    public Item getItem(int id){
        //this.setConnection();
        ResultSet rs;
        String sqlQuery = "SELECT * FROM Items"+
                " WHERE id = "+id;
        try{
            Statement statement = connection.createStatement();
            rs= statement.executeQuery(sqlQuery);
            if(rs.next()){
                Item item =  new Item(rs.getInt("id"),rs.getString("name"),rs.getInt("cost"),rs.getInt("howManyLeft"));
                //connection.close();
                return  item;
            }
        } catch (SQLException e) {
            System.out.println(e +"207");
        }

        return null;
    }
    public Order getOrder(int id){
        //this.setConnection();
        ResultSet rs;
        String sqlQuery = "SELECT * FROM Orders"+
                " WHERE id = "+id;
        try{
            Statement statement = connection.createStatement();
            rs= statement.executeQuery(sqlQuery);
            if(rs.next()){
                Order order = new Order(rs.getInt("id"),this.getItem(rs.getInt("itemId")),
                        this.getPerson(rs.getInt("personId")),rs.getInt("howManyOrdered"),rs.getInt("howManyBought"));
                //connection.close();
                return order;
            }
        } catch (SQLException e) {
            System.out.println(e +"227");
        }

        return null;
    }
    /**
     * wrapper for updating how many left column in Items table
     * @param changeToWhat what to change to in cell
     * @param itemId id of an item
     * @return true if successful , otherwise false
     */
    public boolean updateHowManyLeftItems(int changeToWhat , int itemId){
        String sqlQuery = "UPDATE Items SET howManyLeft = '"+changeToWhat+"' WHERE id = '"+itemId+"'";
        return this.executeUpdate(sqlQuery);
    }

    /**
     * wrapper for updating booked column in Orders table
     * @param changeToWhat what to change to in cell
     * @param orderId id of an item
     * @return true if successful , otherwise false
     */
    public boolean updateBookedOrder(int changeToWhat , int orderId){
        String sqlQuery = "UPDATE Orders SET howManyBooked = '"+changeToWhat+"' WHERE id = '"+orderId+"'";
        return this.executeUpdate(sqlQuery);
    }

    /**
     * wrapper for updating ordered column in Orders table
     * @param changeToWhat what to change to in cell
     * @param orderId id of an item
     * @return true if successful , otherwise false
     */
    public boolean updateOrderedOrder(int changeToWhat , int orderId){
        String sqlQuery = "UPDATE Orders SET howManyOrdered = '"+changeToWhat+"' WHERE id = '"+orderId+"'";
        return this.executeUpdate(sqlQuery);
    }
    public boolean deleteOrdersWithZeroBooked(){
        String sqlQuery = "DELETE FROM Orders WHERE howManyOrdered = '"+0 +"'";
        return  this.executeUpdate(sqlQuery);
    }

    /**
     * wrapper for inserting new order  Orders table
     * @param howMuch how much was ordered
     * @param personId what person makes order
     * @param itemId what item person orders
     * @return true if successful , otherwise false
     */
    public boolean insertOrder(int howMuch, int personId , int itemId){
        String sqlQuery = "INSERT INTO Orders (id, itemId, personId, howManyOrdered, howManyBought) "+
                "VALUES ("+null+",'"+itemId+"','"+personId+"','"+howMuch+"','"+0+"');";
        return this.executeUpdate(sqlQuery);
    }

    /**
     * uses sql connection , searches Orders table for order
     * @param personId id of a person who orderd
     * @param itemId item id of an item which was orderd
     * @return found order or null if nothing was found
     */
    public Order findOrder(int personId, int itemId){
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
    private boolean executeUpdate(String sqlQuery){
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

    private void setConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "ReytanPW99");
        } catch (ClassNotFoundException | SQLException e){
            System.out.println(e +"185");
        }
    }

}
