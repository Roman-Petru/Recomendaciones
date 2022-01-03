package Roman.Recomendacion.Series.y.Libros.controllers;

import Roman.Recomendacion.Series.y.Libros.models.entities.ListaNoticias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class NoticiaController {

    @Autowired
    private ApiController apiController;

    @Autowired
    private InicioController inicioController;

    @RequestMapping(value = "noticias")
    public String login(Model model, HttpServletRequest request) {
        inicioController.cargarUsuarioLogueado(request, model);
        ListaNoticias noticias = apiController.traerNoticias();
        List<ListaNoticias.Articles> articulos = Arrays.asList(noticias.articles);
        if (new Integer(noticias.getTotalResults()) > 5)
            articulos = articulos.stream().limit(5).collect(Collectors.toList());
        model.addAttribute("articulos", articulos);
        return "noticias";
    }
}
