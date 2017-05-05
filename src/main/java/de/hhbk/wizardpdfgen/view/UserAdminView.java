package de.hhbk.wizardpdfgen.view;

import de.hhbk.wizardpdfgen.model.base.User;
import de.hhbk.wizardpdfgen.model.persistence.sql.MySqlUserAdministrationDAO;
import de.hhbk.wizardpdfgen.viewmodel.LoginViewModel;
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
public class UserAdminView implements FxmlView<LoginViewModel> {
    @FXML
    ListView listviewBenutzer;

    @FXML
    Button benutzerHinzufuegenButton;

    @FXML
    Button benutzerLoeschenButton;

    @FXML
    Button zurueckButton;

    @FXML
    TextField benutzerNameTextfield;

    @FXML
    TextField benutzerPasswortTextfield;

    @FXML
    Label benutzerNameLabel;

    @FXML
    Label benutzerPasswortLabel;

    @FXML
    Label BenutzerVerwaltungLabel;

    @InjectViewModel
    LoginViewModel viewModel;

    MySqlUserAdministrationDAO mySqlUserAdministrationDAO;


    ObservableList<User> userObservableList  = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        List<User> userList = MySqlUserAdministrationDAO.selectAllUser();

        for (User u :userList)
        {
            userObservableList.add(new User(u.getUser(),u.getPassword()));

        }

        listviewBenutzer.setItems(userObservableList);

    }


    public void benutzerLoeschenButtonEvent(MouseEvent mouseEvent) {

        User user = (User)listviewBenutzer.getSelectionModel().getSelectedItem();


        boolean gelöscht = MySqlUserAdministrationDAO.deleteUser(user.getUser(),user.getPassword());

        if(gelöscht)
        {
            userObservableList.remove(listviewBenutzer.getSelectionModel().getSelectedItem());
            listviewBenutzer.refresh();
        }

    }


    public void benutzerHinzufuegenButtonEvent(MouseEvent mouseEvent) {


        String user =  benutzerNameTextfield.getText();
        String password = benutzerPasswortTextfield.getText();

        if(user.isEmpty() || password.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Bitte geben Sie etwas in die Felder ein!");
            alert.showAndWait();
        }
        User u = new User(user,password);
        boolean hinzugefügt =   mySqlUserAdministrationDAO.insertUser(u);

        if(hinzugefügt)
        {
            benutzerNameTextfield.setText("");
            benutzerPasswortTextfield.setText("");
        }
        userObservableList.add(new User(user,password));

        listviewBenutzer.refresh();
    }

    public void zurueckButtonEvent(MouseEvent mouseEvent) {
        Main.switchToMain();
    }

}
