package org.opencrash.api;
import org.opencrash.domain_objects.User;

/**
 * Created by Fong on 12.05.14.
 */
public interface ApplicationUserService {
    public User getUser(String uid);
    public void newUser(User applicationuser);
}
