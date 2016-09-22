package com.examples.rehan.excluzo.Models;

/**
 * Created by rehan r on 02-09-2016.
 */
public class User {

    String userid,username,email,mobile,address,password;
    int age,gender;

    public User( String userid, String username, String email,String password, String mobile, int age, int gender,String address) {
        this.email = email;
        this.userid = userid;
        this.username = username;
        this.mobile = mobile;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.password = password;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (age != user.age) return false;
        if (gender != user.gender) return false;
        if (!userid.equals(user.userid)) return false;
        if (!username.equals(user.username)) return false;
        if (!email.equals(user.email)) return false;
        if (!mobile.equals(user.mobile)) return false;
        if (!address.equals(user.address)) return false;
        return password.equals(user.password);

    }

    @Override
    public int hashCode() {
        int result = userid.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + mobile.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + age;
        result = 31 * result + gender;
        return result;
    }
}
