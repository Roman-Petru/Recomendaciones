package Roman.Recomendacion.Series.y.Libros.controllers;

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
        Review nuevaReview = new Review();
        nuevaReview.setPutanje(puntaje);
        nuevaReview.setDescripcion(review);
        nuevaReview.setContenido(this.repositorioContenidos.findById(id).get());

        Usuario user = this.inicioController.traerUsuarioLogueado(request);
        if (user != null)
            nuevaReview.setUsuario(user);

        this.repositorioReviews.save(nuevaReview);
        return "redirect:../mensaje/Critica enviada";
    }
}
