package Model;


import java.util.Calendar;

public class Appointment {
    private int id;
    private Calendar dateCreated;
    private Calendar time;
    private int customerID;
    private int consultantID;
    private String type;

    public Appointment(int id, Calendar dateCreated, Calendar time, int customerID, int consultantID, String type) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.time = time;
        this.customerID = customerID;
        this.consultantID = consultantID;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public Calendar getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Calendar dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getConsultantID() {
        return consultantID;
    }

    public void setConsultantID(int consultantID) {
        this.consultantID = consultantID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



}