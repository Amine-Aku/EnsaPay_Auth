package ensa.example.backoffice.Service;

import ensa.example.backoffice.Config.TaskletStep;
import ensa.example.backoffice.Entities.ComptePayement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;

@Service
public class OpenCsvWriterByAppend {
   //String csvFilename = "C:\\Users\\pc\\Documents\\bank\\src\\main\\resources\\newFile.csv";
    @Autowired
    TaskletStep taskletStep;

    public  void addCompteToCsvFile(ComptePayement ComptePayement){
        try {
            System.out.println("add Compte To csv file");
            System.out.println(taskletStep.getCsvFilename());
            FileWriter csvwriter = new FileWriter(taskletStep.getCsvFilename());

            csvwriter.append(ComptePayement.getSolde().toString());
            csvwriter.append(",");

            csvwriter.append(ComptePayement.getType_compte());
            csvwriter.append(",");
            csvwriter.append((char)ComptePayement.getId_client());


            csvwriter.close();
            System.out.println("CSV file created succesfully.");
        } catch (Exception e) {
            System.out.println("exception :" + e.getMessage());
        }


    }
}
