/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author plucas
 */

import java.io.*;

class User implements Data_lists, Serializable {

    private String id;
    private StringBuffer notifications;
    private transient DataInputStream in;
    private transient DataOutputStream out;
    int active;

    
    
    public User(String _id, DataInputStream _in, DataOutputStream _out) {
        id = _id;
        active = 1;
        notifications = new StringBuffer();
        in = _in;
        out = _out;
        users.add(this);
    }

    public String getID() {
        return id;
    }

    public StringBuffer getNot() {
        return notifications;
    }

    public void setStream(DataInputStream _in, DataOutputStream _out) {
        in = _in;
        out = _out;
    }

    public void setNot(StringBuffer msg) {
        notifications = msg;
    }

    public DataInputStream getINStream() {
        return in;
    }

    public DataOutputStream getOUTStream() {
        return out;
    }
}