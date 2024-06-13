package main.java.views;

import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import main.java.world.World;

import java.util.ArrayList;

public class ViewTools extends GridPane implements Observer {
    private World world;
    private ArrayList<Button> buttons;
    private static final double BUTTON_SIZE = 60;

    public ViewTools(World world){
        super();
        this.world = world;
        this.buttons = new ArrayList<>();
        this.world.addObserver(this);
        initializeButtons();
        configureGridPane();

        for(Button button : buttons){
            button.setOnAction(new ListenButton(world, this));
        }
    }

    private void initializeButtons(){
        // Create calculator buttons
        Button btnClear = createButton("C");
        Button btnParenLeft = createButton("(");
        Button btnParenRight = createButton(")");
        Button btnMod = createButton("mod");
        Button btnPi = createButton("π");
        Button btn7 = createButton("7");
        Button btn8 = createButton("8");
        Button btn9 = createButton("9");
        Button btnDivide = createButton("÷");
        Button btnSqrt = createButton("√(");
        Button btn4 = createButton("4");
        Button btn5 = createButton("5");
        Button btn6 = createButton("6");
        Button btnMultiply = createButton("×");
        Button btnSquare = createButton("x²");
        Button btn1 = createButton("1");
        Button btn2 = createButton("2");
        Button btn3 = createButton("3");
        Button btnSubtract = createButton("-");
        Button btnEqual = createButton("=");
        btnEqual.setPrefHeight(145);
        Button btn0 = createButton("0");
        btn0.setPrefWidth(135);
        Button btnComma = createButton(",");
        Button btnPercent = createButton("%");
        Button btnAdd = createButton("+");

        // Set up GridPane
        this.add(btnClear, 0, 0);
        this.add(btnParenLeft, 1, 0);
        this.add(btnParenRight, 2, 0);
        this.add(btnMod, 3, 0);
        this.add(btnPi, 4, 0);

        this.add(btn7, 0, 1);
        this.add(btn8, 1, 1);
        this.add(btn9, 2, 1);
        this.add(btnDivide, 3, 1);
        this.add(btnSqrt, 4, 1);

        this.add(btn4, 0, 2);
        this.add(btn5, 1, 2);
        this.add(btn6, 2, 2);
        this.add(btnMultiply, 3, 2);
        this.add(btnSquare, 4, 2);

        this.add(btn1, 0, 3);
        this.add(btn2, 1, 3);
        this.add(btn3, 2, 3);
        this.add(btnSubtract, 3, 3);
        this.add(btnEqual, 4, 3, 1, 2); // Span equal button across 2 rows
        this.add(btn0, 0, 4, 2, 1); // Span 0 button across 2 columns
        this.add(btnComma, 2, 4);
        this.add(btnAdd, 3, 4);

        // Add buttons to the list for potential future use
        buttons.add(btnClear);
        buttons.add(btnParenLeft);
        buttons.add(btnParenRight);
        buttons.add(btnMod);
        buttons.add(btnPi);
        buttons.add(btn7);
        buttons.add(btn8);
        buttons.add(btn9);
        buttons.add(btnDivide);
        buttons.add(btnSqrt);
        buttons.add(btn4);
        buttons.add(btn5);
        buttons.add(btn6);
        buttons.add(btnMultiply);
        buttons.add(btnSquare);
        buttons.add(btn1);
        buttons.add(btn2);
        buttons.add(btn3);
        buttons.add(btnSubtract);
        buttons.add(btnEqual);
        buttons.add(btn0);
        buttons.add(btnComma);
        buttons.add(btnPercent);
        buttons.add(btnAdd);

        for(Button button : buttons){
            button.setStyle("-fx-border-radius: 2px; -fx-border-color: #000");
            button.setStyle("-fx-font-weight: bold");

        }

        btnSubtract.setStyle(("-fx-background-color: rgba(255,165,0,0.8);"));
        btnAdd.setStyle("-fx-background-color: rgba(255,165,0,0.8);");
        btnMultiply.setStyle("-fx-background-color: rgba(255,165,0,0.8);");
        btnDivide.setStyle("-fx-background-color: rgba(255,165,0,0.8);");
        btnEqual.setStyle("-fx-background-color: rgba(0,128,0,0.8);");
        btnClear.setStyle("-fx-background-color: rgba(255,0,0,0.8);");

    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
        return button;
    }

    private void configureGridPane() {
        // Define column and row constraints to make them equal size
        for (int i = 0; i < 5; i++) { // 5 columns
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(20); // Equal width columns
            this.getColumnConstraints().add(column);
        }

        for (int i = 0; i < 5; i++) { // 5 rows
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(20); // Equal height rows
            this.getRowConstraints().add(row);
        }
    }

    @Override
    public void react() {
        // Implement reaction to model changes if necessary
    }

    // Getters
    public World getWorld(){
        return world;
    }
    private ArrayList<Button> getButtons(){
        return buttons;
    }

    // Setters
    public void setWorld(World world){
        this.world = world;
    }
}
