package Roman.Recomendacion.Series.y.Libros.controllers;

import Roman.Recomendacion.Series.y.Libros.models.entities.GeneroDeContenido;
import Roman.Recomendacion.Series.y.Libros.models.entities.ParametrosRecomendacion;
import Roman.Recomendacion.Series.y.Libros.models.entities.Usuario;
import Roman.Recomendacion.Series.y.Libros.models.repositories.RepositorioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class RecomendadorController {

    @Autowired
    private InicioController inicioController;

    @Autowired
    private ContenidoController contenidoController;

    @Autowired
    private RepositorioUsuarios repositorioUsuarios;

    @RequestMapping(value = "recomendacion")
    public String recomendacion(Model model, HttpServletRequest request) {
        this.inicioController.cargarUsuarioLogueado(request, model);
        model.addAttribute("generos", this.contenidoController.traerGeneros());
        return "recomendacion";
    }

    @RequestMapping(value = "recomendacion", method = RequestMethod.POST)
    public String recomendacionPost(@RequestParam String tipo, @RequestParam Integer puntajeMinimo, @RequestParam String[] generos, HttpServletRequest request) {

        Usuario user = this.inicioController.traerUsuarioLogueado(request);
        if (user == null)
            return "redirect:mensaje/No hay usuario logueado";

        ParametrosRecomendacion parametros = user.getParametrosRecomendacion();
        if (parametros == null)
            user.setParametrosRecomendacion(new ParametrosRecomendacion());

        parametros.setActivo(Boolean.TRUE);
        parametros.setMinimoPuntaje(puntajeMinimo);
        parametros.setTipoContenido(tipo);

        parametros.setGeneros(new ArrayList<>());

        for (String genero : generos)
        {
            for (GeneroDeContenido generoT : this.contenidoController.traerGeneros())
            {
                if(genero.equals(generoT.getDescripcion()))
                    parametros.agregarGenero(generoT);
            }
        }

        this.repositorioUsuarios.save(user);

        return "redirect:mensaje/Se han guardado sus preferencias de recomendaciones, se enviaran semanalmente!";
    }

    @RequestMapping(value = "desactivarRecomendacion")
    public String desactivarRecomendacion(HttpServletRequest request) {

        Usuario user = this.inicioController.traerUsuarioLogueado(request);
        if (user == null)
            return "redirect:mensaje/No hay usuario logueado";

        user.getParametrosRecomendacion().setActivo(Boolean.FALSE);

        return "redirect:mensaje/Se han desactivado las recomendaciones semanales!";
    }
}
