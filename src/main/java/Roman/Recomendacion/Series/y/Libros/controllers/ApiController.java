package Roman.Recomendacion.Series.y.Libros.controllers;

import Roman.Recomendacion.Series.y.Libros.models.entities.ListaNoticias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ApiController {

    private static final String NEWS_API_KEY = "4c1dee10adef4de7b1f5f6713fd5af81";

    private static final String GET_SHOW_NEWS = "https://newsapi.org/v2/top-headlines?category=entertainment&country=ar&sortBy=popularity&apiKey=" + NEWS_API_KEY;

    @Autowired
    private RestTemplate restTemplate;

    public ListaNoticias traerNoticias() {
        ListaNoticias listaNoticias = restTemplate.getForObject(GET_SHOW_NEWS, ListaNoticias.class); // resttemplate 1 o ??
        return listaNoticias;
    }
}
