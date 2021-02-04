package ensa.example.backoffice.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Table
@Entity
public  class UserApp {
    //en milliseconde
    private static final long PASSWORD_EXPIRATION_TIME
            = 30L * 24L * 60L * 60L * 1000L;

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id_user;
    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "numTel")
    private  String numTel;

    @Column(name = "password_changed_time")
    private Date passwordChangedTime;

    @Column(name="password")
    private String password;

    @Column(name = "profil")
    private String profil;

    @Column(name = "username")
    private String username;



    @Column(name = "cinRecto")
    private byte[] cinRecto;

    @Column(name = "cinVerso")
    private byte[] cinVerso;

    @Column(name="firstAuth")
    private Boolean firstAuth;

    @Column(name = "isBlacklisted")
    private Boolean isBlacklisted;

    // @JsonIgnore
    // @OneToMany(mappedBy="client",  cascade = CascadeType.ALL)
    // private Collection<ComptePayement> comptePayements;
    @OneToOne(mappedBy="client",cascade = CascadeType.ALL)
    private ComptePayement comptePayements;

    public UserApp(String nom, String prenom, String numTel, Date passwordChangedTime, String password, String profil, String username, byte[] cinRecto, byte[] cinVerso) {
        this.nom = nom;
        this.prenom = prenom;
        this.numTel = numTel;
        this.passwordChangedTime = passwordChangedTime;
        this.password = password;
        this.profil = profil;
        this.username = username;
        this.cinRecto = cinRecto;
        this.cinVerso = cinVerso;
    }


}
