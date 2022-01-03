package Roman.Recomendacion.Series.y.Libros.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Usuario extends Persistente {

    @Column(name = "nombreUsuario", unique = true)
    private String nombreUsuario;

    @Column(name = "password")
    private String password;

    @Column(name = "nombreCompleto")
    private String nombreCompleto;

    @Column(name = "email")
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NivelAdmin nivel_admin;

    @OneToMany(mappedBy = "usuario")
    private List<Review> reviews = new ArrayList<>();

    public Usuario() {

    }
}
