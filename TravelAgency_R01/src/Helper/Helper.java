package Helper;

import javax.swing.*;
import java.awt.*;

public class Helper {
    public static void setLayout() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if (("Nimbus".equals(info.getName()))) {

                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                break;


            }
        }
    }


    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    public static boolean isAreaEmpty(JTextPane textArea) {
        return textArea.getText().trim().isEmpty();
    }

    public static void showMsg(String str) {
        optionPageEN();
        String msg;
        String title;

        switch (str) {
            case "fill":
                msg = "Please ensure all required fields are filled ! ";
                title = "Error";
                break;
            case "done":
                msg = "Successfully completed !";
                title = "Result";
                break;
            case "error":
                msg = "An error occurred !";
                title = "Result";
                break;
            default:
                msg = str;
                title = "Message";
                break;

        }
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(String str) {
        optionPageEN();
        String msg;
        switch (str) {
            case "sure":
                msg = "Are you sure to delete this item?";
                break;
            default:
                msg = str;
                break;
        }
        return JOptionPane.showConfirmDialog(null, msg, "Are you really sure ? ", JOptionPane.YES_NO_OPTION) == 0;
    }

    public static void optionPageEN() {
        UIManager.put("OptionPane.okButtonText", "Okay");
        UIManager.put("OptionPane.yesButtonText", "Yes");
        UIManager.put("OptionPane.noButtonText", "No");
    }

    public static String hotelType(String number) {
        String type = "";
        switch (number) {
            case "1":
                type = "Ultra All-Inclusive";
                break;
            case "2":
                type = "All-Inclusive";
                break;
            case "3":
                type = "Bed and Breakfast";
                break;
            case "4":
                type = "Full Board";
                break;
            case "5":
                type = "Half Board";
                break;
            case "6":
                type = "Room Only";
                break;
            case "7":
                type = "Ultra - Alcohol Excluded";
                break;
            default:
                break;
        }
        return type;
    }


}
