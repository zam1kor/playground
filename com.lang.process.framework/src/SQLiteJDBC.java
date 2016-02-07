import java.sql.*;

public class SQLiteJDBC
{
  public static void main( String args[] )
  {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      String sql = "CREATE TABLE PMGR " +
                   "(ID INT PRIMARY KEY     NOT NULL," +
                   " NAME           TEXT    NOT NULL " + 
                   " )"; 
      stmt.executeUpdate(sql);
      stmt.close();
      
      stmt = c.createStatement();
        sql = "CREATE TABLE POOL " +
                   "(POOL_ID INT PRIMARY KEY     NOT NULL," +
                   " NAME           TEXT    NOT NULL, " + 
                   " PMGR_ID           INT    NOT NULL, " + 
                   " FOREIGN KEY(PMGR_ID) REFERENCES PMGR(ID))"; 
      stmt.executeUpdate(sql);
      stmt.close();
      
      
      stmt = c.createStatement();
        sql = "CREATE TABLE WORD " +
                   "(ID INT        NOT NULL," +
                   " NAME           TEXT    NOT NULL, " + 
                   " POOL_ID           TEXT    NOT NULL, " + 
                   " FOREIGN KEY(POOL_ID) REFERENCES POOL(ID))"; 
      stmt.executeUpdate(sql);
      stmt.close();
      
      
      
      
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Table created successfully");
  }
}