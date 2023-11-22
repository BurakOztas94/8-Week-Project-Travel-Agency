package View;

import javax.swing.*;

import Helper.*;
import Model.Hotel;
import Model.HotelSeason;
import Model.Room;

public class RoomAddGUI extends JFrame {
    private JTextField fld_roomAdd_stock;
    private JTextField fld_roomAdd_roomName;
    private JTextField fld_roomAdd_hotel_name;
    private JTextField fld_roomAdd_hotel_ID;
    private JTextField fld_roomAdd_room_ID;
    private JTextField fld_roomAdd_roomType;
    private JTextField fld_roomAdd_area;
    private JComboBox cmb_roomAdd_season;
    private JTextField fld_roomAdd_capacity;
    private JTextField fld_roomAdd_properties;
    private JButton btn_roomAdd_close;
    private JButton btn_roomAdd_save;
    private JPanel wrapper;
    private JLabel lbl_roomAdd_name;
    private Hotel hotel;
    private HotelSeason season;
    private Room room;

    public RoomAddGUI(Hotel hotel, Room room) {
        add(wrapper);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        btn_roomAdd_close.addActionListener(e -> {
            dispose();
        });

        if (room == null) {
            fld_roomAdd_hotel_ID.setText(String.valueOf(hotel.getId()));
        fld_roomAdd_hotel_name.setText(hotel.getName());


        for (HotelSeason hotelSeason : HotelSeason.getList(hotel.getId())) {
            boolean isDuplicate = false;

            for (int i = 0; i < cmb_roomAdd_season.getItemCount(); i++) {
                if (hotelSeason.getSeasonName().equals(cmb_roomAdd_season.getItemAt(i))) {
                    isDuplicate = true;
                    break;
                }
            }

            if (!isDuplicate) {
                cmb_roomAdd_season.addItem(hotelSeason.getSeasonName());
            }

        }


        btn_roomAdd_save.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_roomAdd_roomName) ||
                    Helper.isFieldEmpty(fld_roomAdd_hotel_name) ||
                    Helper.isFieldEmpty(fld_roomAdd_hotel_ID) ||
                    Helper.isFieldEmpty(fld_roomAdd_area) ||
                    Helper.isFieldEmpty(fld_roomAdd_capacity) ||
                    Helper.isFieldEmpty(fld_roomAdd_properties) ||
                    Helper.isFieldEmpty(fld_roomAdd_stock)) {
                Helper.showMsg("fill");
            } else {

                int hotel_id = Integer.parseInt(fld_roomAdd_hotel_ID.getText());
                String room_type = fld_roomAdd_roomName.getText();
                int area = Integer.parseInt(fld_roomAdd_area.getText());
                int capacity = Integer.parseInt(fld_roomAdd_capacity.getText());
                String properties = fld_roomAdd_properties.getText();
                int stock = Integer.parseInt(fld_roomAdd_stock.getText());
                String season_name = cmb_roomAdd_season.getSelectedItem().toString();
                int room_price_per_adult = 0;
                int room_price_per_child = 0;
                String type_hotel = "";
                int season_id = 0;

                Room room2 = new Room(room_type, stock, season_name, room_price_per_adult,
                        room_price_per_child, hotel_id, capacity, properties, area,
                        season_id ,type_hotel);

                if (Room.add(room2)) {
                    Helper.showMsg("success");
                    dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });


    } else if (room != null) {
            lbl_roomAdd_name.setText("Update Room Details");
            btn_roomAdd_save.setText("Update");
            fld_roomAdd_roomName.setText(room.getRoom_type());
            fld_roomAdd_hotel_name.setText(hotel.getName());
            fld_roomAdd_hotel_ID.setText(String.valueOf(hotel.getId()));
            fld_roomAdd_room_ID.setText(String.valueOf(room.getId()));
            fld_roomAdd_area.setText(String.valueOf(room.getArea()));
            fld_roomAdd_capacity.setText(String.valueOf(room.getCapacity()));
            fld_roomAdd_properties.setText(room.getProperties());
            fld_roomAdd_stock.setText(String.valueOf(room.getStock()));

            for (HotelSeason hotelSeason : HotelSeason.getList(hotel.getId())) {
                boolean isDuplicate = false;

                for (int i = 0; i < cmb_roomAdd_season.getItemCount(); i++) {
                    if (hotelSeason.getSeasonName().equals(cmb_roomAdd_season.getItemAt(i))) {
                        isDuplicate = true;
                        break;
                    }
                }

                if (!isDuplicate) {
                    cmb_roomAdd_season.addItem(hotelSeason.getSeasonName());
                }

            }

            for (int i = 0; i < cmb_roomAdd_season.getItemCount(); i++) {
                if (room.getSeason_name().equals(cmb_roomAdd_season.getItemAt(i))) {
                    cmb_roomAdd_season.setSelectedIndex(i);
                    break;
                }
            }

            btn_roomAdd_save.addActionListener(e -> {
                if (Helper.isFieldEmpty(fld_roomAdd_roomName) ||
                        Helper.isFieldEmpty(fld_roomAdd_hotel_name) ||
                        Helper.isFieldEmpty(fld_roomAdd_hotel_ID) ||
                        Helper.isFieldEmpty(fld_roomAdd_area) ||
                        Helper.isFieldEmpty(fld_roomAdd_capacity) ||
                        Helper.isFieldEmpty(fld_roomAdd_properties) ||
                        Helper.isFieldEmpty(fld_roomAdd_stock) ||
                        Helper.isFieldEmpty(fld_roomAdd_room_ID))
                {
                    Helper.showMsg("fill");
                } else {

                    int room_id = room.getId();
                    int hotel_id = Integer.parseInt(fld_roomAdd_hotel_ID.getText());
                    String room_type = fld_roomAdd_roomName.getText();
                    int area = Integer.parseInt(fld_roomAdd_area.getText());
                    int capacity = Integer.parseInt(fld_roomAdd_capacity.getText());
                    String properties = fld_roomAdd_properties.getText();
                    int stock = Integer.parseInt(fld_roomAdd_stock.getText());
                    String season_name = cmb_roomAdd_season.getSelectedItem().toString();
                    int room_price_per_adult = 0;
                    int room_price_per_child = 0;
                    int season_id = 0;

                    Room roomUpdate = new Room(room_id,room_type, stock, season_name, room_price_per_adult,
                            room_price_per_child, hotel_id, capacity, properties, area,
                            season_id, "");

                    if (Room.update(roomUpdate)) {
                        Helper.showMsg("success");
                        dispose();
                    } else {
                        Helper.showMsg("error");
                    }
                }

            });


        }

    }
}
