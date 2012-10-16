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

    private String name;


    
    
    public User(String _name) {
        name = _name;
        users.add(this);
    }

    public String getName() {
        return name;
    }

}