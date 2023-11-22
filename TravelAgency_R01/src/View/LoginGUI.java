package View;

import Helper.Config;
import Helper.Helper;
import Model.Admin;
import Model.Employee;
import Model.User;

import javax.swing.*;

public class LoginGUI extends JFrame {
    private JPanel wrapper;
    private JPasswordField fld_user_pass;
    private JTextField fld_user_uname;
    private JButton btn_user_login;
    private JPanel wtop;
    private JPanel wbot;

    public LoginGUI(){
        add (wrapper);
        setSize (400,600);
        setLocationRelativeTo (null);
        setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        setTitle (Config.PROJECT_TITLE);
        setResizable (false);
        setVisible (true);


        //login button
        btn_user_login.addActionListener (e -> {
            if(Helper.isFieldEmpty (fld_user_uname)||Helper.isFieldEmpty (fld_user_pass)){
                Helper.showMsg ("fill");
            }else{
                User u = User.getFetch (fld_user_uname.getText (),fld_user_pass.getText ());
                if(u==null){
                    Helper.showMsg ("User not found !.Wrong username or password");
                }else {
                    switch (u.getType ()){
                        case "admin":
                            AdminGUI adminGUI = new  AdminGUI((Admin)u);
                            break;
                        case"employee":
                            EmployeeGUI employeeGUI = new EmployeeGUI ((Employee)u);
                            break;

                    }
                    dispose ();
                }

            }
        });
    }


    public static void main (String[] args)
        {
            Helper.setLayout ();
            new LoginGUI ();
        }
}
