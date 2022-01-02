package Roman.Recomendacion.Series.y.Libros.models.entities;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter @Setter
public class GeneroDeContenido extends Persistente {

    @Column
    private String descripcion;
}
