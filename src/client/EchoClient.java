package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class EchoClient {

	private Socket soc;
	private BufferedReader br;
	private PrintWriter pw;
	private Scanner scan;

	public EchoClient() {
		init();
	}

	public void init() {

		try {
			soc = new Socket("localhost", 8080);
			System.out.println(getTime() + "Accept to Server Success!!");

			br = new BufferedReader(new InputStreamReader(soc.getInputStream()));

			pw = new PrintWriter(soc.getOutputStream());

			scan = new Scanner(System.in);

			System.out.println("now, you can chat!!");
			String inputData = "";

			while (!inputData.equals("exit")) {
				System.out.printf(getTime() + "to Server > ");

				inputData = scan.nextLine();
				pw.println(inputData);
				pw.flush();
				System.out.println(getTime() + "from Server > " + br.readLine());
			}

			soc.close();

		} catch (ConnectException ce) {
			System.out.println("서버가 동작하고 있지않아 프로그램을 종료합니다...");
			System.exit(0);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getTime() {
		SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
		return f.format(new Date());
	}

	public static void main(String[] args) {

		new EchoClient();
	}

}
