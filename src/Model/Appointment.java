package Model;


import java.util.Calendar;

public class Appointment {
    private int id;
    private Calendar createDate;
    private Calendar start;
    private Calendar end;
    private int customerID;
    private int consultantID;
    private String type;

    public Appointment(int id, int customerID, int consultantID, Calendar start, Calendar end, String type, Calendar createDate) {
        this.id = id;
        this.createDate = createDate;
        this.start = start;
        this.end = end;
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

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
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