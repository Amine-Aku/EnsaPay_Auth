package ensa.example.backoffice.Service;

import ensa.example.backoffice.Entities.ComptePayement;
import ensa.example.backoffice.Repository.ComptePaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ComptePaymentService {

    @Autowired
    ComptePaymentRepository comptePaymentRepository;

    @Autowired
    UserService userService;

    public ComptePayement saveComptePayment(ComptePayement comptePayement) throws IOException {

              userService.createUser(comptePayement.getClient().getNom(),comptePayement.getClient().getPrenom(),
              comptePayement.getClient().getUsername(),comptePayement.getClient().getNumTel(),null,null,
                "client");
             return comptePaymentRepository.save(comptePayement);
    }
}
