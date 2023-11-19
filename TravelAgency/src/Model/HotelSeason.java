package Model;

import Helper.DBConnect;
import Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// 10. değerlendirme sorusu
public class HotelSeason {

    private int seasonId;


    private String seasonName;

    private String seasonStart;
    private String seasonEnd;
    private int hotelId;

    public HotelSeason ()
        {
        }

    public HotelSeason (int seasonId , String seasonName , String seasonStart , String seasonEnd , int hotelId)
        {
            this.seasonId = seasonId;
            this.seasonName = seasonName;
            this.seasonStart = seasonStart;
            this.seasonEnd = seasonEnd;
            this.hotelId = hotelId;
        }




    public int getSeasonId ()
        {
            return seasonId;
        }

    public void setSeasonId (int seasonId)
        {
            this.seasonId = seasonId;
        }

    public String getSeasonName ()
        {
            return seasonName;
        }

    public void setSeasonName (String seasonName)
        {
            this.seasonName = seasonName;
        }

    public String getSeasonStart ()
        {
            return seasonStart;
        }

    public void setSeasonStart (String seasonStart)
        {
            this.seasonStart = seasonStart;
        }

    public String getSeasonEnd ()
        {
            return seasonEnd;
        }

    public void setSeasonEnd (String seasonEnd)
        {
            this.seasonEnd = seasonEnd;
        }

    public int getHotelId ()
        {
            return hotelId;
        }

    public void setHotelId (int hotelId)
        {
            this.hotelId = hotelId;
        }

    public static ArrayList<HotelSeason> getList ()
        {
            ArrayList<HotelSeason> typeList = new ArrayList<> ();
            String query = "SELECT * FROM season";
            HotelSeason obj;
            try
                {
                    Statement st = DBConnect.getInstance ().createStatement ();
                    ResultSet rs = st.executeQuery (query);
                    while (rs.next ())
                        {
                            obj = new HotelSeason ();
                            obj.setSeasonId (rs.getInt ("id"));
                            obj.setSeasonName (rs.getString ("season_name"));
                            obj.setHotelId (rs.getInt ("hotel_id"));
                            obj.setSeasonStart (rs.getString ("season_start"));
                            obj.setSeasonEnd (rs.getString ("season_end"));


                            typeList.add (obj);

                        }

                } catch (SQLException e)
                {
                    e.printStackTrace ();
                }
            return typeList;

        }

    public Hotel getFetch (int id)
        {
            Hotel obj = null;
            String query = "SELECT * FROM hotel WHERE id = ?";
            try
                {
                    PreparedStatement pr = DBConnect.getInstance ().prepareStatement (query);
                    pr.setInt (1 , id);
                    ResultSet rs = pr.executeQuery ();
                    if ( rs.next () )
                        {
                            obj = new Hotel ();
                            obj.setId (rs.getInt ("id"));
                            obj.setName (rs.getString ("name"));
                            obj.setStar (rs.getString ("star"));
                            obj.setProperty (rs.getString ("property"));
                            obj.setAddress (rs.getString ("address"));
                            obj.setPhone (rs.getString ("phone"));
                            obj.setEmail (rs.getString ("email"));

                        }

                } catch (SQLException e)
                {
                    e.printStackTrace ();
                }

            return obj;
        }

    public static boolean add (String season_name , String season_start , String season_end , int hotel_id )
        {
            String query = "INSERT INTO season (season_name,season_start,season_end,hotel_id) VALUES (?,?,?,?)";

                        try
                {
                    PreparedStatement pr = DBConnect.getInstance ().prepareStatement (query);
                    pr.setString (1,season_name);
                    pr.setString (2,season_start);
                    pr.setString (3,season_end);
                    pr.setInt (4,hotel_id);

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
            String query = "DELETE FROM season WHERE hotel_id = ?";

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
