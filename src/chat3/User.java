package chat3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
//一个用户包含的成员变量和方法
//客户端与服务端建立连接时初始化User
public class User {
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private String name;//第一次连接时需要设置，相当于注册的意思
    private String addrPort;
    private boolean isRunning;

    public User(Socket socket) throws IOException {
        this.socket = socket;
        dis=new DataInputStream(socket.getInputStream());
        dos=new DataOutputStream(socket.getOutputStream());
        addrPort=socket.getInetAddress()+":"+socket.getPort();
        isRunning=true;
    }

    //发送消息
    public void send(String message){
        try {
            dos.writeUTF(message);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //接收消息
    public String receive(){
        String s = null;
        try {
            s = dis.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    //发送消息给自己 如初始化时需要欢迎自己
    //发送消息给别人，如群发消息

    //释放资源
}
