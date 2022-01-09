package Roman.Recomendacion.Series.y.Libros.controllers;

import Roman.Recomendacion.Series.y.Libros.models.entities.Contenido;
import Roman.Recomendacion.Series.y.Libros.models.entities.Review;
import Roman.Recomendacion.Series.y.Libros.models.entities.Usuario;
import Roman.Recomendacion.Series.y.Libros.models.repositories.RepositorioContenidos;
import Roman.Recomendacion.Series.y.Libros.models.repositories.RepositorioReviews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ReviewController {

    @Autowired
    private RepositorioReviews repositorioReviews;

    @Autowired
    private RepositorioContenidos repositorioContenidos;

    @Autowired
    private InicioController inicioController;

    @RequestMapping(value = "escribirCritica/{id}")
    public String escribirCritica(@PathVariable Integer id, Model model, HttpServletRequest request) {
        if (!this.repositorioContenidos.findById(id).isPresent())
            return "redirect:../mensaje/No existe este contenido";
        this.inicioController.cargarUsuarioLogueado(request, model);
        model.addAttribute("serieID", id);
        return "escribirCritica";
    }

    @RequestMapping(value = "escribirCritica/{id}", method = RequestMethod.POST)
    public String escribirCriticaPost(@PathVariable Integer id, @RequestParam Integer puntaje,
                                      @RequestParam String review, HttpServletRequest request) {

        if (!this.repositorioContenidos.findById(id).isPresent())
            return "redirect:../mensaje/No existe este contenido";

        Contenido contenido = this.repositorioContenidos.findById(id).get();

        Usuario usuarioLogueado = this.inicioController.traerUsuarioLogueado(request);
        Review nuevaReview = new Review();

        if (usuarioLogueado != null){
            List<Review> reviews = this.repositorioReviews.findBycontenido(contenido);
            nuevaReview = reviews.stream().filter(r -> r.getUsuario().getId() == usuarioLogueado.getId()).findAny().orElse(new Review());
            nuevaReview.setUsuario(usuarioLogueado);}

        nuevaReview.setPutanje(puntaje);
        nuevaReview.setDescripcion(review);
        nuevaReview.setContenido(contenido);

        this.repositorioReviews.save(nuevaReview);
        return "redirect:../mensaje/Critica enviada";
    }
}
