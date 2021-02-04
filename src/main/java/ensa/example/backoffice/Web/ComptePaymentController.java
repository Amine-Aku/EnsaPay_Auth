package ensa.example.backoffice.Web;

import ensa.example.backoffice.Entities.ComptePayement;
import ensa.example.backoffice.Service.ComptePaymentService;
import ensa.example.backoffice.Service.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.ensa.bank.Service.OpenCsvWriterByAppend;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("comptePay")
public class ComptePaymentController {

    @Autowired
    ComptePaymentService comptePaymentService;



    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String createCompte(@RequestBody ComptePayement comptePayement) throws IOException {
        
        return comptePaymentService.saveComptePayment(comptePayement);
    
    }

    @RequestMapping(value = "/allClients", method = RequestMethod.GET)
    public List<ComptePayement> getAllComptes() throws IOException{
        return  comptePaymentService.getAllComptes();
    }

    @Autowired
    OpenCsvWriterByAppend openCsvWriterByAppend;

    @RequestMapping(value="/creation",method= RequestMethod.POST)
    public void saveComptePay(@RequestBody ComptePayment comptePayment) {
        comptePaymentService.saveComptePayment(comptePayement);
         openCsvWriterByAppend.addCompteToCsvFile(comptePayment);
    }

}
