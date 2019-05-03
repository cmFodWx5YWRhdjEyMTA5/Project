package cu.cdac.myfinalapproyect;

public class Student {

    String name;
    String fatherName;
    String motherName;
    String phone;
    String address;
    String batch;

    public Student(String name, String fatherName, String motherName, String phone, String address, String batch) {
        this.name = name;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.phone = phone;
        this.address = address;
        this.batch = batch;
    }

    public Student() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }
}
