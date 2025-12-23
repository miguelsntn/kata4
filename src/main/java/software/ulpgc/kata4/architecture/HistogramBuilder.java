package software.ulpgc.kata4.architecture;

import java.util.function.Function;


public class HistogramBuilder {
    private final List<Movie> movies;


    public HistogramBuilder(List<Movie> movies) {
        this.movies = movies;
    }


    public Histogram build(Function<Movie, Integer> binarize) {
        Histogram histogram = new Histogram();
        return histogram;
    }
}



}