package chat2;

import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

/**
 * 实现客户端给服务器发送消息，服务端接收消息后返回消息
 */
public class MyClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",8888);
        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(outputStream);
        InputStream inputStream = socket.getInputStream();
        DataInputStream dis = new DataInputStream(inputStream);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String s = reader.readLine();
            if ("exit".equals(s)){
                break;
            }
            dos.writeUTF(s);
            dos.flush();
            System.out.println(dis.readUTF());

        }
        dos.close();
        dis.close();
        socket.close();

    }
}
