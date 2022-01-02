package Roman.Recomendacion.Series.y.Libros.models.repositories;

import Roman.Recomendacion.Series.y.Libros.models.entities.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RepositorioReviews  extends CrudRepository<Review, Integer> {
}
