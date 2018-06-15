package integra.pesintegra.Logic.Clases;

public class CommentReply {
    private String id;
    private String text;
    private String post_id;
    private String user_id;
    private String data;
    private boolean hasReply;
    private CommentReply reply;

    public CommentReply(String id, String text, String post_id, String user_id, String data){
        this.id = id;
        this.text = text;
        this.post_id = post_id;
        this.user_id = user_id;
        this.data = data;
        hasReply = false;
    }

    public String getId(){
        return id;
    }

    public String getText(){
        return text;
    }

    public String getPost_id(){
        return post_id;
    }

    public String getUser_id(){
        return user_id;
    }

    public String getData(){
        return data;
    }

    public void setHasReply(boolean reply){
        hasReply = reply;
    }

    public void setReply(CommentReply nou_reply){
        reply = nou_reply;
    }

    public CommentReply getReply() {
        return reply;
    }

    public boolean hasReply(){
        return hasReply;
    }
}
