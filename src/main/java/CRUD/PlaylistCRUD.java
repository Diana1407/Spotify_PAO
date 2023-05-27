package CRUD;

import Config.DatabaseConfig;
import Entities.Playlist;
import Entities.Song;
import Services.ReadWriteCSV;

import java.sql.*;
import java.util.List;
import java.util.Objects;

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
                "privacy varchar(40)," +
                "ownerID int," +
                "songs varchar(7000))";

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
                    addPlaylist(a.getId(), a.getTitle(), a.getDuration(), a.getPrivacy() ,a.getOwnerId(), a.getSongs());
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addPlaylist(int id, String title, int duration, String privacy, int ownerId, String songs)
    {
        String s = id + ",\"" + title + "\", \"" + duration +
                "\", \"" + privacy + "\", \"" + ownerId +  "\", \"" + songs + "\"";

        String insertPlaylistSql = "INSERT INTO Playlist(id, title, duration, privacy, ownerId, songs) VALUES (" + s + ");";

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
                System.out.println("Playlist privacy: " + resultSet.getString(4));
                System.out.println("Playlist OwnerID: " + resultSet.getInt(5));
                System.out.println("Tracklist: ");

                Playlist playlist = PlaylistCRUD.getInstance().getPlaylistById(resultSet.getInt(1));
                //System.out.println(playlist.toString());

                String songs = playlist.getSongs();
                System.out.println(Objects.requireNonNullElse(songs, "Playlist gol!"));

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

    public void updatePlaylistPrivacy(String privacy, int id)
    {
        String updatePlaylistPrivacySql = "UPDATE Playlist SET privacy=? WHERE id=?";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(updatePlaylistPrivacySql))
        {
            preparedStatement.setString(1, privacy);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
//    public void updatePlaylistSongs(String songs, int id)
//    {
//        String updatePlaylistSongsSql = "UPDATE Playlist SET songs=? WHERE id=?";
//
//        Connection connection = DatabaseConfig.getDatabaseConnection();
//
//        try(PreparedStatement preparedStatement = connection.prepareStatement(updatePlaylistSongsSql))
//        {
//            preparedStatement.setString(1, songs);
//            preparedStatement.setInt(2, id);
//
//            preparedStatement.executeUpdate();
//        }
//        catch(SQLException e)
//        {
//            e.printStackTrace();
//        }
//    }

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

    public void updatePlaylistSongs(String songs, int id)
    {
        String updatePlaylistSongsSql = "UPDATE Playlist SET songs=? WHERE id=?";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(updatePlaylistSongsSql))
        {
            preparedStatement.setString(1, songs);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    private Playlist mapToPlaylist(ResultSet resultSet) throws SQLException
    {
        if(resultSet.next())
            return new Playlist(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6));

        return null;
    }
}
