package Roman.Recomendacion.Series.y.Libros.models.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.util.converter.LocalDateStringConverter;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class Contenido extends Persistente {

    private String nombre;
    private Integer anioLanzamiento;
    private Usuario cargadaPorUsuario;
    private Date fechaCarga;
    private List<GeneroDeContenido> generos = new ArrayList<>();
    private List<Review> reviews = new ArrayList<>();

    public Contenido(){
        this.fechaCarga = new Date();
    }

    public void agregarReview(Review review) {
        this.reviews.add(review);
    }

    public Double puntajePromedio() {

        return new Double(this.reviews.stream().map(a->a.getPutanje()).reduce(0, (a, b) -> a + b)) / (this.reviews.size()); }
}
