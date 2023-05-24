package CRUD;

import Config.DatabaseConfig;
import Entities.Song;
import Services.ReadWriteCSV;

import java.sql.*;
import java.util.List;

public class SongCRUD {
    private static SongCRUD instance;
    private SongCRUD(){}
    public static SongCRUD getInstance()
    {
        if(instance == null)
            instance = new SongCRUD();
        return instance;
    }

    public void createTable()
    {
        String createTableSql = "CREATE TABLE IF NOT EXISTS Song" +
                "(id int PRIMARY KEY, " +
                "name varchar(40), " +
                "artistId int, " +
                "albumId int, " +
                "duration int, " +
                "link varchar(40))";

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
        String selectSql = "SELECT * FROM Song;";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try (Statement stm = connection.createStatement())
        {
            ResultSet resultSet = stm.executeQuery(selectSql);

            if(!resultSet.next())
            {
                List<Song> songs = ReadWriteCSV.readSong();

                for(Song a : songs)
                {
                    addSong(a.getId(), a.getName(), a.getArtistId(), a.getAlbumId(), a.getDuration(), a.getLink());
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addSong(int id, String name, int artistId, int albumId, int duration, String link)
    {
        String s = id + ",\"" + name + "\", \"" + artistId +
                "\", \"" + albumId + "\", \"" + duration + "\", \"" + link + "\"";

        String insertSongSql = "INSERT INTO Song(id, name, artistId, albumId, duration, link) VALUES ("+ s +")";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try(Statement stm = connection.createStatement())
        {
            stm.executeUpdate(insertSongSql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void showSongs()
    {
        String selectSql = "SELECT * FROM Song;";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try(Statement stm = connection.createStatement())
        {
            boolean ok = true;
            ResultSet resultSet = stm.executeQuery(selectSql);
            while(resultSet.next())
            {
                ok = false;
                System.out.println("Song ID: " + resultSet.getInt(1));
                System.out.println("Song name: " + resultSet.getString(2));
                System.out.println("Song de modif in nume ArtistID: " + resultSet.getInt(3));
                System.out.println("Song de modif in nume AlbumID: " + resultSet.getInt(4));
                System.out.println("Song duration: " + resultSet.getInt(5));
                System.out.println("Song link: " + resultSet.getString(6));
                System.out.println();
            }
            if(ok)
            {
                System.out.println("No Songs to show!");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Song getSongById(int id)
    {
        String selectSql = "SELECT * FROM Song WHERE id=?";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(selectSql))
        {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToSong(resultSet);
        }
        catch (SQLException e)
        {
            return null;
        }
    }
    public void deleteSongById(int id)
    {
        String deleteSongSql = "DELETE FROM Song WHERE id=?";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(deleteSongSql))
        {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private Song mapToSong(ResultSet resultSet) throws SQLException
    {
        if(resultSet.next())
            return new Song(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getString(6));

        return null;
    }
}