package Roman.Recomendacion.Series.y.Libros.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Usuario extends Persistente {

    @Column(unique = true)
    private String nombreUsuario;

    @Column
    private String password;

    @Column
    private String nombreCompleto;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NivelAdmin nivel_admin;

    @Transient
    private List<Review> reviews = new ArrayList<>();

    public Usuario() {

    }
}
