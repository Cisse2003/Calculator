package main.java.views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import main.java.world.World;


public class ViewResult extends HBox implements Observer {
    private World world;
    private Label result;

    public ViewResult(World world){
        super();
        this.world = world;
        this.result = new Label();
        this.result.setText("0");
        this.world.addObserver(this);

        this.setPrefHeight(50);
        this.setPrefWidth(150);
        this.setStyle("-fx-border-color: black");
        this.setPadding(new Insets(10, 10, 10, 10));

        this.getChildren().add(result);
    }

    @Override
    public void react() {
        if(!world.getToCalculate().equals("")){
            result.setText(world.getToCalculate());
        }else{
            result.setText("0");
        }
    }

    // Getters
    public World getWorld() {
        return world;
    }

    public Label getResult() {
        return result;
    }

    // Setters
    public void setWorld(World world) {
        this.world = world;
    }

    public void setResult(Label result) {
        this.result = result;
    }
}
