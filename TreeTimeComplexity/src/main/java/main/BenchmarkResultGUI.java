/*
 * Copyright (C) 2019 JoonaHa
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package main;

import utils.Operations;
import algorithms.MinBinaryHeap;
import algorithms.MinBinomialHeap;
import algorithms.MinFibonaciHeap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import utils.Benchmark;
import utils.InputTypes;

/**
 *
 * @author JoonaHa
 */
public class BenchmarkResultGUI {

    private int inputLenght;
    private int iterations;
    private Operations[] operations;
    private InputTypes sorting;

    /**
     * Class for creating barchart window
     *
     * @param inputLenght lenght of bechmark data
     * @param iterations bechmark iterations
     * @param operations operatins to benchmark
     * @param sorting benchmark data sorting
     */
    public BenchmarkResultGUI(int inputLenght, int iterations, Operations[] operations, InputTypes sorting) {
        this.inputLenght = inputLenght;
        this.iterations = iterations;
        this.operations = operations;
        this.sorting = sorting;

    }

    /**
     * Runs the benchmarks Creates Window with results. Returns javagx scene
     *
     * @return Scene with benchmark charts
     */
    public Scene run() {
        StackPane charts = new StackPane();
        CategoryAxis xAxis
                = new CategoryAxis();

        NumberAxis yAxis = new NumberAxis();

        BarChart<String, Number> barchart = new BarChart<>(xAxis, yAxis);
        barchart.setTitle("Benchmark Averges");

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Binary Heap");
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Binomial Heap");
        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("Fibonacci Heap");
        ObservableList<Label> list = FXCollections.<Label>observableArrayList();

        Benchmark benBinary = new Benchmark(new MinBinaryHeap(), this.iterations, this.inputLenght, this.sorting);
        Benchmark benBinomial = new Benchmark(new MinBinomialHeap(), this.iterations, this.inputLenght, this.sorting);
        Benchmark benFibonacci = new Benchmark(new MinFibonaciHeap(), this.iterations, this.inputLenght, this.sorting);

        for (Operations operation : operations) {

            if (operation == Operations.ADD) {
                xAxis.getCategories().add("add");

                long[] bs1 = benBinary.testAdd();
                list.add(new Label("Binary add min/max: " + bs1[0] + " / " + bs1[1]));
                series1.getData().add(new XYChart.Data<>("add", bs1[2] / this.inputLenght));

                long[] bs2 = benBinomial.testAdd();
                list.add(new Label("Binomial add min/max: " + bs2[0] + " / " + bs2[1]));
                series2.getData().add(new XYChart.Data<>("add", bs2[2] / this.inputLenght));

                long[] bs3 = benFibonacci.testAdd();
                list.add(new Label("Fibonacci add min/max: " + bs3[0] + " / " + bs3[1]));
                series3.getData().add(new XYChart.Data<>("add", bs3[2] / this.inputLenght));
            }
            if (operation == Operations.PEEK) {

                long[] bs1 = benBinary.testPeek();
                list.add(new Label("Binary peek min/max: " + bs1[0] + " / " + bs1[1]));
                series1.getData().add(new XYChart.Data<>("peek", bs1[2] / this.inputLenght));

                long[] bs2 = benBinomial.testPeek();
                list.add(new Label("Binomial peek min/max: " + bs2[0] + " / " + bs2[1]));
                series2.getData().add(new XYChart.Data<>("peek", bs2[2] / this.inputLenght));

                long[] bs3 = benFibonacci.testPeek();
                list.add(new Label("Fibonacci peek min/max: " + bs3[0] + " / " + bs3[1]));
                series3.getData().add(new XYChart.Data<>("peek", bs3[2] / this.inputLenght));
            }
            if (operation == Operations.POP) {

                long[] bs1 = benBinary.testPop();
                list.add(new Label("Binary pop min/max: " + bs1[0] + " / " + bs1[1]));
                series1.getData().add(new XYChart.Data<>("pop", bs1[2] / this.inputLenght));

                long[] bs2 = benBinomial.testPop();
                list.add(new Label("Binomial pop min/max: " + bs2[0] + " / " + bs2[1]));
                series2.getData().add(new XYChart.Data<>("pop", bs2[2] / this.inputLenght));

                long[] bs3 = benFibonacci.testPop();
                list.add(new Label("Fibonacci pop min/max: " + bs3[0] + " / " + bs3[1]));
                series3.getData().add(new XYChart.Data<>("pop", bs3[2] / this.inputLenght));
            }
            if (operation == Operations.DELETE) {
                long[] bs1 = benBinary.testDelete();
                list.add(new Label("Binary delete min/max: " + bs1[0] + " / " + bs1[1]));
                series1.getData().add(new XYChart.Data<>("delete", bs1[2] / this.inputLenght));

                long[] bs2 = benBinomial.testDelete();
                list.add(new Label("Binomial delete min/max: " + bs2[0] + " / " + bs2[1]));
                series2.getData().add(new XYChart.Data<>("delete", bs2[2] / this.inputLenght));

                long[] bs3 = benFibonacci.testDelete();
                list.add(new Label("Fibonacci delete min/max: " + bs3[0] + " / " + bs3[1]));
                series3.getData().add(new XYChart.Data<>("delete", bs3[2] / this.inputLenght));
            }
            if (operation == Operations.DECREASE_KEY) {
                long[] bs1 = benBinary.testDecreaseKey();
                list.add(new Label("Binary decerease key min/max: " + bs1[0] + " / " + bs1[1]));
                series1.getData().add(new XYChart.Data<>("decerease key", bs1[2] / this.inputLenght));

                long[] bs2 = benBinomial.testDecreaseKey();
                list.add(new Label("Binomial decerease key min/max: " + bs2[0] + " / " + bs2[1]));
                series2.getData().add(new XYChart.Data<>("decerease key", bs2[2] / this.inputLenght));

                long[] bs3 = benFibonacci.testDecreaseKey();
                list.add(new Label("Fibonacci decerease key min/max: " + bs3[0] + " / " + bs3[1]));
                series3.getData().add(new XYChart.Data<>("decerease key", bs3[2] / this.inputLenght));
            }
        }

        ListView<Label> minmaxlist = new ListView<>(list);
        barchart.getData().addAll(series1, series2, series3);
        charts.getChildren().add(barchart);

        VBox cs = new VBox(charts, minmaxlist);

        Scene chartScene = new Scene(cs, 700, 650);

        return chartScene;
    }

}
