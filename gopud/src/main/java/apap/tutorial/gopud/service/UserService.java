package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.UserModel;

public interface UserService {
    UserModel addUser(UserModel user);
    public String encrypt(String password);
    UserModel findByUsername(String username);
    UserModel changePassword(UserModel userModel, String newPassword);
}
