package Model;

import java.util.Calendar;

public class Customer {
//    id, createDate, name, address, phone
    private int id;
    private Calendar createDate;
    private String name;
    private String address;
    private int addressId;
    private String address2;
    private int cityId;
    private String city;
    private String postalCode;
    private int countryId;
    private String country;
    private String phone;


    public Customer(int id, Calendar createDate, String name,
                    String address, int addressId,
                    String address2, int cityId, String city,
                    String postalCode, int countryId, String country,
                    String phone) {
        this.id = id;
        this.createDate = createDate;
        this.name = name;
        this.address = address;
        this.addressId = addressId;
        this.address2 = address2;
        this.cityId = cityId;
        this.city = city;
        this.postalCode = postalCode;
        this.countryId = countryId;
        this.country = country;
        this.phone = phone;
    }

    public int getCustomerId(){
        return id;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String toString(){
        String str = String.format(
        "%s, %s, %s, %s, %s, %s, %s, %s, %s, %s ,%s, %s" , this.id,
        this.name,
        this.address,
        this.addressId,
        this.address2,
        this.cityId,
        this.city,
        this.postalCode,
        this.countryId,
        this.country,
        this.phone,
        this.createDate
        );

        return str;
    }


}
