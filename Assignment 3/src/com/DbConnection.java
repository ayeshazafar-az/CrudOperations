package com;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbConnection {

    private String dbURL = "jdbc:mysql://localhost:3306/user";
    private String username = "root";
    private String password = "";
    private Connection connection;

    DbConnection(){
        dbConnection();
    }

    public void dbConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, username, password);
            if (connection != null) {
                System.out.println("Success");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertRecord(String fName, String lName, String email, String password, int age, String gender, String address) {
        try {
            String sqlQuery = "INSERT INTO user_signup(fName,lName,  email, password,  age,  gender,  address) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, fName);
            preparedStatement.setString(2, lName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, String.valueOf(age));
            preparedStatement.setString(6, gender);
            preparedStatement.setString(7, address);
            int noOfRowsInserted = preparedStatement.executeUpdate();
            if (noOfRowsInserted > 0) {
                System.out.println(noOfRowsInserted + " rows inserted!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void verifyStudent(String email, String password){
        try {
            String sqlQuery = "SELECT * FROM user_signup WHERE email=? and password=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            boolean recordExist = preparedStatement.execute();
            if (recordExist) {
                System.out.println("Successfully Logged In");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllRecords() {
        List<Student> students  = new ArrayList<>();
        try {
            String sqlQuery = "SELECT * FROM user_signup ;";
            Statement preparedStatement = connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(sqlQuery);

            while (resultSet.next()){
                Student s = new Student();
                s.setfName(resultSet.getString("fName"));
                s.setlName(resultSet.getString("lName"));
                s.setEmail(resultSet.getString("email"));
                s.setAge(resultSet.getInt("age"));
                s.setPassword(resultSet.getString("password"));
                s.setGender(resultSet.getString("gender"));
                s.setAddress(resultSet.getString("address"));

                students.add(s);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public int deleteRecord(String email) {
        int row=0;
        try{
            String sqlQuery = "DELETE from user_signup WHERE email=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, email);
            row=preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }
}



