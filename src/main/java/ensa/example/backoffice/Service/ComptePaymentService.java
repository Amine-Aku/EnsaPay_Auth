package ensa.example.backoffice.Service;

import ensa.example.backoffice.Entities.ComptePayement;
import ensa.example.backoffice.Repository.ComptePaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;

@Service
public class ComptePaymentService {

    @Autowired
    ComptePaymentRepository comptePaymentRepository;

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder=new  BCryptPasswordEncoder();

    public String saveComptePayment(ComptePayement comptePayement) throws IOException {
        String pass = userService.genererPassword();
        comptePayement.getClient().setPassword(bCryptPasswordEncoder.encode(pass));
        comptePayement.getClient().setProfil("client");
            //   userService.createUser(comptePayement.getClient().getNom(),comptePayement.getClient().getPrenom(),
            //   comptePayement.getClient().getUsername(),comptePayement.getClient().getNumTel());
        comptePaymentRepository.save(comptePayement);
        return pass;
    }
}
