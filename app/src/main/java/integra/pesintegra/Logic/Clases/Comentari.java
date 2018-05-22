package integra.pesintegra.Logic.Clases;

import java.util.UUID;

public class Comentari {
    private String id;
    private String text;
    private String post_id;
    private String user_id;
    private String data;
    private String reply_id;

    public Comentari(String user_id, String text, String data, String post_id){
        this.user_id = user_id;
        this.text = text;
        this.data = data;
        this.post_id = post_id;
        setId();
    }

    private void setId() {
        this.id = UUID.randomUUID().toString();
    }

    public String getID(){
        return id;
    }
    public String gettext(){
        return text;
    }
    public String getuser_id(){
        return user_id;
    }
        public String getdata(){
        return data;
    }
    public String getreply(){
        return reply_id;
    }
    public String getPost_id(){
        return post_id;
    }
    public void setreply(String id){
        reply_id = id;
    }
    public void settext(String nou_text){
        text = nou_text;
    }
}
