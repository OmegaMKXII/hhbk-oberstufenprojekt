package de.hhbk.wizardpdfgen.viewmodel;

import de.hhbk.wizardpdfgen.main.Main;
import de.hhbk.wizardpdfgen.model.base.User;
import de.hhbk.wizardpdfgen.model.enums.AuthorisationLevel;
import de.hhbk.wizardpdfgen.model.persistence.sql.MySqlUserAdministrationDAO;
import de.hhbk.wizardpdfgen.view.UserAdminView;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 08.05.2017.
 */
public class UserAdminViewModel implements ViewModel {

    private StringProperty username = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();

    private ListView listviewUser;

    private ObservableList<User> userObservableList  = FXCollections.observableArrayList();

    private MySqlUserAdministrationDAO mySqlUserAdministrationDAO;

    private static Logger logger = LogManager.getLogger(UserAdminView.class);


    public void initialize(){

        List<User> userList = null;
        try {
            userList = MySqlUserAdministrationDAO.getAllUser();
        } catch (SQLException e) {
            logger.info("Nutzer wird über Funktionsausfall informiert");
            Alert alert = new Alert(Alert.AlertType.WARNING, "Es ist ein Fehler beim Datenbankzugriff aufgetreten");
            alert.showAndWait();
            alert.setTitle("Datenbankzugriff gescheitert");
            alert.setContentText("Folge: Es können keine Nutzerdaten gesehen noch bearbeitet werden.");
        }

        for (User usr : userList)
        {
            userObservableList.add(new User(usr.getUsername(),usr.getPassword(),usr.getRole()));
        }
    }

    /**
     * Deletes user depending on selected item in listView
     */
    public void deleteUser() {

        User user = (User)listviewUser.getSelectionModel().getSelectedItem();
        boolean isDeleted = false;
        try {
            isDeleted = MySqlUserAdministrationDAO.deleteUser(user);
        } catch (SQLException e) {
            logger.info("Nutzer wird über Funktionsausfall informiert");
            Alert alert = new Alert(Alert.AlertType.WARNING, "Es ist ein Fehler beim Datenbankzugriff aufgetreten");
            alert.showAndWait();
            alert.setTitle("Datenbankzugriff gescheitert");
            alert.setContentText("Folge: Es können keine Nutzerdaten bearbeitet werden.");
        }

        if(isDeleted)
        {
            userObservableList.remove(listviewUser.getSelectionModel().getSelectedItem());
            listviewUser.refresh();
        }
    }


    /**
     * Adds user by reading Textfield's content for username and password
     */
    public void addUser() {

        String username =  getUsername();
        String password = getPassword();

        if((username == null || password == null) && username.trim().isEmpty() || password.trim().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Bitte geben Sie etwas in die Felder ein!");
            alert.showAndWait();
        }

        User usr = new User(username, password, AuthorisationLevel.TEACHER);
        boolean isAdded = false;
        try {
            isAdded = MySqlUserAdministrationDAO.insertUser(usr);
        } catch (SQLException e) {
            logger.info("Nutzer wird über Funktionsausfall informiert");
            Alert alert = new Alert(Alert.AlertType.WARNING, "Es ist ein Fehler beim Datenbankzugriff aufgetreten");
            alert.showAndWait();
            alert.setTitle("Datenbankzugriff gescheitert");
            alert.setContentText("Folge: Es können keine Nutzerdaten bearbeitet werden.");
        }

        if(isAdded)
        {
            this.setUsername("");
            this.setPassword("");
        }

        this.userObservableList.add(usr);

        listviewUser.refresh();
    }


    /**
     * Returns to menu, if
     */
    public void returnToMainWindow() {
        Main.switchToMain();
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public ListView getListView() {
        return listviewUser;
    }

    public void setListView(ListView listviewUser) {
        this.listviewUser = listviewUser;
    }


    public ObservableList<User> getUserObservableList() {
        return userObservableList;
    }

    public void setUserObservableList(ObservableList<User> userObservableList) {
        this.userObservableList = userObservableList;
    }

}
