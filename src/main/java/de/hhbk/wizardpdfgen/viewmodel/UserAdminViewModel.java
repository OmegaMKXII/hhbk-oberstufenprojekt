package de.hhbk.wizardpdfgen.viewmodel;

import de.hhbk.wizardpdfgen.model.base.User;
import de.hhbk.wizardpdfgen.model.enums.AuthorisationLevel;
import de.hhbk.wizardpdfgen.model.enums.DBType;
import de.hhbk.wizardpdfgen.model.persistence.interfaces.UserAdministrationDAO;
import de.hhbk.wizardpdfgen.model.persistence.sql.DAOFactory;
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
 * Author: Kenji Kokubo on 08.05.17<br>
 * This class represents the data and functions of the user administration view.
 */
public class UserAdminViewModel implements ViewModel {

    private StringProperty username = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();

    private ListView listviewUser;

    private ObservableList<User> userObservableList  = FXCollections.observableArrayList();

    private UserAdministrationDAO userAdministrationDAO;

    private static Logger logger = LogManager.getLogger(UserAdminView.class);

    /**
     * Retrieves data from database for view.
     * @throws SQLException if an IO error occurs
     */
    public void initialize() throws SQLException {

        this.userAdministrationDAO = DAOFactory.createUserAdminDAO(DBType.MYSQL_DB);

        List<User> userList = this.userAdministrationDAO.getAllUser();
        this.userObservableList.addAll(userList);
    }

    /**
     * Deletes user depending on selected item in listView
     * @throws SQLException if an IO error occurs
     */
    public void deleteUser() throws SQLException {

        User user = (User)listviewUser.getSelectionModel().getSelectedItem();
        boolean isDeleted = this.userAdministrationDAO.deleteUser(user);

        if(isDeleted)
        {
            userObservableList.remove(listviewUser.getSelectionModel().getSelectedItem());
        }
    }

    /**
     * Adds user by reading Textfield's content for username and password
     */
    public void addUser() throws SQLException {

        String username =  getUsername();
        String password = getPassword();

        if((username == null || password == null) && username.trim().isEmpty() || password.trim().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Bitte geben Sie etwas in die Felder ein!");
            alert.showAndWait();
        }

        User usr = new User(username, password, AuthorisationLevel.TEACHER);
        boolean isAdded = this.userAdministrationDAO.insertUser(usr);

        if(isAdded)
        {
            this.setUsername("");
            this.setPassword("");
        }

        this.userObservableList.add(usr);

        listviewUser.refresh();
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
