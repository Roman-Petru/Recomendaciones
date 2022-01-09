package Roman.Recomendacion.Series.y.Libros.models.entities.validador;

import Roman.Recomendacion.Series.y.Libros.models.entities.Contenido;
import Roman.Recomendacion.Series.y.Libros.models.entities.ParametrosRecomendacion;
import Roman.Recomendacion.Series.y.Libros.models.entities.Usuario;

public class ValidarPuntaje implements ValidacionRecomendacion{
    @Override
    public boolean validarRecomendacion(ParametrosRecomendacion userParams, Contenido contenido) {
        return contenido.puntajePromedio() > userParams.getMinimoPuntaje();
    }
}
