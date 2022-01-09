package Roman.Recomendacion.Series.y.Libros.models.entities.notificador;

import Roman.Recomendacion.Series.y.Libros.models.entities.Contenido;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ArmadorMensajeRecomendacion implements ArmadorMensaje{

    private List<Contenido> contenidos;

    @Override
    public Mensajeable armarMensaje() {

        Mensajeable mensajeable = new Mensajeable();
        mensajeable.setAsunto("Recomendacion semanal de series y libros");
        mensajeable.setMensaje("Tenemos las siguientes recomendaciones esta semana:\n\n");
        for (Contenido contenido : contenidos)
        {
            mensajeable.setMensaje(mensajeable.getMensaje().concat(contenido.getNombre() + ": " + contenido.getDescripcion() + "\nPuntaje: " + contenido.puntajePromedio() + "\n\n"));
        }
        return mensajeable;
    }
}
