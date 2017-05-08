package de.hhbk.wizardpdfgen.view;

import de.hhbk.wizardpdfgen.model.base.Configuration;
import de.hhbk.wizardpdfgen.model.persistence.sql.MySqlTemplateDAO;
import de.hhbk.wizardpdfgen.viewmodel.LoginViewModel;
import de.hhbk.wizardpdfgen.viewmodel.TemplateViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import de.hhbk.wizardpdfgen.main.Main;

import java.util.List;

/**
 * Created by monikaschepan on 02.05.17.
 */
public class TemplateView implements FxmlView<TemplateViewModel> {

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
    TemplateViewModel viewModel;

    MySqlTemplateDAO mySqlTemplateDAO;

    ObservableList<Configuration> templateObservableList  = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        templateGenerierenButton.setVisible(false);
        List<Configuration> configurationList = MySqlTemplateDAO.selectAllConfigurations();
        for (Configuration c :configurationList)
        {
            templateObservableList.add(new Configuration(c.getId(), c.getName()));

        }
        listviewTemplate.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listviewTemplate.setItems(templateObservableList);


    }

    public void TemplateGenerierenButtonEvent(MouseEvent mouseEvent) {

        if(TemplateNameTextfield.getText().isEmpty())
        {

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Bitte geben Sie etwas in das Feld ein!");
            alert.showAndWait();
        }
        else
        {

            Main.switchToMain();
            String templateName =  TemplateNameTextfield.getText();
           // Integer idUser =   LoginView.getUserID();

          //  MySqlTemplateDAO.addTemplate(templateName, );

        }





    }

    public void templateListviewEvent(MouseEvent mouseEvent) {
        templateGenerierenButton.setVisible(true);

    }
}
