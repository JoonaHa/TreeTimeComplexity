package main;

import utils.Operations;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import utils.InputTypes;
/**
 * TODO Javafx palceholder template
 *
 * @author JoonaHa
 */
public class App extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //Checkboxex
        CheckBox addBox = new CheckBox("add");
        CheckBox peekBox = new CheckBox("peek");
        CheckBox popBox = new CheckBox("pop");
        CheckBox deleteBox = new CheckBox("delete");
        CheckBox decreaseBox = new CheckBox("decrease key");
        ObservableList<CheckBox> list = FXCollections.<CheckBox>observableArrayList(peekBox, popBox, addBox, deleteBox, decreaseBox);
        ListView<CheckBox> checkboxses = new ListView<>(list);
        checkboxses.setOrientation(Orientation.HORIZONTAL);
        checkboxses.setPrefSize(300, 50);

        //TexFields and their labels
        TextField inputLenghtTextfield = new TextField();
        inputLenghtTextfield.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        inputLenghtTextfield.setPromptText("Preferably <= 10000");
        Label inputLenghtLabel = new Label("Size of test data:");
        VBox inputLenght = new VBox(inputLenghtLabel, inputLenghtTextfield);
        inputLenght.setAlignment(Pos.CENTER);
        TextField iterationsTextField = new TextField();
        Label iterationstLabel = new Label("How many test iterations:");
        iterationsTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        VBox iterationsText = new VBox(iterationstLabel, iterationsTextField);
        iterationsText.setAlignment(Pos.CENTER);
        HBox textVbox = new HBox();
        textVbox.getChildren().addAll(inputLenght, iterationsText);
        textVbox.setAlignment(Pos.CENTER);
        textVbox.setSpacing(40);

        //dropdown menu
        ChoiceBox sortingChoice = new ChoiceBox(FXCollections.observableArrayList(
                "Random", "Asending", "Descending "));
        sortingChoice.setValue("Random");
        Label sortingLabel = new Label("Sorting for test data:  ");
        HBox sortingHbox = new HBox(sortingLabel, sortingChoice);
        sortingHbox.setAlignment(Pos.CENTER);
        sortingHbox.setPadding(new Insets(20, 10, 20, 10));

        //Run Button and labels
        Label errorLabel = new Label("");
        Button runButton = new Button("Run Benchmark");
        HBox buttonHbox = new HBox(runButton);
        buttonHbox.setAlignment(Pos.CENTER);
        buttonHbox.setPadding(new Insets(20, 10, 40, 10));
        VBox runAndError = new VBox(sortingHbox, errorLabel, buttonHbox);
        runAndError.setAlignment(Pos.CENTER);
        runAndError.setSpacing(20);

        // main pane
        BorderPane menu = new BorderPane();
        menu.setPrefSize(900, 600);
        menu.setCenter(textVbox);
        menu.setTop(checkboxses);
        menu.setBottom(runAndError);

        //init scene
        Scene menuScene = new Scene(menu, 400, 300);

        primaryStage.setScene(menuScene);
        primaryStage.show();

        //Handle button presses
        //Add pressed checkboxes to array
        Operations[] selectedOperations = new Operations[5];
        InputTypes[] sorting = new InputTypes[1];
        sorting[0] = InputTypes.RANDOM;

        addBox.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
            selectedOperations[0] = new_val ? Operations.ADD : null;

        });

        peekBox.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
            selectedOperations[1] = new_val ? Operations.PEEK : null;
        });

        popBox.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
            selectedOperations[2] = new_val ? Operations.POP : null;
        });

        deleteBox.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
            selectedOperations[3] = new_val ? Operations.DELETE : null;
        });

        decreaseBox.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
            selectedOperations[4] = new_val ? Operations.DECREASE_KEY : null;
        });

        //TextField only accept numbers
        inputLenghtTextfield.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                inputLenghtTextfield.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        iterationsTextField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                iterationsTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        //Sortingbox hanbdler
        sortingChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue ov, Number value, Number newValue) {
                switch (newValue.intValue()) {
                    case 1:
                        sorting[0] = InputTypes.ASCENDING;
                        break;
                    case 2:
                        sorting[0] = InputTypes.DESCENDING;
                        break;
                    default:
                        sorting[0] = InputTypes.RANDOM;

                }

            }
        });

        //Run benchmark and open a new window
        runButton.setOnAction((event -> {

            int lenght = 0;
            int iterations = 0;

            try {
                lenght = Integer.parseInt(inputLenghtTextfield.getText().replaceAll("\\s+", ""));

                iterations = Integer.parseInt(iterationsTextField.getText().replaceAll("\\s+", ""));
            } catch (NumberFormatException e) {

                errorLabel.setText("Lenght and iterations field can not be empty!");
                errorLabel.setTextFill(Color.RED);
                errorLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 11));

            }

            boolean atLeasOneSelected = false;
            for (Operations s : selectedOperations) {
                if (s != null) {
                    atLeasOneSelected = true;
                    break;
                }
            }

            if (!atLeasOneSelected) {
                errorLabel.setText("Select atleast one checkbox!");
                errorLabel.setTextFill(Color.RED);
                errorLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 11));

            }

            if (!(lenght == 0 || iterations == 0 || !atLeasOneSelected)) {
                errorLabel.setText("");
                Scene barchart = new BenchmarkResultGUI(lenght, iterations, selectedOperations, sorting[0]).run();

                Stage newstage = new Stage();

                newstage.setScene(barchart);
                newstage.show();
            }

        }));

    }

}
