package Roman.Recomendacion.Series.y.Libros;

import Roman.Recomendacion.Series.y.Libros.models.entities.Review;
import Roman.Recomendacion.Series.y.Libros.models.entities.Serie;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;


public class PuntajeContenidoTest {
    @Test
    public void probarPuntajePromedio() {

        Serie serie = new Serie();
        Review review1 = new Review();
        Review review2 = new Review();
        review1.setPutanje(9);
        review2.setPutanje(8);

        serie.agregarReview(review1);
        serie.agregarReview(review2);

        Assert.state(serie.puntajePromedio() == 8.5);
    }
}
