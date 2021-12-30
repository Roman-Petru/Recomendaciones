package Roman.Recomendacion.Series.y.Libros.controllers;


import Roman.Recomendacion.Series.y.Libros.models.entities.NivelAdmin;
import Roman.Recomendacion.Series.y.Libros.models.entities.Usuario;
import Roman.Recomendacion.Series.y.Libros.models.repositories.RepositorioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioController {

    @Autowired
    private RepositorioUsuarios repositorio;

    @RequestMapping(value = "registrarse")
    public String registrarse() {
        return "registrarse";
    }

    @RequestMapping(value = "registrarse", method = RequestMethod.POST)
    public String registrarsePost(@RequestParam String nombreCompleto, @RequestParam String nombreUsuario, @RequestParam String password ) {

        Usuario nuevo_user = new Usuario();
        nuevo_user.setNivel_admin(NivelAdmin.COMUN);
        nuevo_user.setNombreUsuario(nombreUsuario);
        nuevo_user.setNombreCompleto(nombreCompleto);
        nuevo_user.setPassword(password);

        this.repositorio.save(nuevo_user);

        return "redirect:";
    }



}
