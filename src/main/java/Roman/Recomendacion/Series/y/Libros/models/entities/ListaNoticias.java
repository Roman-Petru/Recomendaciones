package Roman.Recomendacion.Series.y.Libros.models.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListaNoticias {
    public String status;
    public String totalResults;
    public Articles[] articles;

    @Getter
    @Setter
    public static class Articles {
        public Source source;
        public String author;
        public String title;
        public String description;
        public String url;
        public String urlToImage;
        public String publishedAt;
        public String content;

            @Getter
            @Setter
            public class Source {
                public String id;
                public String name;
            }
    }
}
