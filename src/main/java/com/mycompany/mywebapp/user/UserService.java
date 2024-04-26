package com.mycompany.mywebapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> Listll(){
        return (List<User>) userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User get(int id) throws userNotFoundException {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new userNotFoundException("User Not Find Any Users With ID" + id);
    }

    public void delete(int id) throws userNotFoundException {
        Long count = userRepository.countById(id);
        if(count ==null || count == 0){
            throw new userNotFoundException("Cound not find any users with id" +id);
        }
        userRepository.deleteById(id);
    }

}


