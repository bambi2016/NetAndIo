package chat3;

import java.io.*;
import java.net.Socket;

/**
 * 实现客户端给服务器发送消息
 * 服务端随时可以向客户端发消息，
 * 所以当客户端建立成功后，服务端就可以给客户端发消息了，而不是http的请求响应模式
 * 所以请求和响应分别要创建两个线程
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
