package software.ulpgc.kata4.application;

import software.ulpgc.kata4.architecture.Histogram;
import software.ulpgc.kata4.architecture.HistogramBuilder;
import software.ulpgc.kata4.architecture.Movie;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // Composición funcional de la aplicación
        Display.create().show(histogramOf(filter(movies())));
    }
    private static Stream<Movie> filter(Stream<Movie> movies) {
        return movies
                .filter(m->m.year() >= 1900)
                .filter(m->m.year() <=2025);
    }

    private static Histogram histogramOf(Stream<Movie> movies) {
        return HistogramBuilder.with(movies)
                .title("Movies per year")
                .x("Year")
                .y("Count")
                .legend("Movies")
                .build(Movie::year);
    }

    private static Stream<Movie> movies() {
        return new RemoteStore(Main::fromTsv).movies();
    }

    // ... (Métodos de parsing fromTsv igual que en Kata3) ...
    private static Movie fromTsv(String s) {
        return fromTsv(s.split("\t"));
    }

    private static Movie fromTsv(String[] split) {
        return new Movie(split[2], toInt(split[5]), toInt(split[7]));
    }

    private static int toInt(String s) {
        if (s.equals("\\N")) return -1;
        return Integer.parseInt(s);
    }
}