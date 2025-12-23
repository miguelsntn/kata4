package software.ulpgc.kata4.application;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.XYSeries;
import software.ulpgc.kata4.architecture.Histogram;
import javax.swing.*;
import java.awt.*;

public class Display extends JFrame {
    public static Display create() {
        return new Display();
    }
    private Display() throws HeadlessException {
        this.setTitle("Histogram Display");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void show (Histogram histogram) {
        this.getContentPane().add(chartPanelWith(histogram));
        this.setVisible(true);
    }
    public Component chartPanelWith(Histogram histogram) {
        return new ChartPanel(chartOf(histogram));
    }
    private JFreeChart chartOf(Histogram histogram) {
        return ChartFactory.createHistogram(
                histogram.title(),
                histogram.x(),
                histogram.y(),
                datasetOf(histogram)
        );
    }
    private XYSeriesCollection datasetOf(Histogram histogram) {
        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(seriesOf(histogram));
        return collection;
    }
    private XYSeries seriesOf(Histogram histogram) {
        XYSeries series = new XYSeries(histogram.legend());
        histogram.forEach(bin->series.add((double) bin, histogram.count(bin)));
        return series;
    }
}
