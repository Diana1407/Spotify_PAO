import Entities.*;
import Services.*;

import Config.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Menu m = Menu.getInstance();
        m.runMenu();
    }
}