package chat5;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receive implements Runnable {
	private BufferedReader dis ;

	private Socket client;
	private boolean isRunning;
	public Receive(Socket client) {
		this.client = client;
		this.isRunning = true;
		try {
			dis =new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch (IOException e) {
			System.out.println("====2=====");
			release();
		}
	}
	//接收消息
	private String receive() {
		String msg ="";
		try {
			msg =dis.readLine();
		} catch (IOException e) {
			System.out.println("====4====");
			release();
		}
		return msg;
	}
	
	@Override
	public void run() {
		while(isRunning) {
			String msg =receive();
			if(!msg.equals("")) {
				System.out.println(msg);
			}
		}
	}
	//释放资源
	private void release() {
		this.isRunning = false;
		CloseUtils.close(dis,client);
	}

}
