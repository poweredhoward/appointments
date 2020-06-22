package Model;


import java.util.Calendar;

public class Appointment {
    private int id;
    private Calendar createDate;
    private Calendar startTime;
    private int customerID;
    private int consultantID;
    private String type;

    public Appointment(int id, Calendar createDate, Calendar startTime, int customerID, int consultantID, String type) {
        this.id = id;
        this.createDate = createDate;
        this.startTime = startTime;
        this.customerID = customerID;
        this.consultantID = consultantID;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public Calendar getcreateDate() {
        return createDate;
    }

    public void setcreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public Calendar getstartTime() {
        return startTime;
    }

    public void setstartTime(Calendar startTime) {
        this.startTime = startTime;
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