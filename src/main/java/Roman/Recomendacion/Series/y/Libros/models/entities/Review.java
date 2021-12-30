package Roman.Recomendacion.Series.y.Libros.models.entities;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class Review extends Persistente {
    private Integer putanje;
    private String descripcion;
    private Contenido contenido;
    private Usuario usuario;
    private Date fechaCarga;

    public Review() {
        this.fechaCarga = new Date();
    }
}
