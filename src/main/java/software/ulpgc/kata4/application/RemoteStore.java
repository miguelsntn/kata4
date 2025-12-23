package software.ulpgc.kata4.application;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.function.Function;


public class RemoteStore{
    private final Function<String, Movie> deserialize;

    public RemoteStore(Function<String, Movie> deserialize) {
        this.deserialize = deserialize;
    }

    @Override
    public List<Movie> loadAll() {
        try{
            return loadFrom(new URL("https://datasets.imdbws.com/title.basics.tsv.gz"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private List<Movie> loadFrom(URL url) throws IOException {
        return loadFrom(url.openConnection());
    }
    private List<Movie> loadFrom(URLConnection connection) throws IOException {
        try(InputStream is = unzip(connection.getInputStream())) {
            return loadFrom(is);
        }
    }

    private Movie toMovie(String line) {
        return deserialize.apply(line);
    }
    private BufferedReader toReader(InputStream is){
        return new BufferedReader(new InputStreamReader(is));
    }
    private InputStream unzip(InputStream inputStream) throws IOException {
        return new GZIPInputStream(new BufferedInputStream(inputStream, 4096));
    }
}
