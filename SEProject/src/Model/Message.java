package Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Date;

public class Message {
    private String message;
    private int fromUserID;
    private Date sending_time;

    public Message(String message, int fromUserID, Date sending_time){
        this.message = message;
        this.fromUserID = fromUserID;
        this.sending_time = sending_time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(int fromUserID) {
        this.fromUserID = fromUserID;
    }

    public Date getSending_time() {
        return sending_time;
    }

    public void setSending_time(Date sending_time) {
        this.sending_time = sending_time;
    }

    public ObjectNode toJSON(){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode returnJSON = mapper.createObjectNode();
        returnJSON.put("message", this.message);
        returnJSON.put("from_userID", this.fromUserID);
        returnJSON.put("sending_time", this.sending_time.toString());
        return returnJSON;
    }

}
