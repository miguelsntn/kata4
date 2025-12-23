package software.ulpgc.kata4.architecture;

import java.util.function.Function;
import java.util.stream.Stream;
import java.util.HashMap;
import java.util.Map;

public class HistogramBuilder {
    private final Stream<Movie> movies;
    private final Map<String, String> labels;

    public static HistogramBuilder with(Stream<Movie> movies) {
        return new HistogramBuilder(movies);
    }

    public HistogramBuilder(Stream<Movie> movies) {
        this.movies = movies;
        this.labels = new HashMap<>();
    }

    public HistogramBuilder title(String label) {
        labels.put("title", label);
        return this;
    }

    public HistogramBuilder x(String label) {
        labels.put("x", label);
        return this;
    }

    public HistogramBuilder y(String label) {
        labels.put("y", label);
        return this;
    }

    public HistogramBuilder legend(String label) {
        labels.put("legend", label);
        return this;
    }

    public Histogram build(Function<Movie, Integer> binarize) {
        Histogram histogram = new Histogram(labels);
        movies.map(binarize).forEach(histogram::addTo);
        return histogram;
    }
}
