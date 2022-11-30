package com.example.signature;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SendImageToPC extends AsyncTask<Void,Void,Void> {
    static Socket s; //Socket Variable
    @Override
    protected Void doInBackground(Void...params){
        try {
            s = new Socket("192.168.1.240",8000); //Connects to IP address - enter your IP here
            File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS); //Gets information about a said directory on your device - currently downloads
            File photoPath = new File(directory, "imgc.png"); //Define your image name I used png but other formats should also work - make sure to specify file extension on server
            InputStream input = new FileInputStream(photoPath.getAbsolutePath()); //Gets the true path of your image
            try {
                try {
                    //Reads bytes (all together)
                    int bytesRead;
                    while ((bytesRead = input.read()) != -1) {
                        s.getOutputStream().write(bytesRead); //Writes bytes to output stream
                    }
                } finally {
                    //Flushes and closes socket
                    s.getOutputStream().flush();
                    s.close();
                }
            } finally {
                input.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}