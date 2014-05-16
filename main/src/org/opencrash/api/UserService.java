package org.opencrash.api;

import org.opencrash.domain_objects.Register_user;

/**
 * Created by Fong on 14.05.14.
 */
public interface UserService {
    public boolean checkEmail(String email);

    public void addUser(Register_user registerUser);

    public Register_user getUser(String email, String hash);

    public Register_user getByid(Integer user_id);
}
