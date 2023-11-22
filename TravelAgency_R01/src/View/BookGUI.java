package View;
import Helper.Helper;
import Helper.Config;
import Helper.DBConnect;
import Model.Book;
import Model.Room;
import Model.RoomPrice;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BookGUI extends JFrame {
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
    private RoomPrice roomPrice;

    public BookGUI (RoomPrice roomPrice)
        {


            this.roomPrice = roomPrice;
            add (wrapper);
            setSize (700 , 500);
            setLocationRelativeTo (null);
            setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
            setTitle (Config.PROJECT_TITLE);
            setVisible (true);

            fld_book_checkin.setText ("  -  -    ");
            fld_book_checkout.setText ("  -  -    ");

            int room_id = roomPrice.getRoom_id ();
            Room room = Room.getFetchById (room_id);

            fld_book_room_name.setText (room.getRoom_type ());
            fld_book_room_id.setText (String.valueOf (roomPrice.getId ()));

                //reservation price calculate
            btn_book_calculate.addActionListener (e -> {
                try
                    {
                        SimpleDateFormat sdf = new SimpleDateFormat ("dd-MM-yyyy");

                        Date start_date = sdf.parse (fld_book_checkin.getText ());
                        Date end_date = sdf.parse (fld_book_checkout.getText ());

                        Calendar start = Calendar.getInstance ();
                        Calendar end = Calendar.getInstance ();

                        start.setTime (start_date);
                        end.setTime (end_date);

                        long diff = end.getTimeInMillis () - start.getTimeInMillis ();
                        long diffDays = diff / ( 24 * 60 * 60 * 1000 );
                        int days = (int) diffDays;

                        int total_price = days * (
                                ( Integer.parseInt (fld_book_child_amount.getText ()) * roomPrice.getRoom_price_per_child () ) +
                                        ( Integer.parseInt (fld_book_adult_amount.getText ()) * roomPrice.getRoom_price_per_adult () )
                        );

                        fld_book_total_price.setText (String.valueOf (total_price));


                    } catch (Exception exception)
                    {
                        exception.printStackTrace ();
                    }
            });
            //reservation add
            btn_book_save.addActionListener (e -> {

                Book reservation = new Book (room_id , fld_book_book_note.getText () , Integer.parseInt (fld_book_total_price.getText ()) ,
                        fld_book_checkin.getText () , fld_book_checkout.getText () ,
                        Integer.parseInt (fld_book_child_amount.getText ()) ,
                        Integer.parseInt (fld_book_adult_amount.getText ()) , fld_book_guest_name.getText () ,
                        fld_book_guest_id.getText () , Integer.parseInt (fld_book_guest_age.getText ()) ,
                        fld_book_guest_phone.getText () , fld_book_guest_email.getText ());
                if(Book.add(reservation)){
                    Helper.showMsg("Reservation added successfully!");
                    dispose();
                }else{
                    Helper.showMsg("Reservation could not be added!");
                }

            });

        }

        //delete method
    public static boolean delete (int id)
        {
            String query = "DELETE FROM reservation WHERE id = ?";

            try
                {
                    PreparedStatement pr = DBConnect.getInstance ().prepareStatement (query);
                    pr.setInt (1 , id);
                    return pr.executeUpdate () != -1;
                } catch (SQLException e)
                {
                    e.printStackTrace ();
                }
            return true;

        }


}
