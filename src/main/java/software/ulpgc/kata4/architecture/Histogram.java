package software.ulpgc.kata4.architecture;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Histogram implements Iterable<Integer> {
    private final Map<Integer, Integer> bins;
    private final Map<String, String> labels;

    public Histogram(Map<String, String> labels) {
        this.bins = new HashMap<>();
        this.labels = labels;
    }


    public void addTo(int bin) {
        bins.put(bin, count(bin) + 1);
    }


    public int count(int bin) {
        return bins.getOrDefault(bin, 0);
    }


    public Set<Integer> bins() {
        return bins.keySet();
    }


    @Override
    public Iterator<Integer> iterator() {
        return bins.keySet().iterator();
    }
    public String title() {
        return labels.getOrDefault("title", "");
    }
    public String x() {
        return labels.getOrDefault("x", "");
    }
    public String y() {
        return labels.getOrDefault("y", "");
    }
    public String legend() {
        return labels.getOrDefault("legend", "");
    }
}
