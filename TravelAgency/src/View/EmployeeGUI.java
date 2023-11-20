package View;

import Helper.Config;
import Model.*;
import Helper.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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
    private JTable tbl_room_list;
    private JButton btn_room_add;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton reservationButton;
    private JTable table2;
    private JButton roomSearchButton;

    DefaultTableModel mdl_hotel_list;
    private Object[] row_hotel_list;

    DefaultTableModel mdl_hotel_type_list;
    private Object[] row_hotel_type_list;
    DefaultTableModel mdl_hotel_season_list;
    Object[] row_hotel_season_list;
    DefaultTableModel mdl_room_list;
    private Object[] row_room_list;


    public EmployeeGUI (Employee employee)
        {
            this.employee = employee;
            add (wrapper);
            setSize (1200 , 800);
            setLocationRelativeTo (null);
            setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
            setTitle (Config.PROJECT_TITLE);
            setVisible (true);


            mdl_hotel_list = new DefaultTableModel () {
                @Override
                public boolean isCellEditable (int row , int column)
                    {
                        if ( column == 0 )
                            return false;
                        return super.isCellEditable (row , column);

                    }
            };

            // Table-top

            Object[] col_hotel_list = {"id" , "Hotel Name" , "Star" , "Properties" , "Address" , "Phone" , "E-mail"};
            mdl_hotel_list.setColumnIdentifiers (col_hotel_list);
            row_hotel_list = new Object[col_hotel_list.length];
            loadHotelModel ();
            tbl_hotel_list.setModel (mdl_hotel_list);
            tbl_hotel_list.getTableHeader ().setReorderingAllowed (false);
            tbl_hotel_list.getColumnModel ().getColumn (0).setMaxWidth (75);

            //Table left

            mdl_hotel_type_list = new DefaultTableModel () {
                @Override
                public boolean isCellEditable (int row , int column)
                    {
                        if ( column == 0 )
                            return false;
                        return super.isCellEditable (row , column);

                    }
            };

            //type model

            Object[] col_type_list = {"Board ID" , "Board Name" , "Hotel ID" , "Hotel Name "};
            mdl_hotel_type_list.setColumnIdentifiers (col_type_list);
            row_hotel_type_list = new Object[col_type_list.length];

            tbl_hotel_type_list.setModel (mdl_hotel_type_list);
            tbl_hotel_type_list.getTableHeader ().setReorderingAllowed (false);
            tbl_hotel_type_list.getColumnModel ().getColumn (0).setMaxWidth (75);

            //Table right

            mdl_hotel_season_list = new DefaultTableModel () {
                @Override
                public boolean isCellEditable (int row , int column)
                    {
                        if ( column == 0 )
                            return false;
                        return super.isCellEditable (row , column);

                    }
            };

            //Season model

            Object[] col_season_list = {"Season ID" , "Season Name" , "Hotel Name " , "Season Start " , "Season End"};
            mdl_hotel_season_list.setColumnIdentifiers (col_season_list);
            row_hotel_season_list = new Object[col_season_list.length];

            tbl_hotel_season_list.setModel (mdl_hotel_season_list);
            tbl_hotel_season_list.getTableHeader ().setReorderingAllowed (false);
            tbl_hotel_season_list.getColumnModel ().getColumn (0).setMaxWidth (75);

            // brings id for delete function

            tbl_hotel_list.getSelectionModel ().addListSelectionListener (e -> {

                try
                    {
                        String select_hotel_id = tbl_hotel_list.getValueAt
                                (tbl_hotel_list.getSelectedRow () , 0).toString ();
                        fld_hotel_delete_id.setText (select_hotel_id);
                    } catch (Exception exception)
                    {
                        System.out.println (" ");
                    }
            });

            //for brings information to left and right table

            btn_hotel_add.addActionListener (e -> {
                HotelAddGUI hoteladd = new HotelAddGUI (employee);
                hoteladd.addWindowListener (new WindowAdapter () {
                    @Override
                    public void windowClosed (WindowEvent e)
                        {
                            loadHotelModel ();
                            tbl_hotel_list.getSelectionModel ().clearSelection ();
                        }
                });
            });

            //refreshes button brings all tables
            tbl_hotel_refresh.addActionListener (e -> {
                loadHotelModel ();
                loadTypeModel ();
                loadSeasonModel ();
            });

            //update button
            btn_hotel_update.addActionListener (e -> {
                int selectId = Integer.parseInt (tbl_hotel_list.
                        getValueAt (tbl_hotel_list.getSelectedRow () , 0).toString ());
                System.out.println (selectId);
              //  HotelAddGUI hotelGUI = new HotelAddGUI (HotelType.getFetchById (selectId));


            });

            btn_hotel_delete.addActionListener (e ->

            {
                if ( Helper.isFieldEmpty (fld_hotel_delete_id) )
                    {
                        Helper.showMsg ("fill");

                    }
                else
                    {
                        int hotelId = Integer.parseInt (fld_hotel_delete_id.getText ());
                        if ( HotelType.delete (hotelId) && HotelSeason.delete (hotelId) && Hotel.delete (hotelId) )
                            {
                                Helper.showMsg ("done");
                                loadHotelModel ();
                                loadTypeModel ();
                                loadSeasonModel ();
                                fld_hotel_delete_id.setText (null);

                            }
                        else
                            {
                                Helper.showMsg ("error");
                            }
                    }
            });

            //Room table starts

            mdl_room_list = new DefaultTableModel () {
                @Override
                public boolean isCellEditable (int row , int column)
                    {
                        if ( column == 0 )
                            return false;
                        return super.isCellEditable (row , column);

                    }
            };

            Object[] col_room_list = {"id" , "Hotel Name" , "Room Type" , "Stock" , "Season Date" , "Adult Price" , "Child Price" , "Type"};
            mdl_room_list.setColumnIdentifiers (col_room_list);
            row_room_list = new Object[col_room_list.length];

            tbl_room_list.setModel (mdl_room_list);
            tbl_room_list.getTableHeader ().setReorderingAllowed (false);
            tbl_room_list.getColumnModel ().getColumn (0).setMaxWidth (75);
            loadRoomListModel ();

            //room add button codes

            btn_room_add.addActionListener (e -> {
                RoomAddGUI roomAddGUI = new RoomAddGUI (employee);
                loadRoomListModel ();
                tbl_room_list.getSelectionModel ().clearSelection ();
            });

            // The button the most i like
            exitButton.addActionListener (e -> {
                dispose ();
            });
        }

    public void loadHotelModel ()
        {
            DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_list.getModel ();
            clearModel.setRowCount (0);
            int i;
            for ( Hotel obj : Hotel.getList () )
                {
                    i = 0;
                    row_hotel_list[i++] = obj.getId ();
                    row_hotel_list[i++] = obj.getName ();
                    row_hotel_list[i++] = obj.getStar ();
                    row_hotel_list[i++] = obj.getProperty ();
                    row_hotel_list[i++] = obj.getAddress ();
                    row_hotel_list[i++] = obj.getPhone ();
                    row_hotel_list[i++] = obj.getEmail ();
                    mdl_hotel_list.addRow (row_hotel_list);
                }
        }

    private void loadTypeModel ()
        {
            DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_type_list.getModel ();
            clearModel.setRowCount (0);
            int i;
            for ( HotelType obj : HotelType.getList () )
                {
                    i = 0;
                    row_hotel_type_list[i++] = obj.getId ();
                    row_hotel_type_list[i++] = obj.getType ();
                    row_hotel_type_list[i++] = obj.getHotelId ();
                    row_hotel_type_list[i++] = obj.getFetch (obj.getHotelId ()).getName ();
                    mdl_hotel_type_list.addRow (row_hotel_type_list);

                }
        }

    private void loadTypeModelById (int id)
        {
            DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_type_list.getModel ();
            clearModel.setRowCount (0);
            int i;
            for ( HotelType obj : HotelType.getListByHotelID (id) )
                {
                    i = 0;
                    row_hotel_type_list[i++] = obj.getType ();
                    mdl_hotel_type_list.addRow (row_hotel_type_list);
                }
        }

    private void loadSeasonModel ()
        {
            DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_season_list.getModel ();
            clearModel.setRowCount (0);
            int i;
            for ( HotelSeason obj : HotelSeason.getList () )
                {
                    i = 0;
                    row_hotel_season_list[i++] = obj.getSeasonId ();
                    row_hotel_season_list[i++] = obj.getSeasonName ();
                    int j = obj.getHotelId ();
                    Hotel hotel = Hotel.getFetchById (j);
                    row_hotel_season_list[i++] = hotel.getName ();
                    row_hotel_season_list[i++] = obj.getSeasonStart ();
                    row_hotel_season_list[i++] = obj.getSeasonEnd ();
                    mdl_hotel_season_list.addRow (row_hotel_season_list);
                }
        }

    private void loadRoomListModel ()
        {
            DefaultTableModel clearModel = (DefaultTableModel) tbl_room_list.getModel ();
            clearModel.setRowCount (0);
            int i;
            for ( Room obj : Room.getList () )
                {
                    i = 0;
                    row_room_list[i++] = obj.getId ();
                    int j = obj.getHotel_id ();
                    Hotel hotel = Hotel.getFetchById (j);
                    row_room_list[i++] = hotel.getName ();
                    row_room_list[i++] = obj.getRoom_type ();
                    row_room_list[i++] = obj.getStock ();
                    row_room_list[i++] = HotelSeason.getFetch (obj.getSeason_id ()).getSeasonStart () + " - " + HotelSeason.getFetch (obj.getSeason_id ()).getSeasonEnd ();
                    row_room_list[i++] = obj.getAdult_price ();
                    row_room_list[i++] = obj.getChild_price ();
                    int k = obj.getHotel_type_id ();
                    HotelType hotelType = HotelType.getFetchByID (k);
                    row_room_list[i++] = hotelType.getType ();
                    mdl_room_list.addRow (row_room_list);
                }
        }
}
