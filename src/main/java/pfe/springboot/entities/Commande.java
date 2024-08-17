package pfe.springboot.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomPrenom;
    private String email;
    private String telephone;
    private String adresse;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] bonDeCommande;
    private boolean verifier;

    @ManyToOne
    private cycle cycle;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomPrenom() {
        return nomPrenom;
    }

    public void setNomPrenom(String nomPrenom) {
        this.nomPrenom = nomPrenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public byte[] getBonDeCommande() {
        return bonDeCommande;
    }

    public void setBonDeCommande(byte[] bonDeCommande) {
        this.bonDeCommande = bonDeCommande;
    }

    public boolean isVerifier() {
        return verifier;
    }

    public void setVerifier(boolean verifier) {
        this.verifier = verifier;
    }

    public cycle getCycle() {
        return cycle;
    }

    public void setCycle(cycle cycle) {
        this.cycle = cycle;
    }
}