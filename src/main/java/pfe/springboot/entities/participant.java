package pfe.springboot.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter

public class participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id ;
  private String nom ;
  private String prenom ;
    private String email ;
    private String password ;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] photo;


}
