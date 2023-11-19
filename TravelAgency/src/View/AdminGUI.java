package View;

import Helper.Config;
import Helper.Helper;
import Model.Admin;
import Model.User;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;


//7. değerlendirme formu
public class AdminGUI extends JFrame {

    private JPanel panel1;
    private JPanel wrapper;
    private JButton btn_user_exit;
    private JButton btn_srch_user;
    private JComboBox cmb_srch_user_type;
    private JTextField fld_add_user_name;
    private JTextField fld_add_user_uname;
    private JPasswordField fld_add_user_pass;
    private JComboBox cmb_add_user_type;
    private JButton btn_user_add;
    private JTextField fld_user_del_id;
    private JButton btn_user_del;
    private JTabbedPane tabbedPane1;
    private JTable tbl_user_list;
    private JTextField fld_srch_user_name;
    private JTextField fld_srch_user_uname;
    private JPanel pnl_name_surname;
    private JTextField fld_update_name;
    private JTextField fld_update_uname;
    private JPasswordField fld_update_pass;
    private JComboBox cmb_update_type;
    private JButton bttn_update;
    private JTextField fld_update_user_id;

    private DefaultTableModel mdl_user_list;

    private Object[] row_user_list;

    private Admin admin;


    public AdminGUI (Admin admin)
        {
            this.admin = admin;
            add (wrapper);
            setSize (1000 , 800);
            setLocationRelativeTo (null);
            setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
            setTitle (Config.PROJECT_TITLE);
            setResizable (true);
            setVisible (true);

            //User tab start
            mdl_user_list = new DefaultTableModel () {
                @Override
                public boolean isCellEditable (int row , int column)
                    {
                        if ( column == 0 )
                            return false;
                        return super.isCellEditable (row , column);

                    }
            };


            Object[] col_user_list = {"ID" , "Name Surname" , "Username" , "Password" , "Authorization Type"};
            mdl_user_list.setColumnIdentifiers (col_user_list);
            row_user_list = new Object[col_user_list.length];
            loadUserModel ();
            tbl_user_list.setModel (mdl_user_list);
            tbl_user_list.getTableHeader ().setReorderingAllowed (false);
            tbl_user_list.getColumnModel ().getColumn (0).setMaxWidth (75);

            btn_user_add.addActionListener (e -> {
                if ( Helper.isFieldEmpty (fld_add_user_name) || Helper.isFieldEmpty (fld_add_user_uname) || Helper.isFieldEmpty (fld_add_user_pass) )
                    {
                        Helper.showMsg ("fill");
                    }
                else
                    {
                        String name = fld_add_user_name.getText ();
                        String uname = fld_add_user_uname.getText ();
                        String pass = fld_add_user_pass.getText ();
                        String type = cmb_add_user_type.getSelectedItem ().toString ();
                        if ( User.add (name , uname , pass , type) )
                            {
                                Helper.showMsg ("done");
                                loadUserModel ();
                                fld_add_user_name.setText (null);
                                fld_add_user_uname.setText (null);
                                fld_add_user_pass.setText (null);
                                cmb_add_user_type.setSelectedIndex (0);


                            }
                    }

            });


            btn_user_del.addActionListener (e -> {


                if ( Helper.isFieldEmpty (fld_user_del_id) )
                    {
                        Helper.showMsg ("fill");

                    }
                else
                    {
                        int user_id = Integer.parseInt (fld_user_del_id.getText ());
                        if ( User.delete (user_id) )
                            {
                                Helper.showMsg ("done");
                                loadUserModel ();
                                fld_user_del_id.setText (null);
                                fld_update_name.setText("");
                                fld_update_uname.setText("");
                                fld_update_pass.setText("");

                            }

                        else
                            {
                                Helper.showMsg ("error");
                            }
                    }


            });
            tbl_user_list.getSelectionModel ().addListSelectionListener (e -> {

                try
                    {
                        String select_user_id = tbl_user_list.getValueAt
                                (tbl_user_list.getSelectedRow () , 0).toString ();
                        fld_user_del_id.setText (select_user_id);
                    } catch (Exception exception)
                    {
                        System.out.println (" ");
                    }
            });


            btn_srch_user.addActionListener (e -> {
                String name = fld_srch_user_name.getText ();
                String uname = fld_srch_user_uname.getText ();
                String type = cmb_srch_user_type.getSelectedItem ().toString ();
                String query = User.searchQuery (name , uname , type);

                loadUserModel (User.searchUserList (query));

            });

            tbl_user_list.getSelectionModel ().addListSelectionListener (e -> {

                try
                    {
                        String select_user_id = tbl_user_list.getValueAt
                                (tbl_user_list.getSelectedRow () , 0).toString ();
                        fld_update_user_id.setText (select_user_id);
                    } catch (Exception exception)
                    {
                        System.out.println (" ");
                    }
            });

            tbl_user_list.getSelectionModel ().addListSelectionListener (e -> {

                try
                    {
                        String select_user_name = tbl_user_list.getValueAt
                                (tbl_user_list.getSelectedRow () , 1).toString ();
                        fld_update_uname.setText (select_user_name);
                    } catch (Exception exception)
                    {
                        System.out.println (" ");
                    }
            });

            tbl_user_list.getSelectionModel ().addListSelectionListener (e -> {

                try
                    {
                        String select_user_uname = tbl_user_list.getValueAt
                                (tbl_user_list.getSelectedRow () , 2).toString ();
                        fld_update_name.setText (select_user_uname);
                    } catch (Exception exception)
                    {
                        System.out.println (" ");
                    }
            });


            bttn_update.addActionListener (e -> {


                   boolean result = false;

                if (tbl_user_list.getSelectedRow() != -1) {
                    int selectedRow = tbl_user_list.getSelectedRow();
                    int userId = Integer.parseInt(tbl_user_list.getValueAt(selectedRow, 0).toString()); // Assuming the user ID is in column 0

                    // Get the updated values from the fields
                    String name = fld_update_name.getText();
                    String uname = fld_update_uname.getText();
                    String pass = fld_update_pass.getText();
                    String userType = (String) cmb_update_type.getSelectedItem();

                    // Update the user details
                    result = User.update(userId, name, uname, pass, userType);
                }
                if ( result )
                    {
                        Helper.showMsg ("done");
                        loadUserModel ();
                        fld_update_user_id.setText("");
                        fld_update_name.setText("");
                        fld_update_uname.setText("");
                        fld_update_pass.setText("");
                    }
                else
                    {
                        Helper.showMsg ("error");
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
            for ( User obj : User.getList () )
                {
                    i = 0;
                    row_user_list[i++] = obj.getId ();
                    row_user_list[i++] = obj.getName ();
                    row_user_list[i++] = obj.getUname ();
                    row_user_list[i++] = obj.getPass ();
                    row_user_list[i++] = obj.getType ();
                    mdl_user_list.addRow (row_user_list);
                }
        }

    public void loadUserModel (ArrayList<User> list)
        {
            DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel ();
            clearModel.setRowCount (0);

            for ( User obj : list )
                {
                    int i = 0;
                    row_user_list[i++] = obj.getId ();
                    row_user_list[i++] = obj.getName ();
                    row_user_list[i++] = obj.getUname ();
                    row_user_list[i++] = obj.getPass ();
                    row_user_list[i++] = obj.getType ();
                    mdl_user_list.addRow (row_user_list);
                }

        }

    public Admin getAdmin ()
        {
            return admin;
        }
}
