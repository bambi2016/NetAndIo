package chat4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现一次请求一次响应
 */
public class Sever {
    public static void main(String[] args) throws IOException {
        Sever sever = new Sever();
        sever.start();
    }

    private void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        System.out.println("服务端启动,等待客户端连接。。");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("服务端接收到一个客户端的连接");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println(in.readLine());
            out.println("你好啊客户端");
            out.println("你好啊客户端");//这个不能执行
            out.close();
            in.close();
            socket.close();
        }
    }

}
