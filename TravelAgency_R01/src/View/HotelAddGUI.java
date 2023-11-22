package View;

import Helper.Config;
import Helper.Helper;
import Model.Hotel;
import Model.HotelSeason;
import Model.HotelType;

import javax.swing.*;

public class HotelAddGUI extends JFrame {

    private HotelType hotelType;
    private Hotel hotel;
    private JPanel wrapper;
    private JTextField fld_hotel_name;
    private JComboBox cmb_hotel_star;
    private JTextPane textarea_hotel_property;
    private JTextPane textarea_hotel_address;
    private JTextField fld_hotel_phone;
    private JTextField fld_hotel_email;

    private JRadioButton radioButton6;
    private JRadioButton radioButton1;
    private JRadioButton radioButton5;
    private JRadioButton radioButton4;
    private JRadioButton radioButton3;
    private JRadioButton radioButton2;
    private JTextField fld_hotel_season_end1;
    private JTextField fld_hotel_season_start1;
    private JTextField fld_hotel_season_end2;
    private JTextField fld_hotel_season_start2;
    private JButton btn_hotel_add;
    private JRadioButton radiobutton7;
    private JRadioButton rb_season1;
    private JRadioButton rb_season2;
    private JLabel lbl_hotel_save;

    private String select_star;
    private int added_hotel_id;
    private int added_hotel_season_id;
    private int added_hotel_type_id;
    boolean isUpdate;
    HotelSeason hotelSeason;


    //9.değerlendirme formu
    //save işleminden sonra lütfen update tuşuna basınız yoksa tablo refresh etmemekte

