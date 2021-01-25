package ensa.example.backoffice.Repository;

import ensa.example.backoffice.Entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserApp,Integer> {

    UserApp findByUsername(String email);
}
