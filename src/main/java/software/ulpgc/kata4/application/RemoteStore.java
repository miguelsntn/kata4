package software.ulpgc.kata4.application;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;
import java.util.function.Function;
import java.util.stream.Stream;
import software.ulpgc.kata4.architecture.Movie;
import software.ulpgc.kata4.architecture.Store;

public class RemoteStore implements Store {
    private final Function<String, Movie> deserialize;

    public RemoteStore(Function<String, Movie> deserialize) {
        this.deserialize = deserialize;
    }

    @Override
    public Stream<Movie> movies() {
        try{
            return streamIn(new URL("https://datasets.imdbws.com/title.basics.tsv.gz"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private Stream<Movie> streamIn(URL url) throws IOException {
        return streamIn(url.openConnection());
    }
    private Stream<Movie> streamIn(URLConnection connection) throws IOException {
        return streamIn(unzip(connection.getInputStream()));
    }

    private Stream<Movie> streamIn(InputStream inputStream) {
        return streamIn(toReader(inputStream)).onClose(()->close(inputStream));
    }

    private Stream<Movie> streamIn(BufferedReader reader) {
        return reader.lines()
                .skip(1)
                .map(deserialize);
    }
    private void close(InputStream inputStream) {
        try{
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private BufferedReader toReader(InputStream inputStream){
        return new BufferedReader(new InputStreamReader(inputStream));
    }
    private InputStream unzip(InputStream inputStream) throws IOException {
        return new GZIPInputStream(new BufferedInputStream(inputStream, 4096));
    }
}

