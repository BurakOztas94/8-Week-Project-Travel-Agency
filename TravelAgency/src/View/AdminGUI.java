package View;

import Helper.Config;
import Helper.Helper;
import Model.Admin;
import Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminGUI extends JFrame{

    private JPanel panel1;
    private JPanel wrapper;
    private JButton btn_user_exit;
    private JButton btn_srch_user;
    private JComboBox cmb_user_type;
    private JTextField fld_add_user_name;
    private JTextField fld_add_user_uname;
    private JPasswordField fld_add_user_pass;
    private JComboBox cmb_add_user_type;
    private JButton btn_user_add;
    private JTextField fld_user_del_id;
    private JButton btn_user_del;
    private JTabbedPane tabbedPane1;
    private JTable tbl_user_list;
    private JTextField fld_user_name;
    private JTextField fld_user_uname;
    private JPanel pnl_name_surname;

    private DefaultTableModel mdl_user_list;

    private Object [] row_user_list;

    private  Admin admin ;
    public AdminGUI(Admin admin)
        {
            this.admin=admin;
            add (wrapper);
            setSize (1000 , 800);
            setLocationRelativeTo (null);
            setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
            setTitle (Config.PROJECT_TITLE);
            setResizable (true);
            setVisible (true);

            //User tab start
            mdl_user_list = new DefaultTableModel (){
                @Override
                public boolean isCellEditable(int row,int column){
                    if (column==0)
                        return false;
                    return super.isCellEditable (row,column);

                }
            };


                Object[] col_user_list= {"ID" , "Name Surname" , "Username" , "Password" , "Authorization Type"};
                mdl_user_list.setColumnIdentifiers ( col_user_list);
                row_user_list=new Object[col_user_list.length];
                loadUserModel();
                tbl_user_list.setModel (mdl_user_list);
                tbl_user_list.getTableHeader ().setReorderingAllowed (false);
                tbl_user_list.getColumnModel ().getColumn (0).setMaxWidth (75);

            btn_user_add.addActionListener (e -> {
                if(Helper.isFieldEmpty (fld_add_user_name)||Helper.isFieldEmpty (fld_add_user_uname)||Helper.isFieldEmpty (fld_add_user_pass)){
                    Helper.showMsg ("fill");
                }else{
                    String name=fld_add_user_name.getText ();
                    String uname=fld_add_user_uname.getText ();
                    String pass=fld_add_user_pass.getText ();
                    String type=cmb_add_user_type.getSelectedItem ().toString ();
                    if(User.add(name,uname,pass,type)){
                        Helper.showMsg ("done");
                        loadUserModel ();
                        fld_add_user_name.setText (null);
                        fld_add_user_uname.setText (null);
                        fld_add_user_pass.setText (null);
                        cmb_add_user_type.setSelectedIndex (0);



                    }
                }

            });

            btn_user_exit.addActionListener (e -> {
               dispose ();
            });


        }

    private void loadUserModel ()
        {

            DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel ();
            clearModel.setRowCount (0);
            int i;
            for( User obj : User.getList() ){
                i=0;
                row_user_list[i++]=obj.getId ();
                row_user_list[i++]=obj.getName ();
                row_user_list[i++]=obj.getUname ();
                row_user_list[i++]=obj.getPass ();
                row_user_list[i++]=obj.getType ();
                mdl_user_list.addRow(row_user_list);
            }
        }


    public Admin getAdmin ()
        {
            return admin;
        }
}
