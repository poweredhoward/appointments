package Model;

import java.util.Calendar;

public class Customer {
//    id, datecreated, name, address, phone
    private int id;
    private Calendar dateCreated;
    private String name;
    private String address;
    private String phone;

    public Customer(int id, Calendar dateCreated, String name, String address, String phone) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Calendar getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Calendar dateCreated) {
        this.dateCreated = dateCreated;
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
