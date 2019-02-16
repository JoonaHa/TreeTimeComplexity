package main;

import algorithms.MinBinomialHeap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
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
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ArrayList<Integer> data = 
                new ArrayList<>(Arrays.asList(new Integer[]{466, 6182,1859, 2881 ,5434}));
        
        ArrayList<int[][]> results= new ArrayList<>();

        PriorityQueue<Integer> compHeap = new PriorityQueue<>(data);

        MinBinomialHeap testHeap = new MinBinomialHeap(data);

        for (int i = 0; i < data.size(); i++) {
            int comp = compHeap.poll();
            int test = testHeap.pop();
            results.add(new int[][]{{comp},{test}});

            Boolean x = comp -test == 0;

        }

        launch(args);
    }

    private static ArrayList<Integer> createTestData(int size) {

        ArrayList<Integer> values = new ArrayList<>();

        for (int i = 0; i < size; i++) {

            values.add(new Random().nextInt(9999));
        }

        return values;
    }

}
