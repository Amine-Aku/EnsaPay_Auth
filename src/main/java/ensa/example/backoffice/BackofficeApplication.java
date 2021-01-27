package ensa.example.backoffice;

import ensa.example.backoffice.Config.SmtpMailSender;
import ensa.example.backoffice.Entities.UserApp;
import ensa.example.backoffice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class BackofficeApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

   
   

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder=new  BCryptPasswordEncoder();
    public static void main(String[] args) {
        SpringApplication.run(BackofficeApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

        UserApp userApp=new UserApp("admin","admin","0651354661",new Date(),bCryptPasswordEncoder.encode("admin"),"admin","admin",null,null);
        userRepository.save(userApp);

    }
}
