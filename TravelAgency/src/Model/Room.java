package Model;

import Helper.DBConnect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Room {

    private int id;
    private  String room_type;
    private  int stock;
    private  int season_id;
    private  double adult_price;
    private  double child_price;
    private  int hotel_type_id;
    private  int hotel_id;

    public Room ()
        {

        }

    public Room (int id , String room_type , int stock , int season_id , double adult_price , double child_price , int hotel_type_id , int hotel_id)
        {
            this.id = id;
            this.room_type = room_type;
            this.stock = stock;
            this.season_id = season_id;
            this.adult_price = adult_price;
            this.child_price = child_price;
            this.hotel_type_id = hotel_type_id;
            this.hotel_id = hotel_id;
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

    public int getSeason_id ()
        {
            return season_id;
        }

    public void setSeason_id (int season_id)
        {
            this.season_id = season_id;
        }

    public double getAdult_price ()
        {
            return adult_price;
        }

    public void setAdult_price (double adult_price)
        {
            this.adult_price = adult_price;
        }

    public double getChild_price ()
        {
            return child_price;
        }

    public void setChild_price (double child_price)
        {
            this.child_price = child_price;
        }

    public int getHotel_type_id ()
        {
            return hotel_type_id;
        }

    public void setHotel_type_id (int hotel_type_id)
        {
            this.hotel_type_id = hotel_type_id;
        }

    public int getHotel_id ()
        {
            return hotel_id;
        }

    public void setHotel_id (int hotel_id)
        {
            this.hotel_id = hotel_id;
        }

    public static ArrayList<Room> getList ()

        {
            ArrayList<Room> roomList=new ArrayList<> ();
            String query = "SELECT * FROM room";
            Room obj;

            Statement st = null;
            try
                {
                    st = DBConnect.getInstance ().createStatement ();
                    ResultSet rs = st.executeQuery(query);
                    while (rs.next ()){
                        obj=new Room ();
                        obj.setId (rs.getInt ("id"));
                        obj.setRoom_type (rs.getString ("room_type"));
                        obj.setStock (rs.getInt ("stock"));
                        obj.setSeason_id (rs.getInt ("season_id"));
                        obj.setAdult_price (rs.getDouble ("adult_price"));
                        obj.setChild_price  (rs.getDouble ("child_price"));
                        obj.setHotel_type_id  (rs.getInt ("hotel_type_id"));
                        obj.setHotel_id  (rs.getInt ("hotel_id"));
                        roomList.add (obj);
                    }


                } catch (SQLException e)
                {
                   e.printStackTrace ();
                }
          return  roomList;


        }
}
