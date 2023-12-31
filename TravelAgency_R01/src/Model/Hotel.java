package Model;

import Helper.DBConnect;
import Helper.Helper;

import java.sql.*;
import java.util.ArrayList;

public class Hotel {

    private int id;
    private String name;
    private String star;
    private String property;
    private String address;
    private String phone;
    private String email;
    private Connection connection;

    public Hotel ()
        {

        }

    public Hotel (int id , String name , String star , String property , String address , String phone , String email)
        {
            this.id = id;
            this.name = name;
            this.star = star;
            this.property = property;
            this.address = address;
            this.phone = phone;
            this.email = email;
        }


    public int getId ()
        {
            return id;
        }

    public void setId (int id)
        {
            this.id = id;
        }

    public String getName ()
        {
            return name;
        }

    public void setName (String name)
        {
            this.name = name;
        }

    public String getStar ()
        {
            return star;
        }

    public void setStar (String star)
        {
            this.star = star;
        }

    public String getProperty ()
        {
            return property;
        }

    public void setProperty (String property)
        {
            this.property = property;
        }

    public String getAddress ()
        {
            return address;
        }

    public void setAddress (String address)
        {
            this.address = address;
        }

    public String getPhone ()
        {
            return phone;
        }

    public void setPhone (String phone)
        {
            this.phone = phone;
        }

    public String getEmail ()
        {
            return email;
        }

    public void setEmail (String email)
        {
            this.email = email;
        }

    public static ArrayList<Hotel> getList ()
        {
            ArrayList<Hotel> hotelList = new ArrayList<> ();
            String query = "SELECT * FROM hotel";
            Hotel obj;
            try
                {
                    Statement st = DBConnect.getInstance ().createStatement ();
                    ResultSet rs = st.executeQuery (query);
                    while (rs.next ())
                        {
                            obj = new Hotel ();
                            obj.setId (rs.getInt ("id"));
                            obj.setName (rs.getString ("name"));
                            obj.setStar (rs.getString ("star"));
                            obj.setProperty (rs.getString ("property"));
                            obj.setAddress (rs.getString ("address"));
                            obj.setPhone (rs.getString ("phone"));
                            obj.setEmail (rs.getString ("email"));
                            hotelList.add (obj);

                        }

                } catch (SQLException e)
                {
                    e.printStackTrace ();
                }
            return hotelList;

        }

    public static Hotel getFetch (String email)
        {
            Hotel obj = null;
            String query = "SELECT * FROM hotel WHERE email=?";

            PreparedStatement pr = null;
            try
                {
                    pr = DBConnect.getInstance ().prepareStatement (query);
                    pr.setString (1 , email);
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

    public static boolean add (String name , String star , String property , String address , String phone , String email)
        {
            String query = "INSERT INTO hotel (name,star,property,address,phone,email) VALUES (?,?,?,?,?,?)";
            Hotel findhotel = Hotel.getFetch (email);
            if ( findhotel != null )
                {
                    Helper.showMsg ("This hotel already exists in the system!");
                    return false;
                }
            try
                {
                    PreparedStatement pr = DBConnect.getInstance ().prepareStatement (query);
                    pr.setString (1,name);
                    pr.setString (2,star);
                    pr.setString (3,property);
                    pr.setString (4,address);
                    pr.setString (5,phone);
                    pr.setString (6,email);

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
            String query = "DELETE FROM hotel WHERE id = ?";

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

    public static boolean update (Hotel hotel) {
        String query = "UPDATE hotel SET name = ?, star = ?, property = ?, address = ?, phone = ?, " +
                "email = ? WHERE id = ?";

        try {
            PreparedStatement pr = DBConnect.getInstance().prepareStatement(query);
            pr.setString(1, hotel.getName());
            pr.setString(2, hotel.getStar());
            pr.setString(3, hotel.getProperty());
            pr.setString(4, hotel.getAddress());
            pr.setString(5, hotel.getPhone());
            pr.setString(6, hotel.getEmail());
            pr.setInt(7, hotel.getId());

            return pr.executeUpdate() != -1;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public static String searchQuery (String name, String address, String star) {
        String query = "SELECT * FROM hotel WHERE name LIKE '%{{name}}%' AND " +
                "address LIKE '%{{address}}%'";

        query = query.replace("{{name}}",name);
        query = query.replace("{{address}}", address);

        if (!star.isEmpty()) {
            query += " AND star = '{{star}}'";
            query = query.replace("{{star}}",star );
        }
        System.out.println(query);

        return query;

    }

    public static ArrayList<Hotel> searchHotelList (String query) {
        ArrayList<Hotel> hotelArrayList = new ArrayList<>();

        try {
            Statement statement = DBConnect.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                hotelArrayList.add(match(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return hotelArrayList;
    }

    public static Hotel match(ResultSet resultSet) throws SQLException {
        Hotel object = new Hotel();

        object.setId(resultSet.getInt("id"));
        object.setName(resultSet.getString("name"));
        object.setStar(resultSet.getString("star"));
        object.setProperty(resultSet.getString("property"));
        object.setAddress(resultSet.getString("address"));
        object.setPhone(resultSet.getString("phone"));
        object.setEmail(resultSet.getString("email"));

        return object;
    }

}
