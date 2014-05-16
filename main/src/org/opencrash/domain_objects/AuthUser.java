package org.opencrash.domain_objects;

/**
 * Created by Fong on 15.05.14.
 */
public class AuthUser {
    String username = "";
    String error = "";
    Integer user_id;
    boolean loginflag = false;

    public AuthUser() {
    }

    public String GetUser() {
        return username;
    }

    public void Login(String Username,Integer user_id) {
        loginflag = true;
        this.user_id = user_id;
        error = "";
        username = Username;
    }

    public void SetError(String Text) {
        error = Text;
    }

    public String GetError() {
        return error;
    }

    public String IsLogin() {
        if (loginflag)
            return "true";
        return "false";
    }

    public void Logout() {
        loginflag = false;
        username = "";
        error = "";
    }

    public Integer getUser_id() {
        return user_id;
    }
}

