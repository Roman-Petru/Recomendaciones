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
public class ParametrosRecomendacion {

    @Column
    private String tipoContenido;

    @Column
    private Integer minimoPuntaje;

    @ManyToMany
    private List<GeneroDeContenido> generos = new ArrayList<>();

    public ParametrosRecomendacion() {
    }

}
