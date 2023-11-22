package View;

import Helper.Config;
import Model.*;
import Helper.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;


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
    private JButton btn_room_update;
    private JTextField fld_room_search_name;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton btn_room_add_reservation;
    private JTable tbl_room_price_list;
    private JButton btn_room_search;
    private JTextField fld_delete_type;
    private JButton btn_delete_type;
    private JTextField fld_delete_season;
    private JButton btn_delete_season;
    private JTextField fld_hotel_search_hotel_name;
    private JTextField fld_hotel_search_hotel_city;
    private JButton searchButton;
    private JComboBox cmb_search_hotel_stars;
    private JButton btn_hotel_add_room;
    private JButton btn_room_refresh;
    private JTextField fld_room_id;
    private JButton btn_room_delete;
    private JButton btn_room_add_price;
    private JTextField fld_room_id_addPrice;
    private JTextField fld_room_price_id;
    private JButton btn_room_delete_price;
    private JTextField fld_room_search_region;
    private JTable tbl_hotel_reservation;
    private JScrollPane tbl_hotel_booking;
    private JButton btn_book_del;
    private JTextField fld_book__del_id;

    DefaultTableModel mdl_hotel_list;
    private Object[] row_hotel_list;

    DefaultTableModel mdl_hotel_type_list;
    private Object[] row_hotel_type_list;
    DefaultTableModel mdl_hotel_season_list;
    Object[] row_hotel_season_list;
    DefaultTableModel mdl_room_list;
    private Object[] row_room_list;

    private Object[] row_room_price_list;
    DefaultTableModel mdl_room_price_list;

    private Object[] row_book_list;
    DefaultTableModel mdl_book_list;


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

            Object[] col_type_list = {"Board ID" , "Board Name" , "Hotel ID " , "Hotel Name"};
            mdl_hotel_type_list.setColumnIdentifiers (col_type_list);
            row_hotel_type_list = new Object[col_type_list.length];

            tbl_hotel_type_list.setModel (mdl_hotel_type_list);
            tbl_hotel_type_list.getTableHeader ().setReorderingAllowed (false);
            tbl_hotel_type_list.getColumnModel ().getColumn (0).setMaxWidth (75);
            loadTypeModel ();

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

            //Table left

            mdl_room_price_list = new DefaultTableModel () {
                @Override
                public boolean isCellEditable (int row , int column)
                    {
                        if ( column == 0 )
                            return false;
                        return super.isCellEditable (row , column);

                    }
            };

            mdl_room_price_list = new DefaultTableModel () {
                @Override
                public boolean isCellEditable (int row , int column)
                    {
                        if ( column == 0 )
                            return false;
                        return super.isCellEditable (row , column);
                    }
            };

            //type model

            Object[] col_price_list = {"Price ID" , "Room ID" , "Hotel ID" , "Board Name" , "Hotel Name" , "Room Name" ,
                    "Room Price Per Adult" , "Room Price Per Child" , "Season Name"};
            mdl_room_price_list.setColumnIdentifiers (col_price_list);
            row_room_price_list = new Object[col_price_list.length];

            tbl_room_price_list.setModel (mdl_room_price_list);
            tbl_room_price_list.getTableHeader ().setReorderingAllowed (false);
            tbl_room_price_list.getColumnModel ().getColumn (0).setMaxWidth (75);
            loadPriceModel ();

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

            //Price List model

            Object[] col_season_list = {"Season ID" , "Season Name" , "Hotel Name " , "Season Start " , "Season End"};
            mdl_hotel_season_list.setColumnIdentifiers (col_season_list);
            row_hotel_season_list = new Object[col_season_list.length];

            tbl_hotel_season_list.setModel (mdl_hotel_season_list);
            tbl_hotel_season_list.getTableHeader ().setReorderingAllowed (false);
            tbl_hotel_season_list.getColumnModel ().getColumn (0).setMaxWidth (75);
            loadSeasonModel ();

            // brings id for price List delete function

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

            // brings id for season delete function

            tbl_hotel_season_list.getSelectionModel ().addListSelectionListener (e -> {

                try
                    {
                        String select_season_id = tbl_hotel_season_list.getValueAt
                                (tbl_hotel_season_list.getSelectedRow () , 0).toString ();
                        fld_delete_season.setText (select_season_id);
                    } catch (Exception exception)
                    {
                        System.out.println (" ");
                    }
            });

            // brings id for type delete function

            tbl_hotel_type_list.getSelectionModel ().addListSelectionListener (e -> {

                try
                    {
                        String select_type_id = tbl_hotel_type_list.getValueAt
                                (tbl_hotel_type_list.getSelectedRow () , 0).toString ();
                        fld_delete_type.setText (select_type_id);
                    } catch (Exception exception)
                    {
                        System.out.println (" ");
                    }
            });


            // brings id for room delete function

            tbl_room_list.getSelectionModel ().addListSelectionListener (e -> {

                try
                    {
                        String select_type_id = tbl_room_list.getValueAt
                                (tbl_room_list.getSelectedRow () , 0).toString ();
                        fld_room_id.setText (select_type_id);
                        fld_room_id_addPrice.setText (select_type_id);

                    } catch (Exception exception)
                    {
                        System.out.println (" ");
                    }
            });

            // brings id for room price delete function

            tbl_room_price_list.getSelectionModel ().addListSelectionListener (e -> {

                try
                    {
                        String select_type_id = tbl_room_price_list.getValueAt
                                (tbl_room_price_list.getSelectedRow () , 0).toString ();
                        fld_room_price_id.setText (select_type_id);

                    } catch (Exception exception)
                    {
                        System.out.println (" ");
                    }
            });

            //hotel add button

            btn_hotel_add.addActionListener (e -> {
                HotelSeason season = null;
                HotelType type = null;
                Hotel hotel = null;
                boolean isUpdate = false;
                HotelAddGUI hoteladd = new HotelAddGUI (hotel , type , season , isUpdate);
            });

            //refreshes button brings all tables

            tbl_hotel_refresh.addActionListener (e -> {
                loadHotelModel ();
                loadTypeModel ();
                loadSeasonModel ();
                loadPriceModel ();
                fld_hotel_delete_id.setText (null);
                fld_delete_season.setText (null);
                fld_delete_type.setText (null);
                fld_room_id.setText (null);
                fld_room_id_addPrice.setText (null);
                fld_room_price_id.setText (null);
            });

            //update button

            btn_hotel_update.addActionListener (e -> {

                try
                    {
                        int selectId = Integer.parseInt (tbl_hotel_list.
                                getValueAt (tbl_hotel_list.getSelectedRow () , 0).toString ());
                        System.out.println (selectId);
                        Hotel hotel = Hotel.getFetchById (selectId);
                        HotelType type = new HotelType ();
                        HotelSeason season = new HotelSeason ();
                        boolean isUpdate = true;
                        HotelAddGUI hoteladd = new HotelAddGUI (hotel , type , season , isUpdate);


                    } catch (Exception exception)
                    {
                        Helper.showMsg ("error");
                    }


            });
            //hotel delete button

            btn_hotel_delete.addActionListener (e ->

            {
                if ( Helper.isFieldEmpty (fld_hotel_delete_id) )
                    {
                        Helper.showMsg ("fill");

                    }
                else
                    {

                        int hotelId = Integer.parseInt (fld_hotel_delete_id.getText ());

                        if ( Helper.confirm ("sure") )
                            {
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

            Object[] col_room_list = {"Room Id" , "Hotel Name" , "Address" , "Room Type" , "Stock" , "Season Name" ,
                    "Capacity" , "Season Start" , "Season End"};
            mdl_room_list.setColumnIdentifiers (col_room_list);
            row_room_list = new Object[col_room_list.length];

            tbl_room_list.setModel (mdl_room_list);
            tbl_room_list.getTableHeader ().setReorderingAllowed (false);
            tbl_room_list.getColumnModel ().getColumn (0).setMaxWidth (75);
            loadRoomListModel ();

            // The button the most i like
            exitButton.addActionListener (e -> {
                dispose ();
            });

                //season delete
            btn_delete_season.addActionListener (e -> {


                if ( Helper.isFieldEmpty (fld_delete_season) )
                    {
                        Helper.showMsg ("fill");

                    }
                else
                    {
                        int seasonId = Integer.parseInt (fld_delete_season.getText ());

                        if ( Helper.confirm ("sure") )
                            {
                                if ( HotelSeason.deletebySeasonId (seasonId) )
                                    {
                                        Helper.showMsg ("done");
                                        loadSeasonModel ();
                                        fld_delete_season.setText (null);

                                    }
                                else
                                    {
                                        Helper.showMsg ("error");
                                    }

                            }
                    }
            });

// Booking List Table
            mdl_book_list = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    if (column == 0)
                        return false;
                    return super.isCellEditable(row, column);

                }
            };

            Object[] col_book_list = {"Book ID", "Room ID", "Guest Name", "Identity Number", "Age", "Phone", "Email",
                    "Start Date", "End Date", "Adult Visitor", "Child Visitor", "Price", "Not"};
            mdl_book_list.setColumnIdentifiers(col_book_list);
            row_book_list = new Object[col_book_list.length];
            loadBookModel();
            tbl_hotel_reservation.setModel(mdl_book_list);
            tbl_hotel_reservation.getTableHeader().setReorderingAllowed(false);
            tbl_hotel_reservation.getColumnModel().getColumn(0).setMaxWidth(75);






            //delete type

            btn_delete_type.addActionListener (e -> {
                if ( Helper.isFieldEmpty (fld_delete_type) )
                    {
                        Helper.showMsg ("fill");

                    }
                else
                    {

                        int typeId = Integer.parseInt (fld_delete_type.getText ());
                        if ( Helper.confirm ("sure") )
                            {

                                if ( HotelType.delete (typeId) )
                                    {
                                        Helper.showMsg ("done");
                                        loadTypeModel ();
                                        fld_delete_type.setText (null);

                                    }
                                else
                                    {
                                        Helper.showMsg ("error");
                                    }

                            }

                    }
            });



            searchButton.addActionListener (e -> {
                String hotel_name = fld_hotel_search_hotel_name.getText ();
                String hotel_city = fld_hotel_search_hotel_city.getText ();
                String hotel_stars = cmb_search_hotel_stars.getSelectedItem ().toString ();
                String query = Hotel.searchQuery (hotel_name , hotel_city , hotel_stars);
                ArrayList<Hotel> filterHotels = Hotel.searchHotelList (query);
                loadHotelModel (filterHotels);

            });

            //room search button
            btn_room_search.addActionListener (e -> {
                String hotel_name = fld_room_search_name.getText ();
                String hotel_city = fld_room_search_region.getText ();

                String query = Room.searchQuery (hotel_name , hotel_city);
                ArrayList<Room> filterRooms = Room.searchRoomList (query);
                loadRoomListModel (filterRooms);

            });
            // Filters season and hotel type according to hotel ID at Hotel Management Tab

            tbl_hotel_list.getSelectionModel ().addListSelectionListener (e -> {

                try
                    {

                        String selected_id = tbl_hotel_list.getValueAt
                                (tbl_hotel_list.getSelectedRow () , 0).toString ();


                        String query = HotelSeason.searchQuery (selected_id);
                        ArrayList<HotelSeason> filterSeasons = HotelSeason.searchSeasonList (query);
                        loadSeasonModel (filterSeasons);

                        String queryType = HotelType.searchQuery (selected_id);
                        ArrayList<HotelType> filterHotelTypes = HotelType.searchHotelTypes (queryType);
                        loadTypeModel (filterHotelTypes);


                    } catch (Exception exception)
                    {
                    }

            });

            // Filters room price according to Room ID at Room Management Tab

            tbl_room_list.getSelectionModel ().addListSelectionListener (e -> {

                try
                    {

                        String selected_id = tbl_room_list.getValueAt
                                (tbl_room_list.getSelectedRow () , 0).toString ();


                        String query = RoomPrice.searchQuery (selected_id);
                        ArrayList<RoomPrice> filterPriceList = RoomPrice.searchSeasonList (query);
                        loadPriceModel (filterPriceList);


                    } catch (Exception exception)
                    {
                    }

            });

            btn_hotel_add_room.addActionListener (e -> {
                try
                    {
                        int selectId = Integer.parseInt (tbl_hotel_list.
                                getValueAt (tbl_hotel_list.getSelectedRow () , 0).toString ());
                        System.out.println (selectId);
                        Hotel hotel = Hotel.getFetchById (selectId);
                        Room room = null;
                        RoomAddGUI roomAddGUI = new RoomAddGUI (hotel , room);
                    } catch (Exception exception)
                    {
                        Helper.showMsg ("error");
                    }

            });

            btn_room_update.addActionListener (e -> {
                try
                    {
                        int selectId = Integer.parseInt (tbl_room_list.
                                getValueAt (tbl_room_list.getSelectedRow () , 0).toString ());

                        Room room = Room.getFetchById (selectId);
                        Hotel hotel = Hotel.getFetchById (room.getHotel_id ());
                        RoomAddGUI roomAddGUI = new RoomAddGUI (hotel , room);
                    } catch (Exception exception)
                    {
                        Helper.showMsg ("error");

                    }


            });

            btn_room_refresh.addActionListener (e -> {
                loadTypeModel ();
                loadHotelModel ();
                loadSeasonModel ();
                loadRoomListModel ();
                loadPriceModel ();
                fld_room_price_id.setText (null);
                fld_room_id.setText (null);
                fld_room_id_addPrice.setText (null);

            });
            btn_room_delete.addActionListener (e -> {

                if ( Helper.confirm ("sure") )
                    {
                        if ( Helper.isFieldEmpty (fld_room_id) )
                            {
                                Helper.showMsg ("Please select a room to delete");
                            }
                        else
                            {
                                boolean result = Room.delete (Integer.parseInt (fld_room_id.getText ()));
                                if ( result )
                                    {
                                        Helper.showMsg ("done");
                                        loadRoomListModel ();
                                    }
                                else
                                    {
                                        Helper.showMsg ("error");
                                    }

                            }

                    }

            });
            btn_room_add_price.addActionListener (e -> {
                try
                    {
                        Room roomAddPrice = Room.getFetchById (Integer.parseInt (fld_room_id_addPrice.getText ()));
                        RoomPriceGUI roomPriceGUI = new RoomPriceGUI (roomAddPrice);

                    } catch (Exception exception)
                    {
                        Helper.showMsg ("error");
                    }
            });

            btn_room_delete_price.addActionListener (e -> {

                if ( Helper.isFieldEmpty (fld_room_price_id) )
                    {
                        Helper.showMsg ("Please select a price row to delete");
                    }
                else
                    {
                        if ( Helper.confirm ("sure") )
                            {
                                boolean result = RoomPrice.delete (Integer.parseInt (fld_room_price_id.getText ()));
                                if ( result )
                                    {
                                        Helper.showMsg ("done");
                                        loadPriceModel ();
                                    }
                                else
                                    {
                                        Helper.showMsg ("error");
                                    }

                            }

                    }

            });

            btn_room_add_reservation.addActionListener (e -> {

                try
                    {
                        RoomPrice price = RoomPrice.getFetchById (Integer.parseInt (fld_room_price_id.getText ()));
                        BookGUI bookGUI = new BookGUI (price);

                    } catch (Exception exception)
                    {
                        Helper.showMsg ("error");
                    }


            });
        }



    public void loadHotelModel (ArrayList<Hotel> list)
        {
            DefaultTableModel resetModel = (DefaultTableModel) tbl_hotel_list.getModel ();
            resetModel.setRowCount (0);

            for ( Hotel obj : list )
                {
                    int i = 0;
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


    private void loadTypeModel (ArrayList<HotelType> list)
        {
            DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_type_list.getModel ();
            clearModel.setRowCount (0);

            for ( HotelType obj : list )
                {
                    int i = 0;
                    row_hotel_type_list[i++] = obj.getId ();
                    row_hotel_type_list[i++] = obj.getType ();
                    row_hotel_type_list[i++] = obj.getHotelId ();
                    row_hotel_type_list[i++] = obj.getFetch (obj.getHotelId ()).getName ();
                    mdl_hotel_type_list.addRow (row_hotel_type_list);

                }

        }

    private void loadPriceModel ()
        {
            DefaultTableModel clearModel = (DefaultTableModel) tbl_room_price_list.getModel ();
            clearModel.setRowCount (0);
            int i;
            for ( RoomPrice obj : RoomPrice.getList () )
                {
                    i = 0;
                    row_room_price_list[i++] = obj.getId ();
                    row_room_price_list[i++] = obj.getRoom_id ();
                    row_room_price_list[i++] = obj.getHotel_id ();
                    row_room_price_list[i++] = obj.getHotelType ();
                    int j = obj.getHotel_id ();
                    Hotel hotel = Hotel.getFetchById (j);
                    row_room_price_list[i++] = hotel.getName ();
                    int k = obj.getRoom_id ();
                    Room room = Room.getFetchById (k);
                    row_room_price_list[i++] = room.getRoom_type ();
                    row_room_price_list[i++] = obj.getRoom_price_per_adult ();
                    row_room_price_list[i++] = obj.getRoom_price_per_child ();
                    row_room_price_list[i++] = obj.getSeasonName ();
                    mdl_room_price_list.addRow (row_room_price_list);

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

    private void loadSeasonModel (ArrayList<HotelSeason> list)
        {
            DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_season_list.getModel ();
            clearModel.setRowCount (0);

            for ( HotelSeason obj : list )
                {
                    int i = 0;
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
                    row_room_list[i++] = hotel.getAddress ();
                    row_room_list[i++] = obj.getRoom_type ();
                    row_room_list[i++] = obj.getStock ();
                    row_room_list[i++] = obj.getSeason_name ();
                    row_room_list[i++] = obj.getCapacity ();
                    if ( obj.getSeason_name ().equals ("Winter") )
                        {
                            row_room_list[i++] = "01/11/2023";
                            row_room_list[i++] = "30/04/2024";
                        }
                    else if ( obj.getSeason_name ().equals ("Summer") )
                        {
                            row_room_list[i++] = "01/05/2024";
                            row_room_list[i++] = "30/10/2024";
                        }

                    mdl_room_list.addRow (row_room_list);
                }
        }

    private void loadRoomListModel (ArrayList<Room> list)
        {
            DefaultTableModel clearModel = (DefaultTableModel) tbl_room_list.getModel ();
            clearModel.setRowCount (0);
            for ( Room obj : list )
                {
                    int i = 0;
                    row_room_list[i++] = obj.getId ();
                    int j = obj.getHotel_id ();
                    Hotel hotel = Hotel.getFetchById (j);
                    row_room_list[i++] = hotel.getName ();
                    row_room_list[i++] = hotel.getAddress ();
                    row_room_list[i++] = obj.getRoom_type ();
                    row_room_list[i++] = obj.getStock ();
                    row_room_list[i++] = obj.getSeason_name ();
                    row_room_list[i++] = obj.getCapacity ();
                    if ( obj.getSeason_name ().equals ("Winter") )
                        {
                            row_room_list[i++] = "01/11/2023";
                            row_room_list[i++] = "30/04/2024";
                        }
                    else if ( obj.getSeason_name ().equals ("Summer") )
                        {
                            row_room_list[i++] = "01/05/2024";
                            row_room_list[i++] = "30/10/2024";
                        }
                    mdl_room_list.addRow (row_room_list);

                }




        }

    // loadPriceModel(filterPriceList);
    public void loadPriceModel (ArrayList<RoomPrice> list)
        {
            DefaultTableModel clearModel = (DefaultTableModel) tbl_room_price_list.getModel ();
            clearModel.setRowCount (0);
            int i;
            for ( RoomPrice obj : list )
                {
                    i = 0;
                    row_room_price_list[i++] = obj.getId ();
                    row_room_price_list[i++] = obj.getRoom_id ();
                    row_room_price_list[i++] = obj.getHotel_id ();
                    row_room_price_list[i++] = obj.getHotelType ();
                    int j = obj.getHotel_id ();
                    Hotel hotel = Hotel.getFetchById (j);
                    row_room_price_list[i++] = hotel.getName ();
                    int k = obj.getRoom_id ();
                    Room room = Room.getFetchById (k);
                    row_room_price_list[i++] = room.getRoom_type ();
                    row_room_price_list[i++] = obj.getRoom_price_per_adult ();
                    row_room_price_list[i++] = obj.getRoom_price_per_child ();
                    row_room_price_list[i++] = obj.getSeasonName ();
                    mdl_room_price_list.addRow (row_room_price_list);

                }

        }

        //loadBookModel
    private void loadBookModel ()
        {


        }

}
