package ensa.example.backoffice.Web;



import ensa.example.backoffice.Entities.UserApp;
import ensa.example.backoffice.Repository.UserRepository;
import ensa.example.backoffice.Service.ResponseMessage;
import ensa.example.backoffice.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("userApp")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


    @RequestMapping(value = "/changeMdp", method = RequestMethod.POST)
    public void changeMdp(@RequestBody String mdp){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if(!auth.getPrincipal().equals("anonymousUser")) {
            UserApp user = userRepository.findByUsername(auth.getName());
            userService.changePassword(user, mdp);
        }        
    }

    @RequestMapping(value = "/currentAgent", method = RequestMethod.GET)
    public UserApp currentAgent() {

        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);
        if(!auth.getPrincipal().equals("anonymousUser")) {
            UserApp user = userRepository.findByUsername(auth.getName());
            return user;
        }
        return null;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> saveUser(@RequestParam(value = "cinRecto", required = false) MultipartFile cinRecto,
                                                    @RequestParam(value = "cinVerso", required = false) MultipartFile cinVerso,
                                                    @RequestParam(value = "nom", required = false) String nom,
                                                    @RequestParam(value = "prenom", required = false) String prenom,
                                                    @RequestParam(value = "username", required = false)String username,
                                                    @RequestParam(value = "numTel", required = false) String numTel,
                                                    @RequestParam(value = "profil", required = false) String profil) {

        String typeRecto = cinRecto.getContentType().split("/")[0];
        String typeVerso = cinVerso.getContentType().split("/")[0];

        String message = "";
        if (!typeRecto.equals("image") || !typeVerso.equals("image")) {
            message = "Extension erroné";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
        else {

            try {

                String pass=null;
Boolean bol=userService.createAgent(nom, prenom, username, numTel, cinRecto, cinVerso, profil);

System.out.println(bol);
                if(bol) {

                    message = "Uploaded the file successfully ";
                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
                }
                else{

                    message = "Could not upload the file !";
                    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
                }
            } catch (Exception e) {
                message = "Could not upload the file !";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        }
}
