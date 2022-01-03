package Roman.Recomendacion.Series.y.Libros.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class ParametrosRecomendacion extends Persistente{

    @Column
    private Boolean activo;

    @Column
    private String tipoContenido;

    @Column
    private Integer minimoPuntaje;

    @ManyToMany
    private List<GeneroDeContenido> generos;

    public ParametrosRecomendacion() {
    }

    public void agregarGenero(GeneroDeContenido generoT) {
        generos.add(generoT);
    }
}
