package Model;

import java.util.Calendar;

public class Customer {
//    id, createDate, name, address, phone
    private int id;
    private Calendar createDate;
    private String name;
    private String address;
    private String phone;

    public Customer(int id, Calendar createDate, String name, String address, String phone) {
        this.id = id;
        this.createDate = createDate;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Calendar getcreateDate() {
        return createDate;
    }

    public void setcreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
