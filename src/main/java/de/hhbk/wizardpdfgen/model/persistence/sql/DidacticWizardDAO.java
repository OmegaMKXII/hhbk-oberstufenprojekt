package de.hhbk.wizardpdfgen.model.persistence.sql;

import de.hhbk.wizardpdfgen.model.enums.DBType;
import de.hhbk.wizardpdfgen.model.persistence.util.DBUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by monikaschepan on 08.05.17.
 */
public class DidacticWizardDAO {

    private static Logger logger = LogManager.getLogger(DidacticWizardDAO.class);

    // MySQL Statement
    private static final String GET_ALL_SKILLED_OCCUPATION= "SELECT tbl_beruf.Berufname FROM `tbl_beruf`";

    /**
     * Retrieves a list of all skilled occupation in database of didactic wizard application
     * @return list of skilled occupation title as string, if there is no such title, an empty list will be returned
     * @throws SQLException if an IO error occurs
     */
    public List<String> getAllSkilledOccupation() throws SQLException {

        List<String> skilledOccupationList = new ArrayList<String>();

        Connection conn = null;
        try {
            conn = DBUtil.getConnection(DBType.DIDACTSQL_DB);
            PreparedStatement statement = conn.prepareStatement(GET_ALL_SKILLED_OCCUPATION);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                skilledOccupationList.add(rs.getString(1));
            }
        } catch (SQLException e) {
            String errMsg = "Error while connecting to database";
            logger.error(errMsg);
            throw new SQLException(errMsg, e);
        } finally {
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                String errMsg = "Error while closing the connection to database";
                logger.warn(errMsg);
                throw new SQLException(errMsg, e);
            }
        }
        return skilledOccupationList;
    }


    /**
     * Retrieves all training years of given skilled occupation
     * @param skilledOccupation
     */
    public List<Integer> getTrainingYearsBySkilledOccupation(String skilledOccupation){
        List<Integer> trainingYearList = new ArrayList<Integer>();
        //TODO


        return trainingYearList;
    }






}
