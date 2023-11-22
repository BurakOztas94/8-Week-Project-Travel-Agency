package Model;

import Helper.DBConnect;
import Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Room {

    private int id;
    private String room_type;
    private int stock;
    private String season_name;
    private int room_price_per_adult;
    private int room_price_per_child;
    private int hotel_id;
    private int capacity;
    private String properties;
    private int area;
    private int season_id;
    private String hotel_type;


    public Room ()
        {

        }

    public Room (int id , String room_type , int stock , String season_name , int room_price_per_adult ,
                 int room_price_per_child ,
                 int hotel_id , int capacity , String properties , int area , int season_id , String hotel_type)
        {
            this.id = id;
            this.room_type = room_type;
            this.stock = stock;
            this.season_name = season_name;
            this.room_price_per_adult = room_price_per_adult;
            this.room_price_per_child = room_price_per_child;
            this.hotel_id = hotel_id;
            this.capacity = capacity;
            this.properties = properties;
            this.area = area;
            this.season_id = season_id;
            this.hotel_type = hotel_type;

        }

    public Room (String room_type , int stock , String season_name , int room_price_per_adult ,
                 int room_price_per_child ,
                 int hotel_id , int capacity , String properties , int area , int season_id , String hotel_type)
        {

            this.room_type = room_type;
            this.stock = stock;
            this.season_name = season_name;
            this.room_price_per_adult = room_price_per_adult;
            this.room_price_per_child = room_price_per_child;
            this.hotel_id = hotel_id;
            this.capacity = capacity;
            this.properties = properties;
            this.area = area;
            this.season_id = season_id;
            this.hotel_type = hotel_type;


        }


    public int getSeason_id ()
        {
            return season_id;
        }

    public void setSeason_id (int season_id)
        {
            this.season_id = season_id;
        }

    public String getHotel_type ()
        {
            return hotel_type;
        }

    public void setHotel_type (String hotel_type_id)
        {
            this.hotel_type = hotel_type;
        }

    public int getCapacity ()
        {
            return capacity;
        }

    public void setCapacity (int capacity)
        {
            this.capacity = capacity;
        }

    public String getProperties ()
        {
            return properties;
        }

    public void setProperties (String properties)
        {
            this.properties = properties;
        }

    public String getSeason_name ()
        {
            return season_name;
        }

    public void setSeason_name (String season_name)
        {
            this.season_name = season_name;
        }

    public int getId ()
        {
            return id;
        }

    public void setId (int id)
        {
            this.id = id;
        }

    public String getRoom_type ()
        {
            return room_type;
        }

    public void setRoom_type (String room_type)
        {
            this.room_type = room_type;
        }

    public int getStock ()
        {
            return stock;
        }

    public void setStock (int stock)
        {
            this.stock = stock;
        }

    public int getRoom_price_per_adult ()
        {
            return room_price_per_adult;
        }

    public void setRoom_price_per_adult (int room_price_per_adult)
        {
            this.room_price_per_adult = room_price_per_adult;
        }

    public int getRoom_price_per_child ()
        {
            return room_price_per_child;
        }

    public void setRoom_price_per_child (int room_price_per_child)
        {
            this.room_price_per_child = room_price_per_child;
        }

    public int getHotel_id ()
        {
            return hotel_id;
        }

    public void setHotel_id (int hotel_id)
        {
            this.hotel_id = hotel_id;
        }

    public int getArea ()
        {
            return area;
        }

    public void setArea (int area)
        {
            this.area = area;
        }

    public static ArrayList<Room> getList ()
        {
            ArrayList<Room> roomList = new ArrayList<> ();
            String query = "SELECT * FROM room";
            Room obj;

            Statement st = null;
            try
                {
                    st = DBConnect.getInstance ().createStatement ();
                    ResultSet rs = st.executeQuery (query);
                    while (rs.next ())
                        {
                            obj = new Room ();
                            obj.setId (rs.getInt ("id"));
                            obj.setRoom_type (rs.getString ("room_type"));
                            obj.setStock (rs.getInt ("stock"));
                            obj.setSeason_name (rs.getString ("season_name"));
                            obj.setRoom_price_per_adult (rs.getInt ("room_price_per_adult"));
                            obj.setRoom_price_per_child (rs.getInt ("room_price_per_child"));
                            obj.setHotel_id (rs.getInt ("hotel_id"));
                            obj.setCapacity (rs.getInt ("capacity"));
                            obj.setProperties (rs.getString ("room_properties"));
                            obj.setArea (rs.getInt ("area"));
                            obj.setSeason_id (rs.getInt ("season_id"));
                            obj.setHotel_type (rs.getString ("type_hotel"));

                            roomList.add (obj);
                        }


                } catch (SQLException e)
                {
                    e.printStackTrace ();
                }
            return roomList;

        }

    public static boolean add (Room room)
        {

            String query = "INSERT INTO room (room_type,stock,season_name,room_price_per_adult," +
                    "room_price_per_child,hotel_id,capacity,room_properties,area, season_id, type_hotel )" +
                    " VALUES (?,?,?,?,?,?,?,?,?,?,?)";

            try
                {
                    PreparedStatement pr = DBConnect.getInstance ().prepareStatement (query);
                    pr.setString (1 , room.getRoom_type ());
                    pr.setInt (2 , room.getStock ());
                    pr.setString (3 , room.getSeason_name ());
                    pr.setInt (4 , room.getRoom_price_per_adult ());
                    pr.setInt (5 , room.getRoom_price_per_child ());
                    pr.setInt (6 , room.getHotel_id ());
                    pr.setInt (7 , room.getCapacity ());
                    pr.setString (8 , room.getProperties ());
                    pr.setInt (9 , room.getArea ());
                    pr.setInt (10 , room.getSeason_id ());
                    pr.setString (11 , room.getHotel_type ());

                    int response = pr.executeUpdate ();

                    if ( response == -1 )
                        {
                            Helper.showMsg ("error");
                        }
                    return response != -1;

                } catch (SQLException e)
                {
                    System.out.println (e.getMessage ());

                }
            return true;
        }

    public static Room getFetchById (int id)
        {
            Room obj = null;
            String query = "SELECT * FROM room WHERE id=?";

            PreparedStatement pr = null;
            try
                {
                    pr = DBConnect.getInstance ().prepareStatement (query);
                    pr.setInt (1 , id);
                    ResultSet rs = pr.executeQuery ();
                    if ( rs.next () )
                        {

                            obj = new Room ();
                            obj.setId (rs.getInt ("id"));
                            obj.setRoom_type (rs.getString ("room_type"));
                            obj.setStock (rs.getInt ("stock"));
                            obj.setSeason_name (rs.getString ("season_name"));
                            obj.setRoom_price_per_adult (rs.getInt ("room_price_per_adult"));
                            obj.setRoom_price_per_child (rs.getInt ("room_price_per_child"));
                            obj.setHotel_id (rs.getInt ("hotel_id"));
                            obj.setCapacity (rs.getInt ("capacity"));
                            obj.setProperties (rs.getString ("room_properties"));
                            obj.setArea (rs.getInt ("area"));
                            obj.setSeason_id (rs.getInt ("season_id"));
                            obj.setHotel_type (rs.getString ("type_hotel"));

                        }
                } catch (SQLException e)
                {
                    e.printStackTrace ();
                }
            return obj;

        }

    public static boolean update (Room room)
        {

            String query = "UPDATE room SET room_type=?,stock=?,season_name=?,room_price_per_adult=?," +
                    "room_price_per_child=?,hotel_id=?,capacity=?,room_properties=?,area=?,season_id=?," +
                    "type_hotel=? WHERE id=?";

            try
                {
                    PreparedStatement pr = DBConnect.getInstance ().prepareStatement (query);
                    pr.setString (1 , room.getRoom_type ());
                    pr.setInt (2 , room.getStock ());
                    pr.setString (3 , room.getSeason_name ());
                    pr.setInt (4 , room.getRoom_price_per_adult ());
                    pr.setInt (5 , room.getRoom_price_per_child ());
                    pr.setInt (6 , room.getHotel_id ());
                    pr.setInt (7 , room.getCapacity ());
                    pr.setString (8 , room.getProperties ());
                    pr.setInt (9 , room.getArea ());
                    pr.setInt (10 , room.getSeason_id ());
                    pr.setString (11 , room.getHotel_type ());
                    pr.setInt (12 , room.getId ());


                    return pr.executeUpdate () != -1;

                } catch (Exception e)
                {
                    e.printStackTrace ();
                }

            return true;
        }

    public static boolean delete (int id)
        {
            String query = "DELETE FROM room WHERE id = ?";

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

    public static String searchQuery (String name , String address)
        {
            String query = "SELECT * FROM room INNER JOIN hotel ON room.hotel_id = hotel.id " +
                    "WHERE name LIKE '%{{name}}%' AND " +
                    "address LIKE '%{{address}}%'";

            query = query.replace ("{{name}}" , name);
            query = query.replace ("{{address}}" , address);

            return query;

        }

    public static ArrayList<Room> searchRoomList (String query)
        {
            ArrayList<Room> roomArrayList = new ArrayList<> ();

            try
                {
                    Statement statement = DBConnect.getInstance ().createStatement ();
                    ResultSet resultSet = statement.executeQuery (query);

                    while (resultSet.next ())
                        {
                            roomArrayList.add (match (resultSet));
                        }
                } catch (SQLException e)
                {
                    e.printStackTrace ();

                }
            return roomArrayList;
        }

    public static Room match (ResultSet resultSet) throws SQLException
        {
            Room object = new Room ();

            object.setId (resultSet.getInt ("room.id"));
            object.setRoom_type (resultSet.getString ("room_type"));
            object.setStock (resultSet.getInt ("stock"));
            object.setSeason_name (resultSet.getString ("season_name"));
            object.setRoom_price_per_adult (resultSet.getInt ("room_price_per_adult"));
            object.setRoom_price_per_child (resultSet.getInt ("room_price_per_child"));
            object.setHotel_id (resultSet.getInt ("hotel_id"));
            object.setCapacity (resultSet.getInt ("capacity"));
            object.setProperties (resultSet.getString ("room_properties"));
            object.setArea (resultSet.getInt ("area"));
            object.setSeason_id (resultSet.getInt ("season_id"));
            object.setHotel_type (resultSet.getString ("type_hotel"));

            return object;
        }


}
