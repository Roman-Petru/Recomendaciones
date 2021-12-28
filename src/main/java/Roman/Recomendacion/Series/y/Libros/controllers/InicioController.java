package Roman.Recomendacion.Series.y.Libros.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InicioController {

    @RequestMapping(value = "")
    public String inicio() {
        return "inicio";
    }
}
