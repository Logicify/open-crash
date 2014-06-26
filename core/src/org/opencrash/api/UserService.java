package org.opencrash.api;

import org.opencrash.domain_objects.Register_user;

import java.util.List;

/**
 * Created by Fong on 14.05.14.
 */
public interface UserService {
    public boolean checkEmail(String email);

    public void addUser(Register_user registerUser);

    public Register_user getUser(String email, String hash);

    public Register_user getByid(Integer user_id);

    public void updateUser(Register_user user);

    public List<Register_user> loadAll();
}
