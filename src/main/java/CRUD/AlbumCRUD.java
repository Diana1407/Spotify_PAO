package CRUD;

import Config.DatabaseConfig;
import Entities.Album;
import Services.ReadWriteCSV;

import java.sql.*;
import java.util.List;

public class AlbumCRUD {
    private static AlbumCRUD instance;
    private AlbumCRUD(){}
    public static AlbumCRUD getInstance()
    {
        if(instance == null)
            instance = new AlbumCRUD();
        return instance;
    }

    public void createTable()
    {
        String createTableSql = "CREATE TABLE IF NOT EXISTS Album" +
                "(id int PRIMARY KEY, " +
                "title varchar(40), " +
                "duration int, " +
                "releasedate varchar(40), " +
                "ArtistID int) ";

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
        String selectSql = "SELECT * FROM Album;";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try (Statement stm = connection.createStatement())
        {
            ResultSet resultSet = stm.executeQuery(selectSql);

            if(!resultSet.next())
            {
                List<Album> albums = ReadWriteCSV.readAlbum();

                for(Album a : albums)
                {
                    addAlbum(a.getId(), a.getTitle(), a.getDuration(), a.getReleasedate(), a.getArtistId());
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addAlbum(int id, String title, int duration, String releasedate, int artistID)
    {
        String s = id + ",\"" + title + "\", \"" + duration +
                "\", \"" + releasedate + "\", \"" + artistID + "\"";

        String insertArtistSql = "INSERT INTO Album(id, title, duration, releasedate, artistId) VALUES ("+ s +")";

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

    public void showAlbums()
    {
        String selectSql = "SELECT * FROM Album;";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try(Statement stm = connection.createStatement())
        {
            boolean ok = true;
            ResultSet resultSet = stm.executeQuery(selectSql);
            while(resultSet.next())
            {
                ok = false;
                System.out.println("Album ID: " + resultSet.getInt(1));
                System.out.println("Album title: " + resultSet.getString(2));
                System.out.println("Album duration: " + resultSet.getInt(3));
                System.out.println("Album Release Date: " + resultSet.getString(4));
                System.out.println("Album de modif in nume ArtistID: " + resultSet.getInt(5));
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

    public Album getAlbumById(int id)
    {
        String selectSql = "SELECT * FROM Album WHERE id=?";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(selectSql))
        {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToAlbum(resultSet);
        }
        catch (SQLException e)
        {
            return null;
        }
    }
// daca avem timp update Album Title desi nu prea are sens?

    public void deleteAlbumById(int id)
    {
        String deleteAlbumSql = "DELETE FROM Album WHERE id=?";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(deleteAlbumSql))
        {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private Album mapToAlbum(ResultSet resultSet) throws SQLException
    {
        if(resultSet.next())
            return new Album(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getInt(5));

        return null;
    }
}