    public HotelAddGUI(Hotel hotel, HotelType hotelType, HotelSeason hotelSeason, boolean isUpdate) {
        this.hotel = hotel;
        this.hotelType = hotelType;
        this.hotelSeason = hotelSeason;
        this.isUpdate = isUpdate;
        add(wrapper);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        radioButton1.setText(Helper.hotelType("1"));
        radioButton2.setText(Helper.hotelType("2"));
        radioButton3.setText(Helper.hotelType("3"));
        radioButton4.setText(Helper.hotelType("4"));
        radioButton5.setText(Helper.hotelType("5"));
        radioButton6.setText(Helper.hotelType("6"));
        radiobutton7.setText(Helper.hotelType("7"));

        select_star = cmb_hotel_star.getSelectedItem().toString();
        String email = fld_hotel_email.getText();
        Hotel addedHotel = Hotel.getFetch(email);

        // Hotel Add Operation
        if (isUpdate == false && hotel == null) {
            btn_hotel_add.addActionListener(e -> {

                if (Helper.isFieldEmpty(fld_hotel_name) || Helper.isAreaEmpty(textarea_hotel_property) ||
                        Helper.isAreaEmpty(textarea_hotel_address) || Helper.isFieldEmpty(fld_hotel_phone) ||
                        Helper.isFieldEmpty(fld_hotel_email) ||
                        (!radioButton1.isSelected() && !radioButton2.isSelected() && !radioButton3.isSelected() &&
                                !radioButton4.isSelected() && !radioButton5.isSelected() &&
                                !radioButton6.isSelected() && !radiobutton7.isSelected()) ||
                        Helper.isFieldEmpty(fld_hotel_season_start1) ||
                        Helper.isFieldEmpty(fld_hotel_season_end1)) {

                    Helper.showMsg("fill");
                } else {
                    String name = fld_hotel_name.getText();
                    String star = (String) cmb_hotel_star.getSelectedItem();
                    String property = textarea_hotel_property.getText();
                    String address = textarea_hotel_address.getText();
                    String phone = fld_hotel_phone.getText();
                    String email2 = fld_hotel_email.getText();
                    String season_star1 = fld_hotel_season_start1.getText();
                    String season_end1 = fld_hotel_season_end1.getText();
                    String season_star2 = fld_hotel_season_start2.getText();
                    String season_end2 = fld_hotel_season_end2.getText();

                    //field left part control
                    if (Hotel.add(name, star, property, address, phone, email2)) {

                        Hotel hotel2 = Hotel.getFetch(fld_hotel_email.getText());
                        int added_hotel_id = hotel2.getId();


                        //season control

                        if (rb_season1.isSelected()) {
                            HotelSeason.add("Winter", season_star1, season_end1, added_hotel_id);
                        }

                        if (rb_season2.isSelected()) {
                            HotelSeason.add("Summer", season_star2, season_end2, added_hotel_id);
                        }

                        //radio button control


                        if (radioButton1.isSelected()) {
                            HotelType.add(radioButton1.getText(), added_hotel_id);

                        }

                        if (radioButton2.isSelected()) {
                            HotelType.add(radioButton2.getText(), added_hotel_id);

                        }

                        if (radioButton3.isSelected()) {
                            HotelType.add(radioButton3.getText(), added_hotel_id);

                        }

                        if (radioButton4.isSelected()) {
                            HotelType.add(radioButton4.getText(), added_hotel_id);

                        }

                        if (radioButton5.isSelected()) {
                            HotelType.add(radioButton5.getText(), added_hotel_id);

                        }

                        if (radioButton6.isSelected()) {
                            HotelType.add(radioButton6.getText(), added_hotel_id);

                        }
                        if (radiobutton7.isSelected()) {
                            HotelType.add(radiobutton7.getText(), added_hotel_id);

                        }
                        Helper.showMsg("done");

                    } else {
                        Helper.showMsg("error");
                    }

                    dispose();
                }
            });

        } else if (isUpdate == true && hotel != null && hotelType != null && hotelSeason != null) {

            btn_hotel_add.setText("Update");
            lbl_hotel_save.setText("Update Hotel Screen");

            fld_hotel_name.setText(hotel.getName());
            cmb_hotel_star.setSelectedItem(hotel.getStar());
            textarea_hotel_property.setText(hotel.getProperty());
            textarea_hotel_address.setText(hotel.getAddress());
            fld_hotel_phone.setText(hotel.getPhone());
            fld_hotel_email.setText(hotel.getEmail());
            Hotel hotel1 = Hotel.getFetch(Hotel.getFetch(fld_hotel_email.getText()).getEmail());

            for (HotelSeason season : HotelSeason.getList(hotel1.getId())) {
                if (season.getHotelId() == hotel1.getId()) {

                    if (season.getSeasonStart().equals("01/11/2023")) {
                        rb_season1.setSelected(true);
                    } else if (season.getSeasonStart().equals("01/05/2024")) {
                        rb_season2.setSelected(true);
                    }
                }
            }

            for (HotelType type : HotelType.getList(hotel1.getId())) {
                if (type.getHotelId() == hotel1.getId()) {
                    if (type.getType().equals("Ultra All-Inclusive")) {
                        radioButton1.setSelected(true);
                    } else if (type.getType().equals("All-Inclusive")) {
                        radioButton2.setSelected(true);
                    } else if (type.getType().equals("Bed and Breakfast")) {
                        radioButton3.setSelected(true);
                    } else if (type.getType().equals("Full Board")) {
                        radioButton4.setSelected(true);
                    } else if (type.getType().equals("Half Board")) {
                        radioButton5.setSelected(true);
                    } else if (type.getType().equals("Room Only")) {
                        radioButton6.setSelected(true);
                    } else if (type.getType().equals("Ultra - Alcohol Excluded")) {
                        radiobutton7.setSelected(true);
                    }
                }
            }

            btn_hotel_add.addActionListener(e -> {

                String name = fld_hotel_name.getText();
                String star = (String) cmb_hotel_star.getSelectedItem();
                String property = textarea_hotel_property.getText();
                String address = textarea_hotel_address.getText();
                String phone = fld_hotel_phone.getText();
                String email2 = fld_hotel_email.getText();
                String season_star1 = fld_hotel_season_start1.getText();
                String season_end1 = fld_hotel_season_end1.getText();
                String season_star2 = fld_hotel_season_start2.getText();
                String season_end2 = fld_hotel_season_end2.getText();

                if (!fld_hotel_name.equals("") && !textarea_hotel_property.equals("") &&
                        textarea_hotel_address.equals("") && !fld_hotel_phone.equals("") &&
                        !fld_hotel_email.equals("") &&
                        (radioButton1.isSelected() || radioButton2.isSelected() || radioButton3.isSelected() ||
                                radioButton4.isSelected() || radioButton5.isSelected() ||
                                radioButton6.isSelected() || radiobutton7.isSelected()) ||
                        (rb_season1.isSelected() || rb_season2.isSelected())) {

                    System.out.println("fill");


                    boolean updateRecord = false;
                    hotel.setName(fld_hotel_name.getText());
                    hotel.setStar((String) cmb_hotel_star.getSelectedItem());
                    hotel.setProperty(textarea_hotel_property.getText());
                    hotel.setAddress(textarea_hotel_address.getText());
                    hotel.setPhone(fld_hotel_phone.getText());
                    hotel.setEmail(fld_hotel_email.getText());
                    updateRecord = Hotel.update(hotel);

                    if (updateRecord) {
                        Helper.showMsg("Hotel info updated.");
                        dispose();
                    } else {
                        Helper.showMsg("error");
                    }
                    int hotel_id = hotel.getId();

                    if (rb_season1.isSelected() && HotelSeason.getFetch("Winter",hotel_id) == null)  {
                        HotelSeason.add("Winter", season_star1, season_end1, hotel.getId());
                    }

                    if (rb_season2.isSelected() && HotelSeason.getFetch("Summer",hotel_id) == null) {
                        HotelSeason.add("Summer", season_star2, season_end2, hotel.getId());
                    }

                    if (!rb_season1.isSelected()) {

                        try {
                            HotelSeason deleteSeason1 = new HotelSeason().getFetch("Winter", hotel.getId());
                            HotelSeason.delete(deleteSeason1.getSeasonId());

                        } catch (Exception exception)
                        {

                        }

                    }

                    if (!rb_season2.isSelected()) {
                        try {

                            HotelSeason deleteSeason2 = new HotelSeason().getFetch("Summer", hotel.getId());
                            HotelSeason.delete(deleteSeason2.getSeasonId());

                        } catch (Exception exception) {

                        }
                    }

                    //radio button control
                    if (radioButton1.isSelected() && HotelType.getByHotel_Id_and_Type(hotel.getId(),
                            radioButton1.getText()) == null) {
                        HotelType.add(radioButton1.getText(), hotel.getId());

                    }

                    if (!radioButton1.isSelected() && HotelType.getByHotel_Id_and_Type(hotel.getId(),
                            radioButton1.getText()) != null) {
                        HotelType.delete(HotelType.getByHotel_Id_and_Type(hotel.getId(),
                                radioButton1.getText()).getId());
                    }

                    if (radioButton2.isSelected() && HotelType.getByHotel_Id_and_Type(hotel.getId(),
                            radioButton2.getText()) == null) {
                        HotelType.add(radioButton2.getText(), hotel.getId());

                    }
                    if (!radioButton2.isSelected() && HotelType.getByHotel_Id_and_Type(hotel.getId(),
                            radioButton2.getText()) != null) {
                        HotelType.delete(HotelType.getByHotel_Id_and_Type(hotel.getId(),
                                radioButton2.getText()).getId());

                    }

                    if (radioButton3.isSelected() && HotelType.getByHotel_Id_and_Type(hotel.getId(),
                            radioButton3.getText()) == null) {
                        HotelType.add(radioButton3.getText(), hotel.getId());

                    }
                    if (!radioButton3.isSelected() && HotelType.getByHotel_Id_and_Type(hotel.getId(),
                            radioButton3.getText()) != null) {
                        HotelType.delete(HotelType.getByHotel_Id_and_Type(hotel.getId(),
                                radioButton3.getText()).getId());

                    }

                    if (radioButton4.isSelected() && HotelType.getByHotel_Id_and_Type(hotel.getId(),
                            radioButton4.getText()) == null) {
                        HotelType.add(radioButton4.getText(), hotel.getId());

                    }
                    if (!radioButton4.isSelected() && HotelType.getByHotel_Id_and_Type(hotel.getId(),
                            radioButton4.getText()) != null) {
                        HotelType.delete(HotelType.getByHotel_Id_and_Type(hotel.getId(),
                                radioButton4.getText()).getId());

                    }

                    if (radioButton5.isSelected() && HotelType.getByHotel_Id_and_Type(hotel.getId(),
                            radioButton5.getText()) == null) {
                        HotelType.add(radioButton5.getText(), hotel.getId());

                    }

                    if (!radioButton5.isSelected() && HotelType.getByHotel_Id_and_Type(hotel.getId(),
                            radioButton5.getText()) != null) {
                        HotelType.delete(HotelType.getByHotel_Id_and_Type(hotel.getId(),
                                radioButton5.getText()).getId());

                    }

                    if (radioButton6.isSelected() && HotelType.getByHotel_Id_and_Type(hotel.getId(),
                            radioButton6.getText()) == null) {
                        HotelType.add(radioButton6.getText(), hotel.getId());

                    }

                    if (!radioButton6.isSelected() && HotelType.getByHotel_Id_and_Type(hotel.getId(),
                            radioButton6.getText()) != null) {
                        HotelType.delete(HotelType.getByHotel_Id_and_Type(hotel.getId(),
                                radioButton6.getText()).getId());

                    }

                    if (radiobutton7.isSelected() && HotelType.getByHotel_Id_and_Type(hotel.getId(),
                            radiobutton7.getText()) == null) {
                        HotelType.add(radiobutton7.getText(), hotel.getId());
                    }

                    if (!radiobutton7.isSelected() && HotelType.getByHotel_Id_and_Type(hotel.getId(),
                            radiobutton7.getText()) != null) {
                        HotelType.delete(HotelType.getByHotel_Id_and_Type(hotel.getId(),
                                radiobutton7.getText()).getId());

                    }

                    Helper.showMsg("done");
                }
            });
        }

    }
}
