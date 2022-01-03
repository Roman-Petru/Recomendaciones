package Roman.Recomendacion.Series.y.Libros.controllers;


import Roman.Recomendacion.Series.y.Libros.models.entities.NivelAdmin;
import Roman.Recomendacion.Series.y.Libros.models.entities.Usuario;
import Roman.Recomendacion.Series.y.Libros.models.repositories.RepositorioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UsuarioController {

    @Autowired
    private RepositorioUsuarios repositorio;

    @Autowired
    private InicioController inicioController;

    @RequestMapping(value = "registrarse")
    public String registrarse() {
        return "registrarse";
    }

    @RequestMapping(value = "registrarse", method = RequestMethod.POST)
    public String registrarsePost(@RequestParam String nombreCompleto, @RequestParam String email, @RequestParam String nombreUsuario, @RequestParam String password ) {

        if (this.repositorio.findBynombreUsuario(nombreUsuario).size() > 0)
            return "redirect:mensaje/Ya existe el nombre de usuario";

        Usuario nuevo_user = new Usuario();
        nuevo_user.setNivel_admin(NivelAdmin.COMUN);
        nuevo_user.setNombreUsuario(nombreUsuario);
        nuevo_user.setNombreCompleto(nombreCompleto);
        nuevo_user.setEmail(email);
        nuevo_user.setPassword(password);

        this.repositorio.save(nuevo_user);

        return "redirect:";
    }

    @RequestMapping(value = "perfil")
    public String perfil(Model model, HttpServletRequest request) {
        inicioController.cargarUsuarioLogueado(request, model);
        return "perfil";
    }

    @RequestMapping(value = "modificarUser/{id}", method = RequestMethod.POST)
    public String modificarUser(@PathVariable Integer id, @RequestParam String nombreCompleto, @RequestParam String email, @RequestParam String password, @RequestParam String oldPassword) {
        if (!this.repositorio.findById(id).isPresent())
            return "redirect:../mensaje/No existe el usuario";

        Usuario userAModificar = this.repositorio.findById(id).get();

        if (!userAModificar.getPassword().equals(oldPassword))
            return "redirect:../mensaje/Passowrd incorrecto";

        userAModificar.setNombreCompleto(nombreCompleto);
        userAModificar.setPassword(password);
        userAModificar.setEmail(email);
        this.repositorio.save(userAModificar);
        return "redirect:../mensaje/Usuario modificado";
    }
}
