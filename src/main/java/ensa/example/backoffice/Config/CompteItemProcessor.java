package com.ensa.bank.Config;

import ensa.example.backoffice.Entities.ComptePayment;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.logging.Logger;

public class CompteItemProcessor implements ItemProcessor<ComptePayment, ComptePayment> {


    @Override
    public ComptePayment process(ComptePayment comptePayment) throws Exception {
        return  comptePayment;
    }

}
