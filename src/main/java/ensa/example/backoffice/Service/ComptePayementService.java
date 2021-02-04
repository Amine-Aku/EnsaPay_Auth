package ensa.example.backoffice.Service;

import ensa.example.backoffice.Entities.ComptePayement;
import ensa.example.backoffice.Repository.ComptePayementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;

import java.io.IOException;

@Service
public class ComptePayementService {

    @Autowired
    ComptePayementRepository ComptePayementRepository;

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder=new  BCryptPasswordEncoder();

    private String url="https://ensa-pay-bank.herokuapp.com";

    // @Autowired
    // RestTemplate restTemplate;


    public String saveComptePayement(ComptePayement comptePayement) throws IOException {
        String pass = userService.genererPassword();
        comptePayement.getClient().setPassword(bCryptPasswordEncoder.encode(pass));
        comptePayement.getClient().setProfil("client");
            //   userService.createUser(comptePayement.getClient().getNom(),comptePayement.getClient().getPrenom(),
            //   comptePayement.getClient().getUsername(),comptePayement.getClient().getNumTel());
        comptePaymentRepository.save(comptePayement);
        return pass;
    }

    public List<ComptePayement> getAllComptes() throws IOException {
        return ComptePayementRepository.findAll();

    }


}
