package Roman.Recomendacion.Series.y.Libros.models.entities;

import Roman.Recomendacion.Series.y.Libros.models.entities.notificador.ArmadorMensajeRecomendacion;
import Roman.Recomendacion.Series.y.Libros.models.entities.validador.ValidadorRecomendacion;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Recomendador {

    private ValidadorRecomendacion validadorRecomendacion;

    public Recomendador() {
        validadorRecomendacion = new ValidadorRecomendacion();
    }

    public void recomendar (List<Contenido> listaContenidos, List<Usuario> listaUsuarios){

        for (Usuario usuario : listaUsuarios)
        {
            ParametrosRecomendacion userParams = usuario.getParametrosRecomendacion();
            if (userParams == null || !userParams.getActivo())
                continue;

            Recomendacion recomendacion = new Recomendacion();
            recomendacion.setUsuario(usuario);

            for (Contenido contenido : listaContenidos)
            {
                if (validadorRecomendacion.validarRecomendacion(userParams, contenido))
                    recomendacion.agregarRecomendacion(contenido);

                if (recomendacion.recomendaciones.size() > 2 )
                    break;
            }

            if (recomendacion.recomendaciones.size() == 0)
                continue;

            recomendacion.recomendar();
        }


    }

    @Getter    @Setter
    private class Recomendacion{
        private Usuario usuario;
        private List<Contenido> recomendaciones = new ArrayList<>();

        public void agregarRecomendacion(Contenido contenido){
            recomendaciones.add(contenido);
        }

        public void recomendar(){
            ArmadorMensajeRecomendacion armadorMsj = new ArmadorMensajeRecomendacion();
            armadorMsj.setContenidos(this.recomendaciones);

            usuario.enviarMail(armadorMsj.armarMensaje());
        }
    }
}
