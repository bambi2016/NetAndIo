package chat4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 实现一次请求一次响应
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.start();
    }

    private void start() throws IOException {
        Socket socket = new Socket("localhost", 9000);
        PrintWriter dos = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader dis = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        new Thread(new Runnable() {
            @Override
            public void run() {
                dos.println("你好啊服务端");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(dis.readLine());
                } catch (IOException e) {
                    System.out.println("服务端down了");
                    e.printStackTrace();
                }
            }
        }).start();

        dos.close();
        dis.close();
        socket.close();
    }
}
