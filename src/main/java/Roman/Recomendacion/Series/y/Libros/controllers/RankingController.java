package Roman.Recomendacion.Series.y.Libros.controllers;

import Roman.Recomendacion.Series.y.Libros.models.entities.Contenido;
import Roman.Recomendacion.Series.y.Libros.models.entities.Usuario;
import Roman.Recomendacion.Series.y.Libros.models.repositories.RepositorioContenidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
public class RankingController {

    @Autowired
    private RepositorioContenidos repositorioContenidos;

    @Autowired
    private InicioController inicioController;

    @RequestMapping(value = "mejoresSeries")
    public String mejoresSeries(Model model, HttpServletRequest request) {

        this.mejorContenido(model, request, "Serie");
        return "mostrarContenido";
    }

    @RequestMapping(value = "mejoresLibros")
    public String mejoresLibros(Model model, HttpServletRequest request) {

        this.mejorContenido(model, request, "Libro");
        return "mostrarContenido";
    }

    private void mejorContenido(Model model, HttpServletRequest request, String tipo) {
        this.inicioController.cargarUsuarioLogueado(request, model);

        List<Contenido> listaSeries = new ArrayList<>();
        this.repositorioContenidos.findAll().forEach(listaSeries::add);
        listaSeries = listaSeries.stream().filter( c -> c.tipo().equals(tipo)).collect(Collectors.toList());
        listaSeries.sort((s1, s2) -> Double.compare(s2.puntajePromedio(), s1.puntajePromedio()));

        Usuario usuarioLogueado = this.inicioController.traerUsuarioLogueado(request);
        Integer i = 1;
        for (Contenido serie : listaSeries)
        {
            serie.setRanking(i);
            serie.setPuntaje(serie.puntajePromedio());
            if (usuarioLogueado != null)
                serie.setPuntajeLogueado(serie.getReviews().stream().filter(review -> review.getUsuario() == usuarioLogueado).findFirst().map(review -> review.getPutanje().toString()).orElse("-"));
            i++;
        }

        model.addAttribute("contenidos", listaSeries);
    }

    @RequestMapping(value = "busqueda", method = RequestMethod.POST)
    public String busqueda(@RequestParam String busqueda,Model model, HttpServletRequest request) {

        this.inicioController.cargarUsuarioLogueado(request, model);

        List<Contenido> listaSeries = new ArrayList<>();
        this.repositorioContenidos.findAll().forEach(listaSeries::add);
        listaSeries = listaSeries.stream().filter( cont -> cont.getNombre().toLowerCase(Locale.ROOT).contains(busqueda.toLowerCase(Locale.ROOT))).collect(Collectors.toList());
        listaSeries.sort((s1, s2) -> Double.compare(s2.puntajePromedio(), s1.puntajePromedio()));

        Usuario usuarioLogueado = this.inicioController.traerUsuarioLogueado(request);
        Integer i = 1;
        for (Contenido serie : listaSeries)
        {
            serie.setRanking(i);
            serie.setPuntaje(serie.puntajePromedio());
            if (usuarioLogueado != null)
                serie.setPuntajeLogueado(serie.getReviews().stream().filter(review -> review.getUsuario() == usuarioLogueado).findFirst().map(review -> review.getPutanje().toString()).orElse("-"));
            i++;
        }

        model.addAttribute("contenidos", listaSeries);

        return "mostrarContenido";
    }
}
