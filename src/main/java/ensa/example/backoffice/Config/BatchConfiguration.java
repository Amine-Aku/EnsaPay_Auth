package ensa.example.backoffice.Config;

import ensa.example.backoffice.Entities.ComptePayement;
import ensa.example.backoffice.Service.OpenCsvWriterByAppend;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.support.RunIdIncrementer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    TaskletStep taskletStep;

    @Autowired
    public DataSource dataSource;
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public OpenCsvWriterByAppend openCsvWriterByAppend;

  /*  @JobScope
    public  void FilesService() throws IOException {
        /*File yourFile = new File("C:\\Users\\pc\\Documents\\bank\\src\\main\\resources\\compteePay.csv");
        yourFile.createNewFile();
        String filN="C:\\Users\\pc\\Documents\\bank\\src\\main\\resources\\compteePay.csv";
        File f = new File(filN);

        if(f.exists()){
            f.createNewFile();
            System.out.println("File existed");
        }else{

            System.out.println("File not found!");
        }
        openCsvWriterByAppend.addCompteToCsvFile(new ComptePayement(200.,"c80000",1),filN);
        }*/


    /*@StepScope
    @Bean
    public FlatFileItemReader<ComptePayement> reader() throws IOException {
        FlatFileItemReader<ComptePayement> reader = new FlatFileItemReader<ComptePayement>();


        reader.setResource(new ClassPathResource("newFile.csv"));
       // reader.setResource(new ClassPathResource(fileName));
        reader.setLineMapper(new DefaultLineMapper<ComptePayement>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "solde","type_compte","id_client"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<ComptePayement>() {{
                setTargetType(ComptePayement.class);
            }});
        }});
        return reader;
    }*/
    @Bean
    public CompteItemProcessor processor() {
        return new CompteItemProcessor();
    }
    private static final String OVERRIDDEN_BY_EXPRESSION = "file_name";
    @Bean
    @StepScope
    public FlatFileItemReader<ComptePayement> reader(
            @Value("#{jobParameters[fileName]}") String pathToFile){
        System.out.println("path file" + pathToFile);
        FlatFileItemReader<ComptePayement> itemReader = new FlatFileItemReader<ComptePayement>();
        //itemReader.setLineMapper(lineMapper());
        itemReader.setLineMapper(new DefaultLineMapper<ComptePayement>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "solde","type_compte","id_client"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<ComptePayement>() {{
                setTargetType(ComptePayement.class);
            }});
        }});
        itemReader.setResource(new PathResource(pathToFile));
        return itemReader;
    }
    @Bean
    public JdbcBatchItemWriter<ComptePayement> writer() {
        JdbcBatchItemWriter<ComptePayement> writer = new JdbcBatchItemWriter<ComptePayement>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<ComptePayement>());
        writer.setSql("INSERT INTO compte_payement (solde,type_compte,id_client) VALUES (:solde,:type_compte,:id_client)");

        writer.setDataSource(dataSource);
        return writer;
    }




    @Bean
    public Job job() throws IOException {
        return jobBuilderFactory.get("job")
                .incrementer(new RunIdIncrementer())
                .start(step1()).next(step2())
                .build();
    }


    @Bean
    public Step step1() throws IOException {
        System.out.println("step1");
        return stepBuilderFactory.get("step1")
                .<ComptePayement,ComptePayement> chunk(10)
                .reader(reader(OVERRIDDEN_BY_EXPRESSION))
                .processor(processor())
                .writer(writer())
                .build();
    }


    @Bean
    public Step step2() {

        return stepBuilderFactory.get("step2")
                .tasklet(taskletStep)
                .build();
    }

   /*








    @Bean
    public Job importUserJob(JobCompletionNotificationListener  listener) {
        return jobBuilderFactory.get("importUserJob")
                .flow(step1())
                .end()
                .build();
                /*.incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(step1())
                .next(step2())
                .build();
    }

  */




}
