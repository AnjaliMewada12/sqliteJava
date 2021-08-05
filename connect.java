import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
public class connect {
	public static void createdatabase(String db)
	{
		Connection con=null;
		try{
		String url="jdbc:sqlite:C:/Users/dell/Desktop/mulesoft/"+db;
		con=DriverManager.getConnection(url);
	    }
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	public static Connection getCon(){
		Connection con=null;
		try{
		String url="jdbc:sqlite:C:/Users/dell/Desktop/mulesoft/favourite.db";
		con=DriverManager.getConnection(url);
	    }
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return con;
	}
    public static void createtable() {
             String sql = "CREATE TABLE IF NOT EXISTS Movies(\n"
                +"mvname text Primary key,\n"
                + "	actor text,\n"
				+ " actress text,\n"
                + "	year Integer NOT NULL,\n"
                + "	director text NOT NULL\n"
                + ");";
			 try{
				Connection con=getCon();
				Statement stmt = con.createStatement();
				stmt.execute(sql);
			 }
			 catch(SQLException e)
		     {
			System.out.println(e.getMessage());
		     }
    }
	public static void insertdata(String mvname,String actor,String actress,Integer year,String director){
	    String sql = "INSERT INTO Movies(mvname,actor,actress,year,director) VALUES(?,?,?,?,?)";
		try{
			Connection con =getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, mvname);
			pstmt.setString(2, actor);
            pstmt.setString(3, actress);
			pstmt.setInt(4, year);
            pstmt.setString(5,director);
            pstmt.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	public static void selectdata(){
		String sql = "SELECT * from movies";
		try{
			Connection con = getCon();
			 Statement stmt  = con.createStatement();
             ResultSet rs    = stmt.executeQuery(sql);
            
            while (rs.next()) {
                System.out.println(
                                   rs.getString("mvname") + "\t" +
                                   rs.getString("actor") + "\t" +
								   rs.getString("actress") +  "\t" + 
                                   rs.getInt("year") + "\t" +
                                   rs.getString("director"));
            }
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	public static void selectSpecific(String actor)
	{
		String sql = "SELECT *"
                     + "FROM movies WHERE actor='"+actor+"'";
	    try{
			Connection con = getCon();
			 Statement stmt  = con.createStatement();
			ResultSet rs    = stmt.executeQuery(sql);
            
            while (rs.next()) {
                System.out.println(
                                   rs.getString("mvname") + "\t" +
                                   rs.getString("actor") + "\t" +
								   rs.getString("actress") +  "\t" + 
                                   rs.getInt("year") + "\t" +
                                   rs.getString("director"));
            }
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
    public static void main(String[] args) {
        connect c= new connect();
		c.createdatabase("favourite.db");
		c.createtable();
		c.insertdata("maine pyar kyu kiya","salman","sushmita",2005,"david dhawan");
		c.insertdata("partner","salman","katrina",2007,"david dhawan");
		c.insertdata("krish3","hritik","priyanka",2013,"rakesh roshan");
		c.selectdata();
		c.selectSpecific("salman");
    }
}