package model.user;

import com.google.gson.Gson;
import model.send.receive.UserInfo;

import java.util.HashMap;

public class Manager extends User {
    public Manager() {
        super();
    }

    public Manager(HashMap<String, String> userInfo) {
        super(userInfo);
        User.managerAdded();
    }

    @Override
    public void deleteUser() {
        allUsers.remove(this);
        User.managerRemoved();
        //changing database
    }

    @Override
    public UserInfo userInfoForSending() {
        UserInfo user = new UserInfo();
        user.setEmail(this.getEmail());
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        user.setPhoneNumber(this.getPhoneNumber());
        user.setUsername(this.getUsername());
        return user;
    }
}
