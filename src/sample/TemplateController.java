package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

/**
 * Created by monikaschepan on 02.05.17.
 */
public class TemplateController {

    @FXML
    ListView listviewTemplate;

    @FXML
    Button templateGenerierenButton;

    @FXML
    TextField TemplateNameTextfield;

    @FXML
    Label templateLabel;

    @FXML
    Label templateNameLabel;

    public void TemplateGenerierenButtonEvent(MouseEvent mouseEvent) {

        Main.switchToMain();
    }
}
