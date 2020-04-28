package Model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Message implements Serializable {

    private String User;
    private String Subject;
    private String Message;

    public Message() { }

    public Message(String user, String subject, String message) {
        User = user;
        Subject = subject;
        Message = message;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    @Override
    public String toString() {
        return "Module Name : " + this.Subject + "\n" + "Message : " + this.Message;
    }
}
