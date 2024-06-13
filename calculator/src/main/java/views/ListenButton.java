package main.java.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import main.java.world.World;

public class ListenButton implements EventHandler<ActionEvent> {
    private World world;
    private ViewTools viewTools;

    public ListenButton(World world, ViewTools viewTools) {
        this.world = world;
        this.viewTools = viewTools;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        String buttonText = button.getText();

        if(buttonText.equals("C")) {
            world.resetToCalculate();
        }else if (!buttonText.equals("=")) {
            world.setToCalculate(buttonText);
        }else {
            String result = world.evaluateExpression();
            world.resetToCalculate();
            world.setToCalculate(result);
        }
    }
}
