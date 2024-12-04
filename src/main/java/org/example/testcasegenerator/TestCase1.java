package org.example.testcasegenerator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TestCase1 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Main layout
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #2C2F33;"); // Dark theme

        // Title
        Label title = new Label("TEST CASE GENERATOR");
        title.setFont(Font.font("Arial", 16));
        title.setTextFill(Color.WHITE);

        HBox titleBox = new HBox(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(10, 0, 20, 0));
        root.setTop(titleBox);

        // Center content
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(15);
        grid.setPadding(new Insets(20));

        // Input Fields and Labels
        Label tLabel = createLabel("T =");
        TextField tField = createTextField("5");

        Label nRangeLabel = createRangeLabel("<= n <=");
        TextField nMinField = createTextField("1");
        TextField nMaxField = createTextField("10");

//        Label mRangeLabel = createRangeLabel("<= m <=");
//        TextField mMinField = createTextField("1");
//        TextField mMaxField = createTextField("20");

        Label aiRangeLabel = createRangeLabel("<= Ai <=");
        TextField aiMinField = createTextField("0");
        TextField aiMaxField = createTextField("50");

        // Add inputs to grid
        grid.add(tLabel, 0, 0);
        grid.add(tField, 1, 0);

        grid.add(nMinField, 0, 1);
        grid.add(nRangeLabel, 1, 1);
        grid.add(nMaxField, 2, 1);
//
//        grid.add(mMinField, 0, 2);
//        grid.add(mRangeLabel, 1, 2);
//        grid.add(mMaxField, 2, 2);

        grid.add(aiMinField, 0, 3);
        grid.add(aiRangeLabel, 1, 3);
        grid.add(aiMaxField, 2, 3);

        // VBox to hold grid and other content
        VBox centerBox = new VBox(20, grid);
        centerBox.setAlignment(Pos.CENTER);
        root.setCenter(centerBox);

        // Buttons
        Button homeButton = createButton("HOME");
        // Home Button Action
        homeButton.setOnAction(e -> {
            try {
                Main mainApp = new Main(); // Assuming Main.java contains a start method
                mainApp.start(primaryStage); // Redirecting to Main.java scene
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button generateButton = createButton("GENERATE");
        Button exitButton = createButton("EXIT");

        HBox buttonBox = new HBox(20, homeButton, generateButton, exitButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(20, 0, 10, 0));
        root.setBottom(buttonBox);

        // Exit Button Action
        exitButton.setOnAction(e -> System.exit(0));

        // Generate Button Action - Generate test cases and save to a text file
        generateButton.setOnAction(e -> {
            try {
                // Get values from input fields
                int T = Integer.parseInt(tField.getText());
                int nMin = Integer.parseInt(nMinField.getText());
                int nMax = Integer.parseInt(nMaxField.getText());
//                int mMin = Integer.parseInt(mMinField.getText());
//                int mMax = Integer.parseInt(mMaxField.getText());
                int aiMin = Integer.parseInt(aiMinField.getText());
                int aiMax = Integer.parseInt(aiMaxField.getText());

                // Generate test cases
                StringBuilder testCases = new StringBuilder();
                Random rand = new Random();

                testCases.append(T).append("\n"); // Write the number of test cases

                for (int i = 0; i < T; i++) {
                    int n = rand.nextInt(nMax - nMin + 1) + nMin;
                    testCases.append(n).append("\n");

                    // Generate Ai values for this test case
                    for (int j = 0; j < n; j++) {
                        int ai = rand.nextInt(aiMax - aiMin + 1) + aiMin;
                        testCases.append(ai).append(" ");
                    }
                    testCases.append("\n");
                }

                // Write to a text file
                File file = new File("TestCase#1.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(testCases.toString());
                writer.close();

                // Notify user
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Test Cases Generated");
                alert.setContentText("Test cases have been successfully written to 'testcases.txt'.");
                alert.showAndWait();

            } catch (IOException ex) {
                ex.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("File Error");
                alert.setContentText("There was an error writing the test cases to the file.");
                alert.showAndWait();
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input Error");
                alert.setHeaderText("Invalid Input");
                alert.setContentText("Please make sure all fields contain valid numbers.");
                alert.showAndWait();
            }
        });

        // Set Scene and Stage
        Scene scene = new Scene(root, 500, 400);
        primaryStage.setTitle("Test Case Generator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper methods for consistent styling
    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Arial", 14));
        label.setTextFill(Color.WHITE);
        return label;
    }

    private Label createRangeLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Arial", 14));
        label.setTextFill(Color.WHITE);
        label.setAlignment(Pos.CENTER);
        return label;
    }

    private TextField createTextField(String placeholder) {
        TextField textField = new TextField(placeholder);
        textField.setPrefWidth(60);
        return textField;
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", 14));
        button.setStyle("-fx-background-color: #E0E0E0; -fx-border-color: #000000; -fx-border-width: 2px;");
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
