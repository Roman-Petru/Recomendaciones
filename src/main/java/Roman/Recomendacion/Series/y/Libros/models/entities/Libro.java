package Roman.Recomendacion.Series.y.Libros.models.entities;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="Libro")
@Getter @Setter
public class Libro extends Contenido{

    @Column
    private String autor;

    public String tipo() {return "Libro";}
}
