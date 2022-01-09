package Roman.Recomendacion.Series.y.Libros.models.entities.validador;

import Roman.Recomendacion.Series.y.Libros.models.entities.Contenido;
import Roman.Recomendacion.Series.y.Libros.models.entities.ParametrosRecomendacion;

public class ValidarTipo implements ValidacionRecomendacion{
    @Override
    public boolean validarRecomendacion(ParametrosRecomendacion userParams, Contenido contenido) {
        if (userParams.getTipoContenido().equals("Ambos"))
            return true;
        return (userParams.getTipoContenido().equals(contenido.tipo()));
    }
}
