package ensa.example.backoffice.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Table
@Entity
public class ComptePayement {
    @Id
    @GeneratedValue
    private int id_compte;

    @Column(name="solde")
    private Double solde;

    @Column(name="typeCompte")
    private String typeCompte;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_client")
    private UserApp client;


}
