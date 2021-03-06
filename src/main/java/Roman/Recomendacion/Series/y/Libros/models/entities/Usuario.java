package Roman.Recomendacion.Series.y.Libros.models.entities;

import Roman.Recomendacion.Series.y.Libros.models.entities.notificador.JavaGMailAdapter;
import Roman.Recomendacion.Series.y.Libros.models.entities.notificador.Mensajeable;
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

    @OneToOne(cascade = CascadeType.ALL)
    private ParametrosRecomendacion parametrosRecomendacion;

    public Usuario() {

    }

    public void enviarMail(Mensajeable mensajeable){
        mensajeable.setDestinatario(email);
        JavaGMailAdapter gMailAdapter = new JavaGMailAdapter();
        gMailAdapter.enviar(mensajeable);
    }
}
