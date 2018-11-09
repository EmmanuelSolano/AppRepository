package com.aplicacion.appandroid;


import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {

    public ConnectionClass(){


    }

    public Connection createConnection(String user, String password, String database, String server) throws ClassNotFoundException, SQLException {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build(); //política de conección

        StrictMode.setThreadPolicy(policy);

        Connection connection = null;
        String connectionUrl = null;

        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        connectionUrl = "jdbc:jtds:sqlserver://"+ server+";"+database+";user="+user+
                ";password="+password+";";

        connection= DriverManager.getConnection(connectionUrl);

        return connection;
    }
}
