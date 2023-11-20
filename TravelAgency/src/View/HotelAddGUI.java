package View;

import Helper.Config;
import Helper.Helper;
import Model.Employee;
import Model.Hotel;
import Model.HotelSeason;
import Model.HotelType;
import Helper.DBConnect;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelAddGUI extends JFrame {

    private final Employee employee;

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

    private String select_star;
    private int added_hotel_id;
    private int added_hotel_season_id;
    private int added_hotel_type_id;


    //9.değerlendirme formu
    //save işleminden sonra lütfen update tuşuna basınız yoksa tablo refresh etmemekte

    public HotelAddGUI (Employee employee)
        {
            this.employee=employee;
            add (wrapper);
            setSize (800 , 600);
            setLocationRelativeTo (null);
            setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
            setTitle (Config.PROJECT_TITLE);
            setVisible (true);

            radioButton1.setText (Helper.hotelType ("1"));
            radioButton2.setText (Helper.hotelType ("2"));
            radioButton3.setText (Helper.hotelType ("3"));
            radioButton4.setText (Helper.hotelType ("4"));
            radioButton5.setText (Helper.hotelType ("5"));
            radioButton6.setText (Helper.hotelType ("6"));
            radiobutton7.setText (Helper.hotelType ("7"));

            select_star = cmb_hotel_star.getSelectedItem ().toString ();


            btn_hotel_add.addActionListener (e -> {

                        if ( Helper.isFieldEmpty (fld_hotel_name) || Helper.isAreaEmpty (textarea_hotel_property) || Helper.isAreaEmpty (textarea_hotel_address) || Helper.isFieldEmpty (fld_hotel_phone) || Helper.isFieldEmpty (fld_hotel_email) || ( !radioButton1.isSelected () && !radioButton2.isSelected () && !radioButton3.isSelected () && !radioButton4.isSelected () && !radioButton5.isSelected () && !radioButton6.isSelected () && !radiobutton7.isSelected () ) || Helper.isFieldEmpty (fld_hotel_season_start1) || Helper.isFieldEmpty (fld_hotel_season_end1) )
                            {

                                Helper.showMsg ("fill");
                            }
                        else
                            {
                                String name = fld_hotel_name.getText ();
                                String star = (String) cmb_hotel_star.getSelectedItem ();
                                String property = textarea_hotel_property.getText ();
                                String address = textarea_hotel_address.getText ();
                                String phone = fld_hotel_phone.getText ();
                                String email = fld_hotel_email.getText ();
                                String season_star1 = fld_hotel_season_start1.getText ();
                                String season_end1 = fld_hotel_season_end1.getText ();
                                String season_star2 = fld_hotel_season_start2.getText ();
                                String season_end2 = fld_hotel_season_end2.getText ();

                                    //field left part control
                                if ( Hotel.add (name , star , property , address , phone , email) )
                                    {
                                        Hotel addedHotel = Hotel.getFetch (email);
                                        added_hotel_id = addedHotel.getId ();

                                        //season control

                                        if(!Helper.isFieldEmpty (fld_hotel_season_start1)&&!Helper.isFieldEmpty (fld_hotel_season_end1)){
                                            HotelSeason.add ("Winter",season_star1,season_end1,added_hotel_id);
                                        }

                                        if(!Helper.isFieldEmpty (fld_hotel_season_start2)&&!Helper.isFieldEmpty (fld_hotel_season_end2)){
                                            HotelSeason.add ("Summer",season_star2,season_end2,added_hotel_id);
                                        }


                                                //radio button control


                                                if ( radioButton1.isSelected () )
                                                    {
                                                        HotelType.add (radioButton1.getText () , added_hotel_id);

                                                    }

                                                if ( radioButton2.isSelected () )
                                                    {
                                                        HotelType.add (radioButton2.getText () , added_hotel_id);

                                                    }

                                                if ( radioButton3.isSelected () )
                                                    {
                                                        HotelType.add (radioButton3.getText () , added_hotel_id);

                                                    }

                                                if ( radioButton4.isSelected () )
                                                    {
                                                        HotelType.add (radioButton4.getText () , added_hotel_id);

                                                    }

                                                if ( radioButton5.isSelected () )
                                                    {
                                                        HotelType.add (radioButton5.getText () , added_hotel_id);

                                                    }

                                                if ( radioButton6.isSelected () )
                                                    {
                                                        HotelType.add (radioButton6.getText () , added_hotel_id);

                                                    }
                                                if ( radiobutton7.isSelected () )
                                                    {
                                                        HotelType.add (radiobutton7.getText () , added_hotel_id);

                                                    }

                                                Helper.showMsg ("done");

                                    }


                                dispose ();
                            }


        });


}

    public static Hotel getFetchById (int id)
        {
            Hotel obj = null;
            String query = "SELECT * FROM hotel WHERE id=?";

            PreparedStatement pr = null;
            try
                {
                    pr = DBConnect.getInstance ().prepareStatement (query);
                    pr.setInt (1 , id);
                    ResultSet rs = pr.executeQuery ();
                    if ( rs.next () )
                        {
                            obj = new Hotel (rs.getInt ("id") , rs.getString ("name") , rs.getString ("star") , rs.getString ("property") , rs.getString ("address") , rs.getString ("phone") , rs.getString ("email"));


                        }
                } catch (SQLException e)
                {
                    e.printStackTrace ();
                }
            return obj;

        }
}