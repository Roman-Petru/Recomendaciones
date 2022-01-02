package Roman.Recomendacion.Series.y.Libros.models.repositories;

import Roman.Recomendacion.Series.y.Libros.models.entities.GeneroDeContenido;
import Roman.Recomendacion.Series.y.Libros.models.entities.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RepositorioGeneros extends CrudRepository<GeneroDeContenido, Integer> {
}
