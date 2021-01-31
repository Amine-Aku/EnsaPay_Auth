package ensa.example.backoffice.Repository;


import ensa.example.backoffice.Entities.ComptePayement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComptePaymentRepository extends JpaRepository<ComptePayement,Integer> {
}
