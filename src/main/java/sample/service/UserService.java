package sample.service;

import sample.repository.UserRepository;

public class UserService {
    UserRepository userRepository = new UserRepository();

    public boolean validateEmptyString( String s){
        if (s.isEmpty()){
            return false;
        }
        return true;
    }

    public void insertUser(model.User user){
        if (!validateEmptyString(user.getId()) || !validateEmptyString(user.getFirstName()) ||
                !validateEmptyString(user.getLastName()) || !validateEmptyString(user.getEmail())){
            System.out.println("User cannot be inserted");
        }
        else userRepository.insert(user);
    }
}
