package org.example.testcasegenerator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Title Text
        Text title = new Text("Select Test Case Type");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setFill(Color.WHITE);

        // GridPane for buttons
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        // Enhanced Button Text for better readability
        String[] buttonLabels = {
                "T\nn\nA1 A2 A3 ... An",
                "T\nn m\nA1 A2 A3 ... An",
                "T\nn \nA1 A2 A3 ... An\nB1 B2 B3 ... Bn",
                "n m\na11 a12 ...a1m\na21 a22 ... a2m\n...\nan1 an2 ... anm",
                "t\nn\nA1 B1\nA2 B2\n(t rows of A, B pair)",
                "T\nn m k\nA1 A2 ... An\nB1 B2 ... Bm\nC1 C2 ... Ck",
                "T\nn m\nA1 A2 ... Am\n...\nAn1 An2 ... Anm",
                "n m\nA1 A2 ... Am\n...\nAn1 An2 ... Anm",
                "Custom String\nExample: 0 1 / + -",
                "T\nn m\nPairs (A1 B1)\n...\nPairs (An Bm)",
                "Custom String\nWithout 'n'\nExamples: 0 1, +/-",
                "T\nn m k\nn m k pair\nA1 A2 ... An\nB1 B2 ... Bm"
        };

        // Creating buttons and styling
        Button[] buttons = new Button[buttonLabels.length];
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new Button(buttonLabels[i]);
            buttons[i].setStyle(
                    "-fx-font-family: 'Consolas';" +
                            "-fx-font-size: 14px;" +
                            "-fx-text-fill: #FFFFFF;" +
                            "-fx-background-color: #3C3F41;" +
                            "-fx-border-color: #6C757D;" +
                            "-fx-border-width: 2px;"
            );
            buttons[i].setMinSize(180, 100);
            gridPane.add(buttons[i], i % 3, i / 3);
        }

        // Buttons for ANOTHER TYPE and EXIT
        Button anotherTypeButton = new Button("ANOTHER TYPE");
        anotherTypeButton.setMinSize(180, 50);
        anotherTypeButton.setStyle(
                "-fx-font-family: 'Arial';" +
                        "-fx-font-size: 16px;" +
                        "-fx-background-color: #2D89EF;" +
                        "-fx-text-fill: #FFFFFF;" +
                        "-fx-border-color: #1C6EA4;" +
                        "-fx-border-width: 2px;"
        );

        Button exitButton = new Button("EXIT");
        exitButton.setMinSize(180, 50);
        exitButton.setStyle(
                "-fx-font-family: 'Arial';" +
                        "-fx-font-size: 16px;" +
                        "-fx-background-color: #E81123;" +
                        "-fx-text-fill: #FFFFFF;" +
                        "-fx-border-color: #C50F1F;" +
                        "-fx-border-width: 2px;"
        );
        exitButton.setOnAction(e -> System.exit(0));


        buttons[0].setOnAction(e -> {
            TestCase1 testCaseGenerator = new TestCase1();
            try {
                testCaseGenerator.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        buttons[1].setOnAction(e -> {
            TestCase2 testCaseGenerator = new TestCase2();
            try {
                testCaseGenerator.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        buttons[2].setOnAction(e -> {
            TestCase3 testCaseGenerator = new TestCase3();
            try {
                testCaseGenerator.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        buttons[3].setOnAction(e -> {
            TestCase4 testCaseGenerator = new TestCase4();
            try {
                testCaseGenerator.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        HBox bottomButtons = new HBox(20, anotherTypeButton, exitButton);
        bottomButtons.setAlignment(Pos.CENTER);
        bottomButtons.setPadding(new Insets(20));


        VBox mainLayout = new VBox(20, title, gridPane, bottomButtons);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setStyle("-fx-background-color: #2C2F33;");


        Scene scene = new Scene(mainLayout, 900, 600);
        primaryStage.setTitle("Test Case Generator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
