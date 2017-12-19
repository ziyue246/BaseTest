package monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class MonitorPort extends ServerSocket {

    public MonitorPort(int serverPort) throws IOException {
        // ��ָ���Ķ˿ڹ���һ��ServerSocket
        super(serverPort);
        try {
            while (true) {
                // ����һ�˿ڣ��ȴ��ͻ�����
                Socket socket = accept();
                // ���Ự�����̴߳���
                new ServerThread(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(); // �رռ����˿�
        }
    }

    //inner-class ServerThread
    class ServerThread extends Thread {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        // Ready to conversation
      public ServerThread(Socket s) throws IOException{
            this.socket = s;
            // ����ûỰ�е����������
            in = new BufferedReader(new InputStreamReader(socket
                    .getInputStream(), "GB2312"));
            out = new PrintWriter(socket.getOutputStream(),true);
            start();
        }

        // Execute conversation
    public void run() {
        try {

            // Communicate with client until "bye "received.
            while (true) {
                // ͨ�����������տͻ�����Ϣ
                String line = in.readLine();
                if (line == null || "".equals(line.trim())) { //�Ƿ���ֹ�Ự
                    break;
                }
                System.out.println("Received  message: " + line);
                // ͨ���������ͻ��˷�����Ϣ
                out.println(line);
                out.flush();
            }

            out.close();
            in.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

// mainmethod
    public static void main(String[] args) throws IOException {
            new MonitorPort(80);
    }
}
