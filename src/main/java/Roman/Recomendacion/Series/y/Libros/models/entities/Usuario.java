package Roman.Recomendacion.Series.y.Libros.models.entities;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Usuario {

    private String nombre_usuario;
    private String password;
    private String nombre_completo;
    private NivelAdmin nivel_admin;

    public Usuario() {

    }
}
