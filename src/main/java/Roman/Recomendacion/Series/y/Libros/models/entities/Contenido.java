package Roman.Recomendacion.Series.y.Libros.models.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@DiscriminatorColumn(name="tipo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter @Setter
public abstract class Contenido extends Persistente {

    @Column
    private String nombre;

    @Column(columnDefinition="TEXT")
    private String descripcion;

    @Column(name = "anioLanzamiento")
    private Integer anioLanzamiento;

    @ManyToOne
    @JoinColumn(name="usuario_id" , referencedColumnName = "id")
    private Usuario cargadaPorUsuario;

    @Column(name = "fechaCarga")
    private Date fechaCarga;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<GeneroDeContenido> generos = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "contenido")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Review> reviews = new ArrayList<>();

    @Transient
    private Integer ranking;

    @Transient
    private Double puntaje;

    @Transient
    private String puntajeLogueado = "-";

    public Contenido(){
        this.fechaCarga = new Date();
    }

    abstract public String tipo();

    public void agregarReview(Review review) {
        this.reviews.add(review);
    }
    public void agregarGenero(GeneroDeContenido genero) {
        this.generos.add(genero);
    }

    public Double puntajePromedio() {

        if (this.reviews.size() == 0)
            return 0.0;

        Double puntaje = new Double(this.reviews.stream().map(a->a.getPutanje()).reduce(0, (a, b) -> a + b)) / (this.reviews.size());
        return new BigDecimal(puntaje.toString()).setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
}
