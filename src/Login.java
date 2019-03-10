import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {

    Container c = getContentPane();
    private JButton btnLogin,  btnCancel;
    private JLabel lblUName,  lblPasswd;
    private JTextField txtUName;
    private JPasswordField txtPasswd;
    
    public Login() {
        super("Login ...");
        this.setSize(350, 200);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocation((Settings.getScreenSize().width / 2) - 175, (Settings.getScreenSize().height / 2) - 150);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        lblUName = new JLabel("Username");
        lblPasswd = new JLabel("Password");
        txtUName = new JTextField();
        txtPasswd = new JPasswordField();
        btnLogin = new JButton("Login", new ImageIcon(ClassLoader.getSystemResource("Images/login.png")));
        btnCancel = new JButton("Cancel",new ImageIcon(ClassLoader.getSystemResource("Images/cancel.png")));
        lblUName.setBounds(50, 40, 140, 25);
        txtUName.setBounds(150, 40, 130, 25);
        lblPasswd.setBounds(50, 80, 140, 25);
        txtPasswd.setBounds(150, 80, 130, 25);
        btnLogin.setBounds(50, 120, 100, 25);
        btnCancel.setBounds(180, 120, 100, 25);
        btnLogin.addActionListener(this);
        btnCancel.addActionListener(this);
        this.add(lblUName);
        this.add(lblPasswd);
        this.add(txtUName);
        this.add(txtPasswd);
        this.add(btnLogin);
        this.add(btnCancel);
    }//constructor closed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                Connection con = DriverManager.getConnection("jdbc:ucanaccess://E:\\Projects\\kashipara.com_student-managemtnt-system-zip\\student managemtnt system\\StudentManagementSystem\\student.accdb");
                try {
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM UAD WHERE Username='" + txtUName.getText() +
                            "' and Password='" + txtPasswd.getText() + "'");
                    if (rs.next()) {
                        if (rs.getString(3).equals("Student")) {
                            userMDI frm = new userMDI();
                            frm.setVisible(true);
                        } else {                            
                            new frmAdminMDI().setVisible(true);
                        }
                        this.dispose();
                    }else{
                        JOptionPane.showMessageDialog(null,"Invalid username or password","Invalid",JOptionPane.ERROR_MESSAGE);
                    }
                    con.close();
                    
                } catch (HeadlessException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid username or password", "Invalid", JOptionPane.ERROR_MESSAGE);
                    txtUName.setText("");
                    txtPasswd.setText("");
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid username or password", "Invalid", JOptionPane.ERROR_MESSAGE);
                    txtUName.setText("");
                    txtPasswd.setText("");
                    ex.printStackTrace();
                } //inner try catch closed
                //inner try catch closed
            }catch (HeadlessException x) {
                JOptionPane.showMessageDialog(null, "Unable to connect to the database", "Connection error", JOptionPane.ERROR_MESSAGE);
            x.printStackTrace();
            }
            //outer try catch closed
             //outer try catch closed
            catch (SQLException x) {
                JOptionPane.showMessageDialog(null, "Unable to connect to the database", "Connection error", JOptionPane.ERROR_MESSAGE);
                x.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }//outer try catch closed
        }//if closed

        if (e.getSource() == btnCancel) {
            System.exit(0);
        }//if closed
    }//actionPerformed() closed
    public static void main(String args[])    {
        new Login().setVisible(true);
    }
}//class closed

