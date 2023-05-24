package CRUD;

import Config.DatabaseConfig;
import Entities.Artist;
import Entities.PremiumUser;
import Services.ReadWriteCSV;

import java.sql.*;
import java.util.List;

public class PremiumUserCRUD {
    private static PremiumUserCRUD instance;
    private PremiumUserCRUD(){}
    public static PremiumUserCRUD getInstance()
    {
        if(instance == null)
            instance = new PremiumUserCRUD();
        return instance;
    }

    public void createTable()
    {
        String createTableSql = "CREATE TABLE IF NOT EXISTS PremiumUser" +
                "(id int PRIMARY KEY, " +
                "email varchar(40), " +
                "username varchar(40), " +
                "password varchar(40), " +
                "plan varchar(40), " +
                "price double)";

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
        String selectSql = "SELECT * FROM PremiumUser;";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try (Statement stm = connection.createStatement())
        {
            ResultSet resultSet = stm.executeQuery(selectSql);

            if(!resultSet.next())
            {
                List<PremiumUser> premiumUsers = ReadWriteCSV.readPremiumUser();

                for(PremiumUser p : premiumUsers)
                {
                    addPremiumUser(p.getId(), p.getEmail(), p.getUsername(), p.getPassword(), p.getPlan(), p.getPrice());
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addPremiumUser(int id, String email, String username, String password, String plan, double price)
    {
        String s = id + ",\"" + email + "\", \"" + username +
                "\", \"" + password + "\", \"" + plan + "\", \"" + price + "\"";

        String insertPremiumUserSql = "INSERT INTO PremiumUser(id, email, username, password, plan, price) VALUES (" + s + ");";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try(Statement stm = connection.createStatement())
        {
            stm.executeUpdate(insertPremiumUserSql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void showPremiumUsers()
    {
        String selectSql = "SELECT * FROM PremiumUser";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try(Statement stm = connection.createStatement())
        {
            boolean ok = true;
            ResultSet resultSet = stm.executeQuery(selectSql);
            while(resultSet.next())
            {
                ok = false;
                System.out.println("PremiumUser ID: " + resultSet.getInt(1));
                System.out.println("PremiumUser email: " + resultSet.getString(2));
                System.out.println("PremiumUser username: " + resultSet.getString(3));
                System.out.println("PremiumUser password: " + resultSet.getString(4));
                System.out.println("PremiumUser plan: " + resultSet.getString(5));
                System.out.println("PremiumUser price: " + resultSet.getDouble(6));
                System.out.println();
            }
            if(ok)
            {
                System.out.println("No PremiumUsers to show!");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public PremiumUser getPremiumUserById(int id)
    {
        String selectSql = "SELECT * FROM PremiumUser WHERE id=?";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(selectSql))
        {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToPremiumUser(resultSet);
        }
        catch (SQLException e)
        {
            return null;
        }
    }

    public void updatePremiumUserPlan(String plan, double price, int id)
    {
        String updatePremiumUserPlanSql = "UPDATE PremiumUser SET plan=?, price=? WHERE id=?";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(updatePremiumUserPlanSql))
        {
            preparedStatement.setString(1, plan);
            preparedStatement.setDouble(2, price);
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deletePremiumUserById(int id)
    {
        String deletePremiumUserSql = "DELETE FROM PremiumUser WHERE id=?";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(deletePremiumUserSql))
        {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private PremiumUser mapToPremiumUser(ResultSet resultSet) throws SQLException
    {
        if(resultSet.next())
            return new PremiumUser(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDouble(6));

        return null;
    }
}

















