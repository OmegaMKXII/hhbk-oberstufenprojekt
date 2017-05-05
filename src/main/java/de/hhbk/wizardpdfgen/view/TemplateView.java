package de.hhbk.wizardpdfgen.view;

import de.hhbk.wizardpdfgen.main.Main;
import de.hhbk.wizardpdfgen.viewmodel.LoginViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * Created by monikaschepan on 02.05.17.
 */
public class TemplateView implements FxmlView<LoginViewModel> {

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


    @InjectViewModel
    LoginViewModel viewModel;

    public void TemplateGenerierenButtonEvent(MouseEvent mouseEvent) {

        Main.switchToMain();
    }
}
