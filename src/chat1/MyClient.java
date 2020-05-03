package chat1;

import java.io.*;
import java.net.Socket;

/**
 * 实现客户端给服务器发送消息，服务端接收消息后返回消息
 */
public class MyClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",8888);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        while (true) {
//                            String s = reader.readLine();
//                            dos.writeUTF(s);
//                            dos.flush();
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            System.out.println(dis.readUTF());
                        }
                    } catch (IOException e) {
                        try {
                            dos.close();
                            dis.close();
                            socket.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }).start();




    }
}
