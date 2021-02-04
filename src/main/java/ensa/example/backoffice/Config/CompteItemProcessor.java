package ensa.example.backoffice.Config;

import ensa.example.backoffice.Entities.ComptePayement;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.logging.Logger;

public class CompteItemProcessor implements ItemProcessor<ComptePayement, ComptePayement> {


    @Override
    public ComptePayement process(ComptePayement ComptePayement) throws Exception {
        return  ComptePayement;
    }

}
