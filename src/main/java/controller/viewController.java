package controller;

import model.Model;
import view.View;

public class viewController {
    private Model model;
    private View view;
    public void link(View view, Model model){
        this.view = view;
        this.model = model;
        //TODO link view and model
    }
}
