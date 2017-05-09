package de.hhbk.wizardpdfgen.view;

import de.hhbk.wizardpdfgen.main.Main;
import de.hhbk.wizardpdfgen.model.persistence.sql.DidacticWizardDAO;
import de.hhbk.wizardpdfgen.viewmodel.MainWindowViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by monikaschepan on 02.05.17.
 */
public class MainWindowView implements FxmlView<MainWindowViewModel> {

    @FXML
    Label guideLabel;

    @FXML
    Label trainingYearLabel;

    @FXML
    Label skilledOccupationLabel;

    @FXML
    Button userAdminButton;

    @FXML
    Button generateTemplateButton;

    @FXML
    Button deleteTemplateButton;

    @FXML
    Button generatePDFButton;

    @FXML
    ListView listviewMainWIndow;

    @FXML
    ComboBox<String> skilledOccupationComboBox;

    @FXML
    ComboBox<Integer> trainingYearComboBox;

    @InjectViewModel
    MainWindowViewModel viewModel;

    private static Logger logger = LogManager.getLogger(MainWindowView.class);

    @FXML
    public void initialize() {

        viewModel.initialize();

        this.skilledOccupationComboBox.setItems(viewModel.getSkilledOccupationList());
        this.trainingYearComboBox.setItems(viewModel.getTrainingYearList());
        this.guideLabel.textProperty()
                .bindBidirectional(viewModel.guideTextProperty());

        this.userAdminButton.visibleProperty().bind(viewModel.userAdminButtonVisibilityProperty());
        this.deleteTemplateButton.visibleProperty().bind(viewModel.deleteTemplateButtonVisibilityProperty());
        this.generateTemplateButton.visibleProperty().bind(viewModel.generateTemplateButtonVisibilityProperty());
        this.generatePDFButton.visibleProperty().bind(viewModel.generatePDFButtonVisibilityProperty());

        this.generatePDFButton.disableProperty().bind(viewModel.generatePDFButtonEnabledProperty());

       // TODO Delete
        trainingYearComboBox.getItems().setAll(1, 2, 3);

    }


    /**
     *  This method is triggerd by selecting one of the displayed skilled occupation.
     *  It will call a funtion of the underlying its viewModel: {@link MainWindowViewModel#updateTrainingYearBySkilledOccupation()}
     * @param actionEvent source of event
     */
    public void skilledOccupationComboBoxEvent(ActionEvent actionEvent) {
        viewModel.updateTrainingYearBySkilledOccupation();
    }

    /**
     * Event für die ComboBox für das Ausbildungsjahr
     *
     * @param actionEvent source of event
     */
    public void ausbidungsjahrComboBoxEvent(ActionEvent actionEvent) {
        this.generatePDFButton.disableProperty().bind(viewModel.generatePDFButtonEnabledProperty());
    }

    /**
     * @param mouseEvent source of event
     */
    public void pdfGenerierenButtonEvent(MouseEvent mouseEvent) {

    }

    /**
     *
     * @param mouseEvent source of event
     */
    public void manageTemplateButtonEvent(MouseEvent mouseEvent) {
        // System.out.print(LoginViewModel.currentUser.getUser());
        Main.switchToTemplate();
    }

    /**
     * @param mouseEvent source of event
     */
    public void deleteTemplateButtonEvent(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Möchten Sie das Template wirklich löschen?");
        alert.showAndWait();
    }

    /**
     *
     * @param mouseEvent source of event
     */
    public void benutzerverwaltungButtonEvent(MouseEvent mouseEvent) {
        Main.switchToUserAdmin();
    }
}
