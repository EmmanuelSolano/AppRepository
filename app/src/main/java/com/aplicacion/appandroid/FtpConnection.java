package com.aplicacion.appandroid;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FtpConnection {

    final FTPClient client = new FTPClient();
    final String sFTP = "25.52.155.23";//direccion del servidor
    final String sUser = "cliente";//usuario
    final String sPassword = "abc12345";//contraseña

    public FtpConnection() {
        try {
            client.connect(sFTP, 21);
            boolean login = client.login(sUser, sPassword);
            System.out.println(login);
        } catch (IOException ioe) {
            System.out.println("no funciono mier");
        }

    }

    public void disconnect(){
        if(this.client.isConnected()) {
            try {
                client.logout();
                client.disconnect();

            } catch (IOException ioe) {
                System.out.println("no funciono mier");
            }
        }
    }

    public void uploadFile(String localFileFullName, String fileName, String hostDir)
            throws Exception {
        InputStream input = new FileInputStream(new File(localFileFullName));
        this.client.storeFile(hostDir + fileName, input);

    }


}
