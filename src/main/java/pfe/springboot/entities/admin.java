package pfe.springboot.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter

public class admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_admin;

    private String nomEtPrenom;

    private String email;
    private String password;
    private Integer Tel;


    @ManyToMany
    @JoinTable(name = "utilisateurEtRole", joinColumns = @JoinColumn(name = "id_admin"), inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<role> role = new HashSet<>();

}
