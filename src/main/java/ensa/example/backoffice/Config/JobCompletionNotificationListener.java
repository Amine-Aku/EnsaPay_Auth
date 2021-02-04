package ensa.example.backoffice.Config;



import ensa.example.backoffice.Entities.ComptePayement;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {


        private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        @Override
        public void afterJob(JobExecution jobExecution) {
            if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
                System.out.println("!!! JOB FINISHED! Time to verify the results");
                try {
                    FileOutputStream writer = new FileOutputStream("C:\\Users\\pc\\Documents\\bank\\src\\main\\resources\\newFile.csv");
                } catch (FileNotFoundException e) {
                   System.out.println("file not exist");
                }

                List<ComptePayement> results = jdbcTemplate.query("SELECT id_compte, solde,type_compte,id_client FROM compte_payement", new RowMapper<ComptePayement>() {
                    @Override
                    public ComptePayement mapRow(ResultSet rs, int row) throws SQLException {
                        return new ComptePayement(rs.getInt(1), rs.getDouble(2),rs.getString(3),rs.getInt(4));
                    }
                });

                for (ComptePayement ComptePayement : results) {
                   System.out.println("Found <" + ComptePayement + "> in the database.");
                }

            }
        }
    }
