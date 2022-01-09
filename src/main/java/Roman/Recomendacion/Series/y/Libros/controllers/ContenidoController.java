package Roman.Recomendacion.Series.y.Libros.controllers;

import Roman.Recomendacion.Series.y.Libros.models.entities.*;
import Roman.Recomendacion.Series.y.Libros.models.repositories.RepositorioContenidos;
import Roman.Recomendacion.Series.y.Libros.models.repositories.RepositorioGeneros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ContenidoController {

    @Autowired
    private RepositorioContenidos repositorioContenidos;

    @Autowired
    private RepositorioGeneros repositorioGeneros;

    @Autowired
    private InicioController inicioController;

    @RequestMapping(value = "agregarGenero")
    public String agregarGenero(Model model, HttpServletRequest request) {
        this.inicioController.cargarUsuarioLogueado(request, model);
        return "agregarGenero";
    }

    @RequestMapping(value = "agregarGenero", method = RequestMethod.POST)
    public String agregarGeneroPost(@RequestParam String descripcion) {
        GeneroDeContenido genero = new GeneroDeContenido();
        genero.setDescripcion(descripcion);
        repositorioGeneros.save(genero);
        return "redirect:mensaje/Genero agregado";
    }

    @RequestMapping(value = "agregarSerie")
    public String agregarSerie(Model model, HttpServletRequest request) {
        this.inicioController.cargarUsuarioLogueado(request, model);
        model.addAttribute("generos", repositorioGeneros.findAll());
        return "agregarSerie";
    }

    @RequestMapping(value = "agregarSerie", method = RequestMethod.POST)
    public String agregarSeriePost(@RequestParam String nombre, @RequestParam String descripcion, @RequestParam Integer anioLanzamiento,
                                   @RequestParam Integer temporadas, @RequestParam Integer capitulos, @RequestParam String[] generos, HttpServletRequest request) {
        Serie serie = new Serie();
        serie.setNombre(nombre);
        serie.setDescripcion(descripcion);
        serie.setAnioLanzamiento(anioLanzamiento);
        serie.setTemporadas(temporadas);
        serie.setCapitulos(capitulos);

        Usuario usuarioLogueado = this.inicioController.traerUsuarioLogueado(request);
        if (usuarioLogueado != null)
            serie.setCargadaPorUsuario(usuarioLogueado);

        Iterable<GeneroDeContenido> generosTotales = repositorioGeneros.findAll();

        for (String genero : generos)
        {
            for (GeneroDeContenido generoT : generosTotales)
            {
                if(genero.equals(generoT.getDescripcion()))
                    serie.agregarGenero(generoT);
            }
        }

        repositorioContenidos.save(serie);
        return "redirect:mensaje/Serie agregada";
    }

    @RequestMapping(value = "agregarLibro")
    public String agregarLibro(Model model, HttpServletRequest request) {
        this.inicioController.cargarUsuarioLogueado(request, model);
        model.addAttribute("generos", repositorioGeneros.findAll());
        return "agregarLibro";
    }

    @RequestMapping(value = "agregarLibro", method = RequestMethod.POST)
    public String agregarLibroPost(@RequestParam String nombre, @RequestParam String descripcion, @RequestParam Integer anioLanzamiento,
                                   @RequestParam String autor, @RequestParam String[] generos, HttpServletRequest request) {
        Libro libro = new Libro();
        libro.setNombre(nombre);
        libro.setDescripcion(descripcion);
        libro.setAnioLanzamiento(anioLanzamiento);
        libro.setAutor(autor);

        Usuario usuarioLogueado = this.inicioController.traerUsuarioLogueado(request);
        if (usuarioLogueado != null)
            libro.setCargadaPorUsuario(usuarioLogueado);

        for (String genero : generos)
        {
            for (GeneroDeContenido generoT : this.traerGeneros())
            {
                if(genero.equals(generoT.getDescripcion()))
                    libro.agregarGenero(generoT);
            }
        }

        repositorioContenidos.save(libro);
        return "redirect:mensaje/Libro agregado";
    }

    @RequestMapping(value = "verContenido/{id}")
    public String verContenido(@PathVariable Integer id, Model model, HttpServletRequest request) {
        this.inicioController.cargarUsuarioLogueado(request, model);
        if (this.repositorioContenidos.findById(id).isPresent()) {
            Contenido contenido = this.repositorioContenidos.findById(id).get();
            model.addAttribute("contenido", contenido);
            model.addAttribute("tipo", contenido.tipo());
            }

        return "verContenido";
    }

    public List<GeneroDeContenido> traerGeneros() {
        List<GeneroDeContenido> listaGeneros = new ArrayList<>();
        this.repositorioGeneros.findAll().forEach(listaGeneros::add);
        return listaGeneros;
    }

    public List<Contenido> traerContenidos() {
        List<Contenido> listaContenidos = new ArrayList<>();
        this.repositorioContenidos.findAll().forEach(listaContenidos::add);
        return listaContenidos;
    }
}
