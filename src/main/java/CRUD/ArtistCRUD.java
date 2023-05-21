package CRUD;

import Config.DatabaseConfig;
import Entities.Artist;
import Services.ReadWriteCSV;

import java.sql.*;
import java.util.List;

public class ArtistCRUD {
    private static ArtistCRUD instance;
    private ArtistCRUD(){}
    public static ArtistCRUD getInstance()
    {
        if(instance == null)
            instance = new ArtistCRUD();
        return instance;
    }

    public void createTable()
    {
        String createTableSql = "CREATE TABLE IF NOT EXISTS Artist" +
                "(id int PRIMARY KEY, " +
                "email varchar(40), " +
                "username varchar(40), " +
                "password varchar(40), " +
                "followers int)";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try(Statement stm = connection.createStatement())
        {
            stm.execute(createTableSql);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void add()
    {
        String selectSql = "SELECT * FROM Artist;";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try (Statement stm = connection.createStatement())
        {
            ResultSet resultSet = stm.executeQuery(selectSql);

            if(!resultSet.next())
            {
                List<Artist> artists = ReadWriteCSV.readArtist();

                for(Artist a : artists)
                {
                    addArtist(a.getId(), a.getEmail(), a.getUsername(), a.getPassword(), a.getFollowers());
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addArtist(int id, String email, String username, String password, int followers)
    {
        String s = id + ",\"" + email + "\", \"" + username +
                "\", \"" + password + "\", \"" + followers + "\"";

        String insertArtistSql = "INSERT INTO Artist(id, email, username, password, followers) VALUES (" + s + ");";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try(Statement stm = connection.createStatement())
        {
            stm.executeUpdate(insertArtistSql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void showArtists()
    {
        String selectSql = "SELECT * FROM Artist";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try(Statement stm = connection.createStatement())
        {
            boolean ok = true;
            ResultSet resultSet = stm.executeQuery(selectSql);
            while(resultSet.next())
            {
                ok = false;
                System.out.println("Artist ID: " + resultSet.getInt(1));
                System.out.println("Artist email: " + resultSet.getString(2));
                System.out.println("Artist username: " + resultSet.getString(3));
                System.out.println("Artist password: " + resultSet.getString(4));
                System.out.println("Artist followers: " + resultSet.getInt(5));
                System.out.println();
            }
            if(ok)
            {
                System.out.println("No Artists to show!");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Artist getArtistById(int id)
    {
        String selectSql = "SELECT * FROM Artist WHERE id=?";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(selectSql))
        {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToArtist(resultSet);
        }
        catch (SQLException e)
        {
            return null;
        }
    }

    public void updateArtistFollowers(int followers, int id)
    {
        String updateArtistFollowersSql = "UPDATE Artist SET followers=? WHERE id=?";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(updateArtistFollowersSql))
        {
            preparedStatement.setInt(1, followers);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteArtistById(int id)
    {
        String deleteArtistSql = "DELETE FROM Artist WHERE id=?";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(deleteArtistSql))
        {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private Artist mapToArtist(ResultSet resultSet) throws SQLException
    {
        if(resultSet.next())
            return new Artist(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5));

        return null;
    }
}

















