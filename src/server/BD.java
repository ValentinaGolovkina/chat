package server;

import java.sql.*;

public class BD {

    private Connection conn;
    public boolean isOK;

    public BD() {
        isOK=false;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:CHAT.db");
            createBD();
            //fillBD();
            isOK=true;


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private void createBD() throws SQLException{
        PreparedStatement ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Users\n" +
                "(\n" +
                "  Login TEXT PRIMARY KEY NOT NULL,\n" +
                "  Password TEXT NOT NULL,\n" +
                "  NickName TEXT NOT NULL\n" +
                ");");
        ps.execute();
    }

    private void fillBD() throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Users (Login, Password, NickName) VALUES\n"+
                //"(\"qwe\", \"qwe\", \"qwe\"),\n"+
                //"(\"asd\", \"asd\", \"asd\"),\n"+
                "(\"zxc\", \"zxc\", \"zxc\")\n"+
                ";");
        ps.execute();
    }

    public String getNickByLoginAndPassword(String login, String password){
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT NickName FROM Users WHERE  Login=? AND Password=?;");
            ps.setString(1,login);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            return (rs.next())? rs.getString("NickName") : null;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean isExistUserByLoginORNickname(String login, String nickname){
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT NickName FROM Users WHERE  Login=? OR NickName=?;");
            ps.setString(1,login);
            ps.setString(2,nickname);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveUser(String login, String password, String nickname){
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Users (Login, Password, NickName) VALUES(?, ?, ?);");
            ps.setString(1,login);
            ps.setString(2,password);
            ps.setString(3,nickname);
            return ps.execute();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public void closeConn(){
        try {
            if(!conn.isClosed()) conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
