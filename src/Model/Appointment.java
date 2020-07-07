package Model;


import java.time.ZonedDateTime;
import java.util.Calendar;

public class Appointment {
    private int id;
    private ZonedDateTime start;
    private ZonedDateTime end;
    private int customerID;
    private String customerName;
    private int consultantID;
    private String type;

    public Appointment(int id, int customerID, String customerName, int consultantID, ZonedDateTime start, ZonedDateTime end, String type) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.customerID = customerID;
        this.customerName = customerName;
        this.consultantID = consultantID;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
//
//    public ZonedDateTime getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(ZonedDateTime createDate) {
//        this.createDate = createDate;
//    }

    public ZonedDateTime getStart() {
        return start;
    }

    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    public void setEnd(ZonedDateTime end) {
        this.end = end;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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