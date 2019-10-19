package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EchoServer {

	private ServerSocket server_Soc;
	private BufferedReader br;
	private PrintWriter pw;
	private Socket soc;

	public EchoServer() {
		init();
	}

	public void init() {
		try {
			server_Soc = new ServerSocket(8080);
			System.out.println(getTime() + "Server is ready...");
			System.out.println(getTime() + "wait for client...");

			soc = server_Soc.accept();
			System.out.println("Client has accepted!!");

			br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			pw = new PrintWriter(soc.getOutputStream());

			String readData = "";

			while (!(readData = br.readLine()).equals(null)) {
				System.out.println(getTime() + "from Client > " + readData);
				pw.println(readData);
				pw.flush();
			}

			soc.close();

		} catch (SocketException | NullPointerException se) {
			System.out.println("클라이언트가 연결을 종료하여 프로그램을 종료합니다...");
			System.exit(0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static String getTime() {
		SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
		return f.format(new Date());
	}

	public static void main(String[] args) {

		new EchoServer();

	}

}