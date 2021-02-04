package ensa.example.backoffice.Web;

import ensa.example.backoffice.Entities.ComptePayement;
import ensa.example.backoffice.Service.ComptePayementService;
import ensa.example.backoffice.Service.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import ensa.example.backoffice.Service.OpenCsvWriterByAppend;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("comptePay")
public class ComptePayementController {

    @Autowired
    ComptePayementService ComptePayementService;



    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String createCompte(@RequestBody ComptePayement comptePayement) throws IOException {
        
        return ComptePayementService.saveComptePayement(comptePayement);
    
    }

    @RequestMapping(value = "/allClients", method = RequestMethod.GET)
    public List<ComptePayement> getAllComptes() throws IOException{
        return  ComptePayementService.getAllComptes();
    }

    @Autowired
    OpenCsvWriterByAppend openCsvWriterByAppend;

    @RequestMapping(value="/creation",method= RequestMethod.POST)
    public void saveComptePay(@RequestBody ComptePayement comptePayement) throws IOException {
        ComptePayementService.saveComptePayement(comptePayement);
         openCsvWriterByAppend.addCompteToCsvFile(comptePayement);
    }

}
