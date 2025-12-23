package software.ulpgc.kata4.architecture;
import java.util.stream.Stream;
public interface Store {
    Stream<Movie> movies();
}
