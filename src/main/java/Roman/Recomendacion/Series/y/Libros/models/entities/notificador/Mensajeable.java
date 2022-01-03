package Roman.Recomendacion.Series.y.Libros.models.entities.notificador;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mensajeable {

    private String destinatario;

    private String asunto;

    private String mensaje;
}
