package Roman.Recomendacion.Series.y.Libros.models.entities.validador;

import Roman.Recomendacion.Series.y.Libros.models.entities.Contenido;
import Roman.Recomendacion.Series.y.Libros.models.entities.ParametrosRecomendacion;
import Roman.Recomendacion.Series.y.Libros.models.entities.Usuario;

public interface ValidacionRecomendacion {
    boolean validarRecomendacion(ParametrosRecomendacion userParams, Contenido contenido);
}
