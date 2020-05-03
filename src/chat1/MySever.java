package chat1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MySever {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务端启动。。。");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("接收到连接："+socket.getInetAddress()+":"+socket.getPort());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());


//        String s = dis.readUTF();//阻塞
//        System.out.println(s);

            dos.writeUTF(  "too");
            dos.flush();
            dos.writeUTF("tootoo");
            dos.flush();

            dos.close();
            socket.close();
        }


    }
}
