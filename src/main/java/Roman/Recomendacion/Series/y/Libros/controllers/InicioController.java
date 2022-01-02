package Roman.Recomendacion.Series.y.Libros.controllers;

import Roman.Recomendacion.Series.y.Libros.models.entities.Usuario;
import Roman.Recomendacion.Series.y.Libros.models.repositories.RepositorioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class InicioController {

    @Autowired
    private RepositorioUsuarios repositorio;

    @RequestMapping(value = "")
    public String inicio(Model model, HttpServletRequest request) {
        this.cargarUsuarioLogueado(request, model);
        return "inicio";
    }

    @RequestMapping(value = "mensaje/{mensaje}")
    public String mensaje(@PathVariable String mensaje, Model model, HttpServletRequest request) {
        this.cargarUsuarioLogueado(request, model);
        model.addAttribute("mensaje", mensaje);
        return "mensaje";
    }

    @RequestMapping(value = "login")
    public String login(Model model, HttpServletRequest request) {
        this.cargarUsuarioLogueado(request, model);
        return "login";
    }

    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String loginPost(@RequestParam String nombreUsuario, @RequestParam String password , HttpServletRequest request) {


        List<Usuario> listaUsuarios = this.repositorio.findBynombreUsuario(nombreUsuario);

        if (listaUsuarios.size() == 1 &&
                (listaUsuarios.get(0).getPassword().equals(password)))
        {
            HttpSession session = request.getSession();
            session.setAttribute("userID", listaUsuarios.get(0).getId());
            return "redirect:";
        }
        return "redirect:mensaje/Nombre de usuario o password incorrectos";
    }

    public void cargarUsuarioLogueado (HttpServletRequest request, Model model) {

        if(!request.getSession().isNew() && request.getSession().getAttribute("userID") != null) {
            Usuario usuarioLogueado = repositorio.findById(new Integer(request.getSession().getAttribute("userID").toString())).get();
            model.addAttribute("usuarioLogueado", usuarioLogueado);
        }
    }

    public Usuario traerUsuarioLogueado (HttpServletRequest request) {

        if(!request.getSession().isNew() && request.getSession().getAttribute("userID") != null) {
            return repositorio.findById(new Integer(request.getSession().getAttribute("userID").toString())).get();
        }
        else return null;
    }

}
