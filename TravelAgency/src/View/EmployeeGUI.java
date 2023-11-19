package View;

import Helper.Config;
import Model.Employee;
import Model.Hotel;
import Model.HotelSeason;
import Model.HotelType;
import Helper.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


//7. değerlendirme formu
public class EmployeeGUI extends JFrame {
    private final Employee employee;

    private JPanel wrapper;
    private JTabbedPane tabbedPane1;
    private JButton exitButton;
    private JButton btn_hotel_add;
    private JTabbedPane tabbedPane2;
    private JTabbedPane tabbedPane3;

    private JTable tbl_hotel_type_list;
    private JTable tbl_hotel_season_list;
    private JTable tbl_hotel_list;
    private JButton tbl_hotel_refresh;
    private JButton btn_hotel_update;
    private JButton btn_hotel_delete;
    private JTextField fld_hotel_delete_id;

    DefaultTableModel mdl_hotel_list;
    private  Object[] row_hotel_list;

    DefaultTableModel mdl_hotel_type_list;
    private Object[] row_hotel_type_list;
    DefaultTableModel mdl_hotel_season_list;
    Object[] row_hotel_season_list;





    public EmployeeGUI (Employee employee)
        {
            this.employee=employee;
            add (wrapper);
            setSize (1200,800);
            setLocationRelativeTo (null);
            setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
            setTitle (Config.PROJECT_TITLE);
            setVisible (true);

            // Table-top

            mdl_hotel_list = new DefaultTableModel (){
                @Override
                public boolean isCellEditable(int row,int column){
                    if (column==0)
                        return false;
                    return super.isCellEditable (row,column);

                }
            };

            Object[] col_hotel_list={"id","Hotel Name" ,"Star","Properties","Address","Phone","E-mail"};
            mdl_hotel_list.setColumnIdentifiers (col_hotel_list);
            row_hotel_list=new Object [col_hotel_list.length];
          loadHotelModel();
            tbl_hotel_list.setModel (mdl_hotel_list);
            tbl_hotel_list.getTableHeader ().setReorderingAllowed (false);
            tbl_hotel_list.getColumnModel ().getColumn (0).setMaxWidth (75);

            //Table left

            mdl_hotel_type_list = new DefaultTableModel (){
                @Override
                public boolean isCellEditable(int row,int column){
                    if (column==0)
                        return false;
                    return super.isCellEditable (row,column);

                }
            };


            Object[] col_type_list = {"Board ID", "Board Name", "Hotel ID", "Hotel Name "};
            mdl_hotel_type_list.setColumnIdentifiers(col_type_list);
            row_hotel_type_list = new Object[col_type_list.length];
            loadTypeModel();
            tbl_hotel_type_list.setModel(mdl_hotel_type_list);
            tbl_hotel_type_list.getTableHeader().setReorderingAllowed(false);
            tbl_hotel_type_list.getColumnModel().getColumn(0).setMaxWidth(75);

            //Table right

            mdl_hotel_season_list = new DefaultTableModel (){
                @Override
                public boolean isCellEditable(int row,int column){
                    if (column==0)
                        return false;
                    return super.isCellEditable (row,column);

                }
            };

            Object[] col_season_list = {"Season ID", "Season Name", "Hotel Name ", "Season Start ","Season End"};
            mdl_hotel_season_list.setColumnIdentifiers(col_season_list);
            row_hotel_season_list = new Object[col_season_list.length];
          loadSeasonModel();
            tbl_hotel_season_list.setModel(mdl_hotel_season_list);
            tbl_hotel_season_list.getTableHeader().setReorderingAllowed(false);
            tbl_hotel_season_list.getColumnModel().getColumn(0).setMaxWidth(75);


            tbl_hotel_list.getSelectionModel ().addListSelectionListener (e -> {

                try
                    {
                        String select_user_id =  tbl_hotel_list.getValueAt
                                ( tbl_hotel_list.getSelectedRow () , 0).toString ();
                        fld_hotel_delete_id.setText (select_user_id);
                    } catch (Exception exception)
                    {
                        System.out.println (" ");
                    }
            });






            btn_hotel_add.addActionListener (e -> {
            HotelAddGUI hoteladd=new HotelAddGUI (employee);
                hoteladd.addWindowListener (new WindowAdapter () {
                    @Override
                    public void windowClosed (WindowEvent e)
                        {
                            loadHotelModel ();
                            loadSeasonModel ();
                            loadTypeModel ();
                            tbl_hotel_list.getSelectionModel ().clearSelection ();
                        }
                });


            });

            tbl_hotel_refresh.addActionListener (e -> {         //refreshes if save button used
                loadHotelModel ();
                loadTypeModel();
                loadSeasonModel();
            });

            btn_hotel_delete.addActionListener(e ->

            {
                if (Helper.isFieldEmpty(fld_hotel_delete_id)) {
                    Helper.showMsg("fill");

                } else {
                    int hotelId = Integer.parseInt(fld_hotel_delete_id.getText());
                    if (HotelType.delete (hotelId)&&HotelSeason.delete(hotelId)&&Hotel.delete(hotelId)) {
                        Helper.showMsg("done");
                        loadHotelModel ();
                        loadTypeModel ();
                        loadSeasonModel ();
                        fld_hotel_delete_id.setText(null);

                    } else {
                        Helper.showMsg("error");
                    }
                }
            });


            exitButton.addActionListener (e -> {
                dispose ();
            });



        }




    public void loadHotelModel ()
        {
            DefaultTableModel clearModel=(DefaultTableModel) tbl_hotel_list.getModel();
            clearModel.setRowCount (0);
            int i;
            for( Hotel obj : Hotel.getList()){
                i=0;
                row_hotel_list[i++]=obj.getId();
                row_hotel_list[i++]=obj.getName();
                row_hotel_list[i++]=obj.getStar();
                row_hotel_list[i++]=obj.getProperty();
                row_hotel_list[i++]=obj.getAddress();
                row_hotel_list[i++]=obj.getPhone();
                row_hotel_list[i++]=obj.getEmail();
                mdl_hotel_list.addRow (row_hotel_list);

            }
        }

    private void loadTypeModel ()
        {
            DefaultTableModel clearModel=(DefaultTableModel) tbl_hotel_type_list.getModel();
            clearModel.setRowCount (0);
            int i;
            for( HotelType obj : HotelType.getList()){
                i=0;
                row_hotel_type_list[i++]=obj.getId();
                row_hotel_type_list[i++]=obj.getType();
                row_hotel_type_list[i++]=obj.getHotelId ();
                row_hotel_type_list[i++]=obj.getFetch (obj.getHotelId ()).getName ();

                mdl_hotel_type_list.addRow (row_hotel_type_list);

            }
        }

    private void loadSeasonModel ()
        { DefaultTableModel clearModel=(DefaultTableModel) tbl_hotel_season_list.getModel();
            clearModel.setRowCount (0);
            int i;
            for( HotelSeason obj : HotelSeason.getList()){
                i=0;
                row_hotel_season_list[i++]=obj.getSeasonId ();
                row_hotel_season_list[i++]=obj.getSeasonName ();
                row_hotel_season_list[i++]=obj.getFetch (obj.getHotelId ()).getName ();
                row_hotel_season_list[i++]=obj.getSeasonStart ();
                row_hotel_season_list[i++]=obj.getSeasonEnd ();


                mdl_hotel_season_list.addRow (row_hotel_season_list);

            }
        }



}
