package pfe.springboot.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class cycle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cycle")
    private Long id; // Ensure this property is present

    private String nom_cycle_de_formation;

    private String description;
    private Long prix;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateDebut;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateFin;

    //
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_formateur")

    private Formateur formateur;


    @OneToMany(mappedBy = "cycle", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Commande> commandes;

    @OneToMany(mappedBy = "cycle", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<cours> cours;
    // private String nomEtPrenom;

    // @Override
    // public String getNomEtPrenom() {
    // return super.getNomEtPrenom();
    // }

    // private Boolean present;
    public Long getIdFormateur() {
        return formateur != null ? formateur.getId_formateur() : null;
    }
}
