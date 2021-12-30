package Roman.Recomendacion.Series.y.Libros.controllers;

import Roman.Recomendacion.Series.y.Libros.models.entities.Usuario;
import Roman.Recomendacion.Series.y.Libros.models.repositories.RepositorioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

        List<Usuario> usuario = this.repositorio.findBynombreUsuario(nombreUsuario);

        if ((usuario.size() == 1) && (usuario.get(0).getPassword().equals(password)))
        {
            HttpSession session = request.getSession();
            session.setAttribute("userID", usuario.get(0).getId());
            return "redirect:";
        }
        return "redirect:login";

    }

    public void cargarUsuarioLogueado (HttpServletRequest request, Model model) {

        if(!request.getSession().isNew() && request.getSession().getAttribute("userID") != null) {
            Usuario usuarioLogueado = repositorio.findById(new Integer(request.getSession().getAttribute("userID").toString())).get();
            model.addAttribute("usuarioLogueado", usuarioLogueado);
        }
    }

}
