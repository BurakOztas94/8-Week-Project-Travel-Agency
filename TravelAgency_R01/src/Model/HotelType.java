package Model;

import Helper.DBConnect;
import Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HotelType {

    private int id;
    private String type;

    private int hotelId;

    public HotelType() {

    }

    public HotelType(int id, String type, int hotelId) {
        this.id = id;
        this.type = type;
        this.hotelId = hotelId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public static ArrayList<HotelType> getList() {
        ArrayList<HotelType> typeList = new ArrayList<>();
        String query = "SELECT * FROM type_hotel";
        HotelType obj;
        try {
            Statement st = DBConnect.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new HotelType();
                obj.setId(rs.getInt("id"));
                obj.setType(rs.getString("type"));
                obj.setHotelId(rs.getInt("hotel_id"));


                typeList.add(obj);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typeList;

    }


    public Hotel getFetch(int id) {
        Hotel obj = null;
        String query = "SELECT * FROM hotel WHERE id = ?";
        try {
            PreparedStatement pr = DBConnect.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new Hotel();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setStar(rs.getString("star"));
                obj.setProperty(rs.getString("property"));
                obj.setAddress(rs.getString("address"));
                obj.setPhone(rs.getString("phone"));
                obj.setEmail(rs.getString("email"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public static HotelType getFetchByID(int id) {
        HotelType obj = null;
        String query = "SELECT * FROM type_hotel WHERE id = ?";
        try {
            PreparedStatement pr = DBConnect.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new HotelType();
                obj.setId(rs.getInt("id"));
                obj.setType(rs.getString("type"));
                obj.setHotelId(rs.getInt("hotel_id"));


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public static ArrayList<HotelType> getList(int id) {
        ArrayList<HotelType> hotelTypeArrayList = new ArrayList<>();
        String query = "SELECT * FROM type_hotel WHERE hotel_id = ?";
        HotelType obj;
        try {
            PreparedStatement st = DBConnect.getInstance().prepareStatement(query);
            st.setInt(1, id);  // Set the parameter
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                obj = new HotelType();
                obj.setId(rs.getInt("id"));
                obj.setType(rs.getString("type"));
                obj.setHotelId(rs.getInt("hotel_id"));
                hotelTypeArrayList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelTypeArrayList;
    }

    public static boolean add(String type, int hotel_id) {
        String query = "INSERT INTO type_hotel (type,hotel_id) VALUES (?,?)";

        try {
            PreparedStatement pr = DBConnect.getInstance().prepareStatement(query);
            pr.setString(1, type);
            pr.setInt(2, hotel_id);

            int response = pr.executeUpdate();

            if (response == -1) {
                Helper.showMsg("error");
            }
            return response != -1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return true;
    }

    public static boolean delete(int id) {
        String query = "DELETE FROM type_hotel WHERE id = ?";

        try {
            PreparedStatement pr = DBConnect.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;

    }

    public static String searchQuery(String hotel_id) {
        String query = "SELECT * FROM type_hotel WHERE hotel_id = '{{hotel_id}}'";

        query = query.replace("{{hotel_id}}", hotel_id);

        return query;

    }

    public static ArrayList<HotelType> searchHotelTypes(String query) {
        ArrayList<HotelType> hotelTypeArrayList = new ArrayList<>();

        try {
            Statement statement = DBConnect.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                hotelTypeArrayList.add(HotelType.match(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return hotelTypeArrayList;
    }

    public static HotelType match(ResultSet resultSet) throws SQLException {
        HotelType object = new HotelType();
        object.setId(resultSet.getInt("id"));
        object.setType(resultSet.getString("type"));
        object.setHotelId(resultSet.getInt("hotel_id"));
        return object;
    }

    public static HotelType getByHotel_Id_and_Type(int hotel_id, String type) {
        HotelType obj = null;
        String query = "SELECT * FROM type_hotel WHERE hotel_id = ? AND type = ?";
        try {
            PreparedStatement pr = DBConnect.getInstance().prepareStatement(query);
            pr.setInt(1, hotel_id);
            pr.setString(2, type);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new HotelType();
                obj.setId(rs.getInt("id"));
                obj.setType(rs.getString("type"));
                obj.setHotelId(rs.getInt("hotel_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
