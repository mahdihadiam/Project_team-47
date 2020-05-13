package model.user;

import com.google.gson.Gson;
import model.others.Sort;
import model.send.receive.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.regex.Pattern;

abstract public class User {
    static ArrayList<User> allUsers;
    private static TreeSet<String> usedUsernames;
    private static int managersNumber;

    static {
        allUsers = new ArrayList<>();
        usedUsernames = new TreeSet<>();
    }

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String type;


    public User() {
        this.addUser();
    }


    public User(HashMap<String, String> userInfo) {
        this.addUser();
        this.username = userInfo.get("username");
        this.password = userInfo.get("password");
        this.phoneNumber = userInfo.get("phone-number");
        this.email = userInfo.get("email");
        this.firstName = userInfo.get("first-name");
        this.lastName = userInfo.get("last-name");
        this.type = userInfo.get("type");
        usedUsernames.add(this.username);
    }


    public static boolean isThereCustomersWithUsername(ArrayList<String> userNames) {
        for (String username : userNames) {
            User user = getUserByUsername(username);
            if (!(user instanceof Customer)) {
                return false;
            }
        }
        return true;
    }


    public static boolean isThereSeller(Seller seller) {
        return allUsers.contains(seller);
    }


    public static boolean isThereSeller(String username) {
        return getUserByUsername(username) instanceof Seller;
    }


    public static boolean doesUsernameUsed(String username) {
        return usedUsernames.contains(username);
    }

    public static boolean isThereUserWithUsername(String username) {
        for (User user : allUsers) {
            if (user.username.equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }


    public static boolean isThereUserWithEmail(String email) {
        for (User user : allUsers) {
            if (user.email.equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }


    public static boolean isThereUserWithPhone(String phoneNumber) {
        for (User user : allUsers) {
            if (user.phoneNumber.equalsIgnoreCase(phoneNumber)) {
                return true;
            }
        }
        return false;
    }


    public static boolean isUsernameValid(String username) {
        if (username.length() < 5)
            return false;
        return Pattern.matches("[a-zA-Z0-9]+", username);
    }


    public static boolean isPasswordValid(String password) {
        if (password.length() < 8)
            return false;
        return Pattern.matches("[a-zA-Z0-9!@#$%^&*-_=]+", password);
    }


    public static boolean isPhoneValid(String phoneNumber) {
        return Pattern.matches("09\\d{9}", phoneNumber);
    }


    public static boolean isEmailValid(String email) {
        return Pattern.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-zA-Z]+", email);
    }


    public static boolean checkPasswordIsCorrect(String username, String password) {
        User user = getUserByUsername(username);
        if (user == null)
            return false;
        return user.password.equals(password);
    }


    public static boolean isThereManager() {
        return managersNumber > 0;
    }


    public static User getUserByUsername(String username) {
        for (User user : allUsers) {
            if (user.username.equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }


    public static void deleteUser(String username) {
        User user = getUserByUsername(username);
        assert user != null;
        user.deleteUser();
    }


    private static void copyUserInfo(User destinationUser, User sourceUser) {
        //this function copy some field from source user to destination user
        //it uses when we want change role then we can create new user and copy last user info into new user
        destinationUser.email = sourceUser.email;
        destinationUser.firstName = sourceUser.firstName;
        destinationUser.lastName = sourceUser.lastName;
        destinationUser.password=sourceUser.password;
        destinationUser.phoneNumber = sourceUser.phoneNumber;
        destinationUser.username = sourceUser.username;
    }


    public static ArrayList<UserInfo> getAllUsers(String field, String direction) {
        ArrayList<UserInfo> usersInfo = new ArrayList<>();
        ArrayList<User> sortedUsers = Sort.sortUsers(field, direction, allUsers);
        for (User user : sortedUsers) {
            usersInfo.add(user.userInfoForSending());
        }
        return usersInfo;
    }


    static void managerAdded() {
        managersNumber++;
    }


    static void managerRemoved() {
        managersNumber--;
    }


    public abstract void deleteUser();


    public abstract UserInfo userInfoForSending();


    public void changeRole(String newRole) {

        switch (newRole) {
            case "manager":
                if (this instanceof Manager)
                    return;
                this.changeUserToManager();
                break;
            case "customer":
                if (this instanceof Customer)
                    return;
                this.changeUserToCustomer();
                break;
            case "seller":
                if (this instanceof Seller)
                    return;
                this.changeUserToSeller();
                break;
        }
        this.deleteUser();
        //changing database
    }


    void addUser() {
        allUsers.add(this);
        if (this instanceof Manager)
            User.managersNumber++;
        //changing database
    }


    private void changeUserToManager() {
        Manager manager = new Manager();
        copyUserInfo(manager, this);
        manager.setType("manager");
        //changing database
    }


    private void changeUserToSeller() {
        Seller seller = new Seller();
        copyUserInfo(seller, this);
        seller.setType("seller");
        //changing database
    }


    private void changeUserToCustomer() {
        Customer customer = new Customer();
        copyUserInfo(customer, this);
        customer.setType("customer");
        //changing database
    }


    @Override
    public String toString() {
        return "User{}";
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
