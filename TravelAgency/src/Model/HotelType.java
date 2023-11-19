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

    public HotelType ()
        {

        }

    public HotelType (int id , String type , int hotelId)
        {
            this.id = id;
            this.type = type;
            this.hotelId = hotelId;
        }




    public int getId ()
        {
            return id;
        }

    public void setId (int id)
        {
            this.id = id;
        }

    public String getType ()
        {
            return type;
        }

    public void setType (String type)
        {
            this.type = type;
        }

    public int getHotelId ()
        {
            return hotelId;
        }

    public void setHotelId (int hotelId)
        {
            this.hotelId = hotelId;
        }

    public static ArrayList<HotelType> getList ()
        {
            ArrayList<HotelType> typeList = new ArrayList<> ();
            String query = "SELECT * FROM type_hotel";
            HotelType obj;
            try
                {
                    Statement st = DBConnect.getInstance ().createStatement ();
                    ResultSet rs = st.executeQuery (query);
                    while (rs.next ())
                        {
                            obj = new HotelType ();
                            obj.setId (rs.getInt ("id"));
                            obj.setType (rs.getString ("type"));
                            obj.setHotelId (rs.getInt ("hotel_id"));


                            typeList.add (obj);

                        }

                } catch (SQLException e)
                {
                    e.printStackTrace ();
                }
            return typeList;

        }


    public Hotel getFetch (int id )
        {
            Hotel obj =null;
            String query = "SELECT * FROM hotel WHERE id = ?";
            try {
                PreparedStatement pr = DBConnect.getInstance().prepareStatement(query);
                pr.setInt(1,id);
                ResultSet rs = pr.executeQuery();
                if (rs.next()) {
                    obj = new Hotel ();
                    obj.setId (rs.getInt("id"));
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

    public static boolean add ( String type ,  int hotel_id )
        {
            String query = "INSERT INTO type_hotel (type,hotel_id) VALUES (?,?)";

            try
                {
                    PreparedStatement pr = DBConnect.getInstance ().prepareStatement (query);
                    pr.setString (1,type);
                    pr.setInt (2,hotel_id);

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

    public static boolean delete (int id)
        {
            String query = "DELETE FROM type_hotel WHERE hotel_id = ?";

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
