package main;

import algorithms.MinBinaryHeap;
import algorithms.MinBinomialHeap;
import algorithms.MinFibonaciHeap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * TODO Javafx palceholder template
 *
 * @author JoonaHa
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        StackPane root = new StackPane();

        //Barchart
        CategoryAxis xAxis
                = new CategoryAxis(FXCollections.<String>observableArrayList(Arrays.asList("peek", "add", "delete", "pop", "decrease key")));

        NumberAxis yAxis = new NumberAxis();

        BarChart<String, Number> barchart = new BarChart<>(xAxis, yAxis);
        barchart.setTitle("Benchmark Averges");

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("BinaryHeap");
        series1.getData().add(new XYChart.Data<>("peek", new Benchmark(new MinBinaryHeap(), 100, createTestData(4000).stream().mapToInt(Integer::intValue).toArray()).testPeek()[2] / 1000));
        series1.getData().add(new XYChart.Data<>("add", new Benchmark(new MinBinaryHeap(), 100, createTestData(4000).stream().mapToInt(Integer::intValue).toArray()).testAdd()[2] / 1000));
        series1.getData().add(new XYChart.Data<>("delete", new Benchmark(new MinBinaryHeap(), 100, createTestData(4000).stream().mapToInt(Integer::intValue).toArray()).testDelete()[2] / 1000));
        series1.getData().add(new XYChart.Data<>("pop", new Benchmark(new MinBinaryHeap(), 100, createTestData(4000).stream().mapToInt(Integer::intValue).toArray()).testPop()[2] / 1000));
        series1.getData().add(new XYChart.Data<>("decrease key", new Benchmark(new MinBinaryHeap(), 100, createTestData(4000).stream().mapToInt(Integer::intValue).toArray()).testDecreaseKey()[2] / 1000));

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("BinomialHeap");
        series2.getData().add(new XYChart.Data<>("peek", new Benchmark(new MinBinomialHeap(), 100, createTestData(4000).stream().mapToInt(Integer::intValue).toArray()).testPeek()[2] / 1000));
        series2.getData().add(new XYChart.Data<>("add", new Benchmark(new MinBinomialHeap(), 100, createTestData(4000).stream().mapToInt(Integer::intValue).toArray()).testAdd()[2] / 1000));
        series2.getData().add(new XYChart.Data<>("delete", new Benchmark(new MinBinomialHeap(), 100, createTestData(4000).stream().mapToInt(Integer::intValue).toArray()).testDelete()[2] / 1000));
        series2.getData().add(new XYChart.Data<>("pop", new Benchmark(new MinBinomialHeap(), 100, createTestData(4000).stream().mapToInt(Integer::intValue).toArray()).testPop()[2] / 1000));
        series2.getData().add(new XYChart.Data<>("decrease key", new Benchmark(new MinBinomialHeap(), 100, createTestData(4000).stream().mapToInt(Integer::intValue).toArray()).testDecreaseKey()[2] / 1000));

        barchart.getData().addAll(series1, series2);

        root.getChildren().add(barchart);
        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);

    }

    private static ArrayList<Integer> createTestData(int size) {

        ArrayList<Integer> values = new ArrayList<>();

        for (int i = 0; i < size; i++) {

            values.add(new Random().nextInt(Integer.MAX_VALUE));
        }

        return values;
    }

}
