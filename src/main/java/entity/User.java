package entity;

public class User {
    private Integer id;

    private String username;

    private String password;

    private String sex;

    private Integer age;

    private String address;

    private String avatar;

    private String studyDirection;

    private String phone;

    private String email;

    private String motto;

    private Double account;

    private boolean degree;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(String username, String password, String sex, String studyDirection, String phone, String email) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.studyDirection = studyDirection;
        this.phone = phone;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getStudyDirection() {
        return studyDirection;
    }

    public void setStudyDirection(String studyDirection) {
        this.studyDirection = studyDirection == null ? null : studyDirection.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto == null ? null : motto.trim();
    }

    public Double getAccount() {
        return account;
    }

    public void setAccount(Double account) {
        this.account = account;
    }


    public boolean isDegree() {
        return degree;
    }

    public void setDegree(boolean degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", avatar='" + avatar + '\'' +
                ", studyDirection='" + studyDirection + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", motto='" + motto + '\'' +
                ", account=" + account +
                ", degree=" + degree +
                '}';
    }
}