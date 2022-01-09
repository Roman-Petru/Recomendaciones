package Roman.Recomendacion.Series.y.Libros.controllers;

import Roman.Recomendacion.Series.y.Libros.models.entities.*;
import Roman.Recomendacion.Series.y.Libros.models.repositories.RepositorioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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

        user.getParametrosRecomendacion().setActivo(Boolean.TRUE);
        user.getParametrosRecomendacion().setMinimoPuntaje(puntajeMinimo);
        user.getParametrosRecomendacion().setTipoContenido(tipo);

        user.getParametrosRecomendacion().setGeneros(new ArrayList<>());

        for (String genero : generos)
        {
            for (GeneroDeContenido generoT : this.contenidoController.traerGeneros())
            {
                if(genero.equals(generoT.getDescripcion()))
                    user.getParametrosRecomendacion().agregarGenero(generoT);
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
        this.repositorioUsuarios.save(user);

        return "redirect:mensaje/Se han desactivado las recomendaciones semanales!";
    }

    @Scheduled(fixedDelayString = "PT10M")
    public void recomendador(){
        List<Contenido> listaContenidos = this.contenidoController.traerContenidos();
        List<Usuario> listaUsuarios = new ArrayList<>();
        this.repositorioUsuarios.findAll().forEach(listaUsuarios::add);
        Recomendador recomendador = new Recomendador();
        recomendador.recomendar(listaContenidos, listaUsuarios);
    }
}
