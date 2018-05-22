package integra.pesintegra.Logic.Clases;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

public class User {
    private String id;
    private String mail;
    private String username;
    private String tipus;
    private String data;
    private List<String> hiddenPosts;
    private List<String> interessos;

    public User (){
        hiddenPosts = new ArrayList<String>();
    }

    public User(String mail, String testTipus, String testData, String username) {
        setId();
        this.mail = mail;
        this.username = username;
        this.tipus = testTipus;
        this.data = testData;
        hiddenPosts = new ArrayList<String>();
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

    private void setId() {
        this.id = this.tipus + '_' + UUID.randomUUID().toString();
    }

    private void setUsernameFromEmail() {
        int index = this.mail.indexOf('@');
        this.username = this.mail.substring(0,index);
    }

    public List<String> getHiddenPosts(){
        return hiddenPosts;
    }

    private int isInHiddenList(String idPost){
        for(int i = 0; i < hiddenPosts.size(); ++i){
            if(hiddenPosts.get(i).equals(idPost)) return i;
        }
        return -1;
    }

    public boolean addHiddenPost(String idPost){
        if(isInHiddenList(idPost) < 0) {
            hiddenPosts.add(idPost);
            return true;
        }
        return false;
    }

    public boolean removeHiddenPost(String idPost){
        int r = isInHiddenList(idPost);
        if(r > -1){
            hiddenPosts.remove(r);
            return true;
        }
        return false;
    }

    public List<String> getInteressos(){

        return interessos;
    }

}
