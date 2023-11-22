package View;

import Helper.Config;
import Helper.DBConnect;
import Model.RoomPrice;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookGUI extends JFrame{
    private JPanel panel1;
    private JPanel wrapper;
    private JTextField fld_book_room_id;
    private JTextField fld_book_room_name;
    private JButton btn_book_calculate;
    private JTextField fld_book_total_price;
    private JButton btn_book_save;
    private JTextField fld_book_guest_id;
    private JTextField fld_book_guest_name;
    private JTextField fld_book_guest_age;
    private JTextField fld_book_guest_phone;
    private JTextField fld_book_guest_email;
    private JTextField fld_book_book_note;
    private JTextField fld_book_adult_amount;
    private JTextField fld_book_child_amount;
    private JTextField fld_book_checkin;
    private JTextField fld_book_checkout;
    private JButton btn_book_exit;
    private  RoomPrice roomPrice;

    public BookGUI(RoomPrice roomPrice){
this.roomPrice=roomPrice;
    add(wrapper);
    setSize(700, 500);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setTitle(Config.PROJECT_TITLE);
    setVisible(true);

    }



    public static boolean delete(int id) {
        String query = "DELETE FROM reservation WHERE id = ?";

        try {
            PreparedStatement pr = DBConnect.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;

    }


}
