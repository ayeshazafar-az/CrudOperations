package com;

import javax.faces.bean.ManagedBean;

import java.util.List;

@ManagedBean(name = "student")
public class StudentMB {
    Student user;
    DbConnection conn;

    public Student getUser() {
        return user;
    }


    public void setUser (Student user) {
        this.user = user;

    }

    public StudentMB() {

        user=new Student();

        conn=new DbConnection();

    }

    public void setStudent(Student user) {
        this.user = user;
    }

    public void register () {
        conn.insertRecord (user.getfName(),user.getlName(),user.getEmail(),user.getPassword(),user.getAge(),user.getGender(),user.getAddress());
    }

    public void login(String email, String password){
        conn.verifyStudent(email,password);
    }
    public List<Student> allRecords(){
        return conn.getAllRecords();
    }

    public void DeleteData(String email){
        int rowsEffected=conn.deleteRecord(email);
        if(rowsEffected==1)
        {
            System.out.println("Record Deleted Successfully");
        }else{
            System.out.println("Unable to delete record");
        }
    }
}
