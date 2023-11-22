package Model;

import Helper.DBConnect;
import Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RoomPrice {

    private int id;
    private int room_id;
    private int hotel_id;
    private int room_price_per_adult;
    private int room_price_per_child;
    private String seasonName;
    private String hotelType;

    public RoomPrice() {
    }

    public RoomPrice(int id, int room_id, int hotel_id, int room_price_per_adult,
                     int room_price_per_child, String seasonName, String hotelType) {
        this.id = id;
        this.room_id = room_id;
        this.hotel_id = hotel_id;
        this.room_price_per_adult = room_price_per_adult;
        this.room_price_per_child = room_price_per_child;
        this.seasonName = seasonName;
        this.hotelType = hotelType;
    }

    public RoomPrice( int room_id, int hotel_id, int room_price_per_adult,
                     int room_price_per_child, String seasonName, String hotelType) {
        this.id = id;
        this.room_id = room_id;
        this.hotel_id = hotel_id;
        this.room_price_per_adult = room_price_per_adult;
        this.room_price_per_child = room_price_per_child;
        this.seasonName = seasonName;
        this.hotelType = hotelType;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getRoom_price_per_adult() {
        return room_price_per_adult;
    }

    public void setRoom_price_per_adult(int room_price_per_adult) {
        this.room_price_per_adult = room_price_per_adult;
    }

    public int getRoom_price_per_child() {
        return room_price_per_child;
    }

    public void setRoom_price_per_child(int room_price_per_child) {
        this.room_price_per_child = room_price_per_child;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public String getHotelType() {
        return hotelType;
    }

    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
    }

    public static boolean add (Room room, RoomPrice roomPrice )
    {

        String query = "INSERT INTO roomPrice (room_id, hotel_id, room_price_per_adult," +
                " room_price_per_child, seasonName, hotelType) " +
                "VALUES (?,?,?,?,?,?)";

        try
        {
            PreparedStatement pr = DBConnect.getInstance ().prepareStatement (query);
            pr.setInt (1, roomPrice.getRoom_id());
            pr.setInt(2, roomPrice.getHotel_id());
            pr.setInt(3, roomPrice.getRoom_price_per_adult());
            pr.setInt(4, roomPrice.getRoom_price_per_child());
            pr.setString(5, roomPrice.getSeasonName());
            pr.setString(6, roomPrice.getHotelType());

            int response = pr.executeUpdate ();

            if(response==-1){
                Helper.showMsg ("error");
            }
            return  response!=-1;

        } catch (SQLException e)
        {
            System.out.println (e.getMessage ());

        }
        return true;
    }

    public static boolean delete(int id) {
        String query = "DELETE FROM roomPrice WHERE id = ?";

        try {
            PreparedStatement pr = DBConnect.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;

    }

    public static ArrayList<RoomPrice> getList ()
    {
        ArrayList<RoomPrice> priceArrayList = new ArrayList<> ();
        String query = "SELECT * FROM roomPrice";
        RoomPrice obj;
        try
        {
            Statement st = DBConnect.getInstance ().createStatement ();
            ResultSet rs = st.executeQuery (query);
            while (rs.next ())
            {

                obj = new RoomPrice ();
                obj.setId (rs.getInt ("id"));
                obj.setRoom_id (rs.getInt ("room_id"));
                obj.setHotel_id (rs.getInt ("hotel_id"));
                obj.setRoom_price_per_adult (rs.getInt ("room_price_per_adult"));
                obj.setRoom_price_per_child (rs.getInt ("room_price_per_child"));
                obj.setSeasonName (rs.getString ("seasonName"));
                obj.setHotelType (rs.getString ("hotelType"));

                priceArrayList.add (obj);

            }

        } catch (SQLException e)
        {
            e.printStackTrace ();
        }
        return priceArrayList;

    }

    public static String searchQuery(String room_id) {
        String query = "SELECT * FROM roomPrice WHERE room_id = '{{room_id}}'";

        query = query.replace("{{room_id}}", room_id);

        return query;

    }

    public static ArrayList<RoomPrice> searchSeasonList(String query) {
        ArrayList<RoomPrice> roomPriceArrayList = new ArrayList<>();

        try {
            Statement statement = DBConnect.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                roomPriceArrayList.add(RoomPrice.match(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return roomPriceArrayList;
    }

    public static RoomPrice match(ResultSet resultSet) throws SQLException {
        RoomPrice object = new RoomPrice();

        object.setId(resultSet.getInt("id"));
        object.setRoom_id(resultSet.getInt("room_id"));
        object.setHotel_id(resultSet.getInt("hotel_id"));
        object.setRoom_price_per_adult(resultSet.getInt("room_price_per_adult"));
        object.setRoom_price_per_child(resultSet.getInt("room_price_per_child"));
        object.setSeasonName(resultSet.getString("seasonName"));
        object.setHotelType(resultSet.getString("hotelType"));

        return object;
    }


    public static RoomPrice getFetchById (int id)
        {
            RoomPrice obj = null;
            String query = "SELECT * FROM roomprice WHERE id = ?";

            PreparedStatement pr = null;
            try
                {
                    pr = DBConnect.getInstance ().prepareStatement (query);
                    pr.setInt (1 , id);
                    ResultSet rs = pr.executeQuery ();
                    if ( rs.next () )
                        {

                            obj = new RoomPrice();
                            obj.setId(rs.getInt("id"));
                            obj.setRoom_id (rs.getInt("room_id"));
                            obj.setHotel_id (rs.getInt("hotel_id"));
                            obj.setRoom_price_per_adult (rs.getInt("room_price_per_adult"));
                            obj.setRoom_price_per_child (rs.getInt("room_price_per_child"));
                            obj.setSeasonName (rs.getString ("seasonName"));
                            obj.setHotelType (rs.getString ("hotelType"));


                        }
                } catch (SQLException e)
                {
                    e.printStackTrace ();
                }
            return obj;


        }


}
