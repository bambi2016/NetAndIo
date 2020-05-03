package chat2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class MySever {
    public static void main(String[] args) throws IOException {
        HashMap<String, Socket> map = new HashMap<>();
        MySever str = new MySever();
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("服务端已开启。。端口：8888");
        while (true) {
            //循环等待多个用户
            Socket socket = serverSocket.accept();
            String addr=socket.getLocalAddress()+":"+ socket.getPort();
            map.put(addr,socket);
            System.out.println("新连接用户："+socket.getLocalAddress()+":"+socket.getPort());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        DataInputStream dis = new DataInputStream(socket.getInputStream());

                        while (true) {
                            DataOutputStream dos=null;
                            //循环等待一个用户发送多条数据
                            try {
                                Iterator<String> iterator = map.keySet().iterator();
                                while (iterator.hasNext()){
                                    String next = iterator.next();
                                    if (next.equals(socket.getLocalAddress()+":"+ socket.getPort())){
                                        continue;
                                    }
                                    dos = new DataOutputStream(map.get(next).getOutputStream());
                                    dos.writeUTF(dis.readUTF());
                                    dos.flush();
                                }

                            } catch (IOException e) {
                                dis.close();
                                dos.close();
                                socket.close();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
    public String getresponse() {
        String[] ss = new String[]{"你好", "你好美", "很高兴认识你", "嗯嗯", "我知道", "真的吗？"};
        int i = new Random().nextInt(6);
        return ss[i];
    }
}
