package view;

import javafx.scene.control.Label;

public class ClientView extends View {
    private Label time = new Label("hh:mm/hh:mm");
    public ClientView(){
        super();
        setBottom(time);
    }

    public void setTime(String timeString){
        time.setText(timeString);
    }
}
