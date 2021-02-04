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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id_compte;

    @Column(name="solde")
    private Double solde;

    @Column(name="typeCompte")
    private String typeCompte;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_client")
    private UserApp client;

    public ComptePayement(int id_compte, Double solde, String typeCompte, int idClient) {
        this.id_compte = id_compte;
        this.solde = solde;
        this.typeCompte = typeCompte;
        this.client.setId_user(idClient);
    }

    public ComptePayement(Double solde, String typeCompte, int id_client) {
        this.solde = solde;
        this.typeCompte = typeCompte;
        this.client.setId_user(id_client);
    }

}
