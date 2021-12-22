package edu.kpi.iasa.mmsa.dance_std.service;

import edu.kpi.iasa.mmsa.dance_std.model.Account;
import edu.kpi.iasa.mmsa.dance_std.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Account createAccount(Account account) {
        return userRepository.save(account);
    }
}
