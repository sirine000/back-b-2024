package pfe.springboot.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomCours;
    
    @ElementCollection
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private List<byte[]> fileContents = new ArrayList<>(); // Store file contents as binary data

    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cycle")
    private cycle cycle;
}
