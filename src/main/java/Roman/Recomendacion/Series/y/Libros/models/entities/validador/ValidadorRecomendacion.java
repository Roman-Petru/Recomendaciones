package Roman.Recomendacion.Series.y.Libros.models.entities.validador;

import Roman.Recomendacion.Series.y.Libros.models.entities.Contenido;
import Roman.Recomendacion.Series.y.Libros.models.entities.ParametrosRecomendacion;

import java.util.ArrayList;
import java.util.List;

public class ValidadorRecomendacion {

    private final List<ValidacionRecomendacion> validaciones = new ArrayList<>();

    public ValidadorRecomendacion() {
        this.validaciones.add(new ValidarGenero());
        this.validaciones.add(new ValidarTipo());
        this.validaciones.add(new ValidarPuntaje());
    }

    public boolean validarRecomendacion(ParametrosRecomendacion userParams, Contenido contenido){
        return this.validaciones.stream().allMatch(validacion -> validacion.validarRecomendacion(userParams, contenido));
    }
}
