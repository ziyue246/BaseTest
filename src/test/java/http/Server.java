package http;

import javax.swing.*;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    public Server() throws Exception{
        ServerSocket socket=new ServerSocket(80); //ÔÚ1000¶Ë¿Ú¼àÌý
        while(true){
            Socket s=socket.accept();
            while(!s.isConnected()){}
            new Processor(s).start();
        }
    }
    public static void main(String args[]) throws Exception{
        new Server();
    }
}

class Processor extends Thread{
    Socket s=null;
    public Processor(Socket s) throws Exception{
        this.s=s;
    }
    public void run(){
        try{
            ObjectInputStream obj=new ObjectInputStream(s.getInputStream());
            JOptionPane.showMessageDialog(null,(String)obj.readObject());
            obj.close();
        }catch(Exception e){}
    }
}