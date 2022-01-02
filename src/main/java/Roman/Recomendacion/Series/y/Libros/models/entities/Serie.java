package Roman.Recomendacion.Series.y.Libros.models.entities;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="Serie")
@Getter @Setter
public class Serie extends Contenido{

    @Column
    private Integer temporadas;

    @Column
    private Integer capitulos;

    public String tipo() {return "Serie";}
}
