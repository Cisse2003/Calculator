package main.java.views;

import javafx.scene.control.Button;
import main.java.world.World;

import java.util.ArrayList;

public class ViewTools implements Observer {
    private World world;
    private ArrayList<Button> buttons;

    public ViewTools(World world){
        this.world = world;
        this.buttons = new ArrayList<>();
    }

    private void initializeButtons(){}

    @Override
    public void react() {

    }

    //Getters
    private World getWorld(){
        return world;
    }

    // Setters
    private void setWorld(World world){
        this.world = world;
    }
}
