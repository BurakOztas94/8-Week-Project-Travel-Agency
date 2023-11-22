package Model;

public class Book {

    private int id;
    private int room_id;
    private String note;
    private int price;
    private String startDate;
    private String endDate;
    private int child_visitors;
    private int adult_visitors;
    private String name;
    private String identityNo;
    private int age;
    private String phone;
    private String email;

    public Book(int id, int room_id, String note, int price, String startDate, String endDate,
                int child_visitors, int adult_visitors, String name, String identityNo, int age, String phone, String email) {
        this.id = id;
        this.room_id = room_id;
        this.note = note;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.child_visitors = child_visitors;
        this.adult_visitors = adult_visitors;
        this.name = name;
        this.identityNo = identityNo;
        this.age = age;
        this.phone = phone;
        this.email = email;

    }

    public Book(int room_id, String note, int price, String startDate, String endDate,
                int child_visitors, int adult_visitors, String name, String identityNo, int age, String phone, String email) {
        this.room_id = room_id;
        this.note = note;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.child_visitors = child_visitors;
        this.adult_visitors = adult_visitors;
        this.name = name;
        this.identityNo = identityNo;
        this.age = age;
        this.phone = phone;
        this.email = email;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getChild_visitors() {
        return child_visitors;
    }

    public void setChild_visitors(int child_visitors) {
        this.child_visitors = child_visitors;
    }

    public int getAdult_visitors() {
        return adult_visitors;
    }

    public void setAdult_visitors(int adult_visitors) {
        this.adult_visitors = adult_visitors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getidentityNo() {
        return identityNo;
    }

    public void setidentityNo(String identityNo
    ) {
        this.identityNo = identityNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
