package CRUD;

import Config.DatabaseConfig;
import Entities.Playlist;
import Services.ReadWriteCSV;

import java.sql.*;
import java.util.List;

public class PlaylistCRUD {
    private static PlaylistCRUD instance;
    private PlaylistCRUD(){}
    public static PlaylistCRUD getInstance()
    {
        if(instance == null)
            instance = new PlaylistCRUD();
        return instance;
    }

    public void createTable()
    {
        String createTableSql = "CREATE TABLE IF NOT EXISTS Playlist" +
                "(id int PRIMARY KEY," +
                "title varchar(40)," +
                "duration int," +
                "privacy BIT," +
                "OwnerID int" +
                ")";

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
        String selectSql = "SELECT * FROM Playlist;";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try (Statement stm = connection.createStatement())
        {
            ResultSet resultSet = stm.executeQuery(selectSql);

            if(!resultSet.next())
            {
                List<Playlist> playlists = ReadWriteCSV.readPlaylist();

                for(Playlist a : playlists)
                {
                    addPlaylist(a.getId(), a.getTitle(), a.getDuration(), a.getPrivacy() ,a.getOwnerId());
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addPlaylist(int id, String title, int duration, Boolean privacy, int OwnerID)
    {
        String s = id + ",\"" + title + "\", \"" + duration +
                "\", \"" + privacy + "\", \"" + OwnerID + "\"";

        String insertPlaylistSql = "INSERT INTO Playlist(id, title, duration, privacy, OwnerID) VALUES (" + s + ");";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try(Statement stm = connection.createStatement())
        {
            stm.executeUpdate(insertPlaylistSql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void showPlaylists()
    {
        String selectSql = "SELECT * FROM Playlist;";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try(Statement stm = connection.createStatement())
        {
            boolean ok = true;
            ResultSet resultSet = stm.executeQuery(selectSql);
            while(resultSet.next())
            {
                ok = false;
                System.out.println("Playlist ID: " + resultSet.getInt(1));
                System.out.println("Playlist title: " + resultSet.getString(2));
                System.out.println("Playlist duration: " + resultSet.getInt(3));
                System.out.println("Playlist privacy: " + resultSet.getBoolean(4));
                System.out.println("Playlist de modif in nume OwnerID: " + resultSet.getInt(5));
                System.out.println();
            }
            if(ok)
            {
                System.out.println("No Playlist to show!");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Playlist getPlaylistById(int id)
    {
        String selectSql = "SELECT * FROM Playlist WHERE id=?";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(selectSql))
        {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToPlaylist(resultSet);
        }
        catch (SQLException e)
        {
            return null;
        }
    }

    public void updatePlaylistPrivacy(int privacy, int id)
    {
        String updatePlaylistPrivacySql = "UPDATE Playlist SET privacy=? WHERE id=?";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(updatePlaylistPrivacySql))
        {
            preparedStatement.setInt(1, privacy);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deletePlaylistById(int id)
    {
        String deletePlaylistSql = "DELETE FROM Playlist WHERE id=?";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(deletePlaylistSql))
        {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private Playlist mapToPlaylist(ResultSet resultSet) throws SQLException
    {
        if(resultSet.next())
            return new Playlist(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getBoolean(4), resultSet.getInt(5), null);

        return null;
    }
}
