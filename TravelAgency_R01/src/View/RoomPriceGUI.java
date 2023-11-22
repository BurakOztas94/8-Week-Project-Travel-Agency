package View;

import javax.swing.*;

import Helper.Config;
import Helper.Helper;
import Model.*;


public class RoomPriceGUI extends JFrame{
    private JPanel wrapper;
    private JTextField fld_roomPrice_priceId;
    private JTextField fld_room_price_roomId;
    private JTextField fld_roomPrice_hotelId;
    private JTextField fld_roomPrice_per_adult;
    private JTextField fld_roomPrice_per_child;
    private JComboBox cmb_roomPrice_season_name;
    private JComboBox cmb_roomPrice_hotelType;
    private JButton btn_roomPrice_close;
    private JButton btn_roomPrice_addInfo;

    public RoomPriceGUI(Room room) {
        add(wrapper);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        btn_roomPrice_close.addActionListener(e -> {
            dispose();
        });

        if (room != null) {
            fld_room_price_roomId.setText(String.valueOf(room.getId()));
            fld_roomPrice_hotelId.setText(String.valueOf(room.getHotel_id()));

            Hotel hotel = Hotel.getFetchById(room.getHotel_id());
            for (HotelSeason hotelSeason : HotelSeason.getList(hotel.getId())) {
                boolean isDuplicate = false;

                for (int i = 0; i < cmb_roomPrice_season_name.getItemCount(); i++) {
                    if (hotelSeason.getSeasonName().equals(cmb_roomPrice_season_name.getItemAt(i))) {
                        isDuplicate = true;
                        break;
                    }
                }

                if (!isDuplicate) {
                    cmb_roomPrice_season_name.addItem(hotelSeason.getSeasonName());
                }

            }


            for (HotelType hotelType : HotelType.getList(hotel.getId())) {
                boolean isDuplicate = false;

                for (int i = 0; i < cmb_roomPrice_hotelType.getItemCount(); i++) {
                    if (hotelType.equals(cmb_roomPrice_hotelType.getItemAt(i))) {
                        isDuplicate = true;
                        break;
                    }
                }

                if (!isDuplicate) {
                    cmb_roomPrice_hotelType.addItem(hotelType.getType());
                }
            }


            btn_roomPrice_addInfo.addActionListener(e -> {
                if (Helper.isFieldEmpty(fld_roomPrice_per_adult) || Helper.isFieldEmpty(fld_roomPrice_per_child)) {
                    Helper.showMsg("fill");
                } else {
                    int room_id = room.getId();
                    int hotel_id = room.getHotel_id();
                    String season_name = cmb_roomPrice_season_name.getSelectedItem().toString();
                    String hotel_type = cmb_roomPrice_hotelType.getSelectedItem().toString();
                    int per_adult = Integer.parseInt(fld_roomPrice_per_adult.getText());
                    int per_child = Integer.parseInt(fld_roomPrice_per_child.getText());
                    room.setRoom_price_per_child(per_child);
                    room.setRoom_price_per_adult(per_adult);
                    room.setHotel_type(hotel_type);
                    room.setSeason_name(season_name);

                    boolean roomUpdateResult = Room.update(room);

                    if (roomUpdateResult) {
                        Helper.showMsg("Room information is updated");
                    } else {
                        Helper.showMsg("error");
                    }

                    RoomPrice roomPrice = new RoomPrice();
                    roomPrice.setRoom_id(room_id);
                    roomPrice.setHotel_id(hotel_id);
                    roomPrice.setSeasonName(season_name);
                    roomPrice.setHotelType(hotel_type);
                    roomPrice.setRoom_price_per_adult(per_adult);
                    roomPrice.setRoom_price_per_child(per_child);

                    boolean result = RoomPrice.add(room, roomPrice);

                    if (result) {
                        Helper.showMsg("Price information is added");
                    } else {
                        Helper.showMsg("error");
                    }


                    dispose();
                }
            });

        }

    }
}
