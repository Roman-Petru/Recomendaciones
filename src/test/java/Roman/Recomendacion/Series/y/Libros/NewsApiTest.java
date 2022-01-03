package Roman.Recomendacion.Series.y.Libros;

import Roman.Recomendacion.Series.y.Libros.controllers.ApiController;
import Roman.Recomendacion.Series.y.Libros.models.entities.ListaNoticias;
import org.junit.jupiter.api.Test;

public class NewsApiTest {
    @Test
    public void newsApiTest() {

        ApiController apiController = new ApiController();

        ListaNoticias noticias = apiController.traerNoticias();

        System.out.printf(noticias.getTotalResults());
    }
}
