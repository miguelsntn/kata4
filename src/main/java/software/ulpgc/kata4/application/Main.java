package software.ulpgc.kata4.application;

import software.ulpgc.kata4.architecture.Histogram;
import software.ulpgc.kata4.architecture.HistogramBuilder;
import software.ulpgc.kata4.architecture.Movie;

public class Main {
    public static void main(String[] args) {
        // Composición funcional de la aplicación

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