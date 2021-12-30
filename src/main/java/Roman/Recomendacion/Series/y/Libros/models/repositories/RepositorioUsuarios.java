package Roman.Recomendacion.Series.y.Libros.models.repositories;

import Roman.Recomendacion.Series.y.Libros.models.entities.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface RepositorioUsuarios extends CrudRepository<Usuario, Integer> {

    List<Usuario> findBynombreUsuario(String nombreUsuario);
}
