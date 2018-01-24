package view;

import javafx.scene.control.Label;

public class ClientView extends View {
    Label time = new Label("hh:mm/hh:mm");
    public ClientView(){
        super();
        setBottom(time);
    }
}
