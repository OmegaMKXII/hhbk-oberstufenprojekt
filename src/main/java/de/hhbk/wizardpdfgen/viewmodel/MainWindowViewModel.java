package de.hhbk.wizardpdfgen.viewmodel;

import de.hhbk.wizardpdfgen.main.Main;
import de.hhbk.wizardpdfgen.model.base.User;
import de.hhbk.wizardpdfgen.model.enums.AuthorisationLevel;
import de.hhbk.wizardpdfgen.model.enums.DBType;
import de.hhbk.wizardpdfgen.model.persistence.sql.DidacticWizardDAO;
import de.hhbk.wizardpdfgen.model.persistence.sql.MySqlDAOFactory;
import de.hhbk.wizardpdfgen.model.persistence.sql.MySqlUserAdministrationDAO;
import de.hhbk.wizardpdfgen.view.LoginView;
import de.hhbk.wizardpdfgen.view.MainWindowView;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 08.05.2017.
 */
public class MainWindowViewModel implements ViewModel {

    private BooleanProperty userAdminButtonVisibility = new SimpleBooleanProperty();
    private BooleanProperty deleteTemplateButtonVisibility = new SimpleBooleanProperty();
    private BooleanProperty generateTemplateButtonVisibility = new SimpleBooleanProperty();
    private BooleanProperty generatePDFButtonVisibility = new SimpleBooleanProperty();

    private BooleanProperty generatePDFButtonEnabled = new SimpleBooleanProperty();

    private ObservableList<String> skilledOccupationList  = FXCollections.observableArrayList();
    private ObservableList<Integer> trainingYearList  = FXCollections.observableArrayList();


    private StringProperty guideText = new SimpleStringProperty();

    private static Logger logger = LogManager.getLogger(MainWindowView.class);


    public void initialize() {
        this.adjustMainWindowToAuthorisationLevel();
    }

    private void adjustMainWindowToAuthorisationLevel(){

        AuthorisationLevel status = LoginViewModel.currentUser.getRole();
        switch (status)
        {
            case TEACHER:
                userAdminButtonVisibility.setValue(false);
                generateTemplateButtonVisibility.setValue(true);
                deleteTemplateButtonVisibility.setValue(true);
                generatePDFButtonVisibility.setValue(true);
                break;

            case GUEST:
                userAdminButtonVisibility.setValue(false);
                generateTemplateButtonVisibility.setValue(false);
                deleteTemplateButtonVisibility.setValue(false);
                generatePDFButtonVisibility.setValue(true);
                break;

            case ADMIN:
                userAdminButtonVisibility.setValue(true);
                generateTemplateButtonVisibility.setValue(true);
                deleteTemplateButtonVisibility.setValue(true);
                generatePDFButtonVisibility.setValue(true);
                break;
            default:
                break;
        }

    }
    private void getSkilledOccupation(){
        MySqlDAOFactory daoFactory = new MySqlDAOFactory();

        DidacticWizardDAO didactDAO = MySqlDAOFactory.createDidkatWizardDAO(DBType.DIDACTSQL_DB);
        logger.info("DidactDAO created");

        List<String> ausbildungList = null;
        try {
            ausbildungList = didactDAO.getAllSkilledOccupation();

            if (ausbildungList.isEmpty()) {
                logger.info("Nutzer wird über Funktionsausfall informiert");
                Alert alert = new Alert(Alert.AlertType.ERROR, "Es konnten keine Daten zu Ausbildungsberufen gefunden werden");
                alert.showAndWait();
                alert.setTitle("Keine Daten vorhanden");
                alert.setContentText("Folge: Es können keine PDFs generiert werden. Bitte kontaktieren Sie den Administrator");
            }else {
                skilledOccupationList.addAll(skilledOccupationList);
            }

        } catch (SQLException e) {
            logger.info("Nutzer wird über Funktionsausfall informiert");
            Alert alert = new Alert(Alert.AlertType.ERROR, "Ein Fehler beim Datenbankzugriff ist aufgetreten");
            alert.showAndWait();
            alert.setTitle("Datenbankzugriff gescheitert");
            alert.setContentText("Folge: Es können keine PDFs generiert werden. Bitte kontaktieren Sie den Administrator");
        }
    }

    public void updateTrainingYearBySkilledOccupation() {
    }

    public boolean isGeneratePDFButtonEnabled() {
        return generatePDFButtonEnabled.get();
    }

    public BooleanProperty generatePDFButtonEnabledProperty() {
        return generatePDFButtonEnabled;
    }

    public void setGeneratePDFButtonEnabled(boolean generatePDFButtonEnabled) {
        this.generatePDFButtonEnabled.set(generatePDFButtonEnabled);
    }

    public boolean isUserAdminButtonVisibility() {
        return userAdminButtonVisibility.get();
    }

    public BooleanProperty userAdminButtonVisibilityProperty() {
        return userAdminButtonVisibility;
    }

    public void setUserAdminButtonVisibility(boolean userAdminButtonVisibility) {
        this.userAdminButtonVisibility.set(userAdminButtonVisibility);
    }

    public boolean isDeleteTemplateButtonVisibility() {
        return deleteTemplateButtonVisibility.get();
    }

    public BooleanProperty deleteTemplateButtonVisibilityProperty() {
        return deleteTemplateButtonVisibility;
    }

    public void setDeleteTemplateButtonVisibility(boolean deleteTemplateButtonVisibility) {
        this.deleteTemplateButtonVisibility.set(deleteTemplateButtonVisibility);
    }

    public boolean isGenerateTemplateButtonVisibility() {
        return generateTemplateButtonVisibility.get();
    }

    public BooleanProperty generateTemplateButtonVisibilityProperty() {
        return generateTemplateButtonVisibility;
    }

    public void setGenerateTemplateButtonVisibility(boolean generateTemplateButtonVisibility) {
        this.generateTemplateButtonVisibility.set(generateTemplateButtonVisibility);
    }

    public boolean isGeneratePDFButtonVisibility() {
        return generatePDFButtonVisibility.get();
    }

    public BooleanProperty generatePDFButtonVisibilityProperty() {
        return generatePDFButtonVisibility;
    }

    public void setGeneratePDFButtonVisibility(boolean generatePDFButtonVisibility) {
        this.generatePDFButtonVisibility.set(generatePDFButtonVisibility);
    }

    public ObservableList<String> getSkilledOccupationList() {
        return skilledOccupationList;
    }

    public void setSkilledOccupationList(ObservableList<String> skilledOccupationList) {
        this.skilledOccupationList = skilledOccupationList;
    }

    public ObservableList<Integer> getTrainingYearList() {
        return trainingYearList;
    }

    public void setTrainingYearList(ObservableList<Integer> trainingYearList) {
        this.trainingYearList = trainingYearList;
    }

    public String getGuideText() {
        return guideText.get();
    }

    public StringProperty guideTextProperty() {
        return guideText;
    }

    public void setGuideText(String guideText) {
        this.guideText.set(guideText);
    }


}


