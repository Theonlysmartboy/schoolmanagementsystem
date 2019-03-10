
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Settings {

    public static Dimension getScreenSize() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        return d;
    }//getScreenSize() closed

    public static Connection getDBConnection() {
        Connection con;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            con = DriverManager.getConnection("jdbc:ucanaccess://E:\\Projects\\kashipara.com_student-managemtnt-system-zip\\student managemtnt system\\StudentManagementSystem\\student.accdb");

            return con;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}//class closed
