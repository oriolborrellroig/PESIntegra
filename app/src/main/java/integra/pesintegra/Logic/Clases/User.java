package integra.pesintegra.Logic.Clases;

public class User {
    private String id;
    private String mail;
    private String username;
    private String password;
    private String tipus;
    private String data;

    public User(String s, String s1, String testUsername1, String testPassword, String testTipus, String testData) {
        this.id = s;
        this.mail = s1;
        this.username = testUsername1;
        this.password = testPassword;
        this.tipus = testTipus;
        this.data = testData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
