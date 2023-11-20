package View;

import Helper.Config;
import Model.Employee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomAddGUI extends JFrame {


    private JPanel wrapper;
    private JComboBox cmb_room_hotel_name;
    private JTextField fld_room_bed_type;
    private JButton btn_room_add;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JRadioButton radioButton5;
    private JComboBox cmb_room_type;
    private JTextField fld_room_stock;
    private JComboBox cmb_room_board_type;
    private JComboBox cmb_room_season;
    private JTextField fld_room_adult_price;
    private JTextField fld_room_child_price;
    private JTextField fld_room_area;

    private String select_room_type;

private final Employee employee;

    public RoomAddGUI (Employee employee)
        {
            this.employee = employee;
            add (wrapper);
            setSize (600,400);
            setLocationRelativeTo (null);
            setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
            setTitle (Config.PROJECT_TITLE);
            setVisible (true);



            btn_room_add.addActionListener (e -> {


            });
        }
}
