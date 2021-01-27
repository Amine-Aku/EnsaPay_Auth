package ensa.example.backoffice.Service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.org.apache.xpath.internal.operations.Mult;
import ensa.example.backoffice.Config.SmtpMailSender;
import ensa.example.backoffice.Entities.UserApp;
import ensa.example.backoffice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.net.URLConnection;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service

public class UserService {

    final String letterLower = "abcdefghijklmnopqrstuvwxyz";
    final String letterUpper= letterLower.toUpperCase();
    final String number = "0123456789";
    final String caractereSpeciaux = "!@#$%&*_?':,;~°^";
    final String passworwdCombinaison= letterLower+ letterUpper + number + caractereSpeciaux;

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private SmtpMailSender smtpMailSender;



    public void changePassword(UserApp user, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        if(user.getFirstAuth() == true){ user.setFirstAuth(false);}
        user.setPassword(encodedPassword);

        user.setPasswordChangedTime(new Date());

        userRepository.save(user);
    }


    public String genererPassword() {

        List<String> letters = Arrays.asList(caractereSpeciaux.split(""));
        Collections.shuffle(letters);

        SecureRandom random = new SecureRandom();
        String password="";

        password+=letterLower.charAt(random.nextInt(letterLower.length()));
        password+=letterUpper.charAt(random.nextInt(letterUpper.length()));
        password+=number.charAt(random.nextInt(number.length()));
        password+=caractereSpeciaux.charAt(random.nextInt(caractereSpeciaux.length()));

        for(int i=0;i<5;i++) {
            password+=passworwdCombinaison.charAt(random.nextInt(passworwdCombinaison.length()));
        }

        return password;
    }

    public Boolean createAgent(String nom, String prenom, String username, String numTel, MultipartFile cinRecto, MultipartFile cinVerso, String profil) throws IOException {

       UserApp user=new UserApp();
       user.setNom(nom);
       user.setPrenom(prenom);
       user.setUsername(username);
       user.setNumTel(numTel);
       user.setCinRecto(cinRecto.getBytes());
       user.setCinVerso(cinVerso.getBytes());
       user.setProfil(profil);


       String pass=this.genererPassword();
System.out.println(pass);
      String body = "Bonjour Monsieur / Madame . \n  \n Votre mot de passe est " + pass + ". \n Bienvenue chez nous";
           //this.smtpMailSender.sendMail(user.getUsername(), "Your Password", body);

        user.setFirstAuth(true);
        smtpMailSender.sendMail(user.getUsername(), "Your Password", body);
        user.setPassword(passwordEncoder.encode(pass));
      userRepository.save(user);
        return  true;



    }
}
