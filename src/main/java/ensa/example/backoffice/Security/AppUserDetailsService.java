package ensa.example.backoffice.Security;


import ensa.example.backoffice.Entities.UserApp;
import ensa.example.backoffice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
System.out.println(username);
        UserApp user =  userRepository.findByUsername(username);
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        if(user==null) throw new UsernameNotFoundException(username);

        return new User(user.getUsername(),user.getPassword(),new ArrayList<>());
    }
}
