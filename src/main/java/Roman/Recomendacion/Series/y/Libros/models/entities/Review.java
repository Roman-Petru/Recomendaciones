package Roman.Recomendacion.Series.y.Libros.models.entities;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Getter @Setter
public class Review extends Persistente {

    @Column
    private Integer putanje;

    @Column
    private String descripcion;

    @ManyToOne
    private Contenido contenido;

    @ManyToOne
    private Usuario usuario;

    @Column(name = "fechaCarga")
    private Date fechaCarga;

    public Review() {
        this.fechaCarga = new Date();
    }
}
