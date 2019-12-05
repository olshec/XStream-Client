package pr4;

import java.io.*;
import java.net.*;

import com.thoughtworks.xstream.XStream;


public class Client {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		// Info inf = new Info(true,"get tasks");
		menu();

	}

	public static Info getStringFromXML(String str) {
		XStream xstream = new XStream();
		// Allow types for Info
		xstream.allowTypes(new String[] {"pr4.Info"});
		return (Info)xstream.fromXML(str);
	}
	
	public static String serializeInfoToXML(Info inf) {
		XStream xstream = new XStream();
		String xml = xstream.toXML(inf);
		return xml;
	}
	public static void sendRequest(String str) {

		try {
			Socket s = new Socket("localhost", 6666);
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			dout.writeUTF(str);
			// dout.write(bos.toByteArray());
			//dout.flush();
			
			
			DataInputStream din = new DataInputStream(s.getInputStream());
			String strInput = (String) din.readUTF();
			Info inf = getStringFromXML(strInput);
			System.out.println("Ответ сервера: " + inf.getMessage());
			
			dout.close();
			din.close();
			s.close();
		} catch (

		Exception e) {
			System.out.println(e);
		}
	}

	public static void menu() throws IOException, ClassNotFoundException {
		System.out.println("Выберите действие:");
		System.out.println("0 - Выход");
		System.out.println("1 - Получить список закрепленных задач");
		System.out.println("2 - Создать новую задачу");
		System.out.println("3 - Назначить задачу пользователю");
		System.out.println("4 - Изменить статус задачи");
		System.out.print("?: ");
		// Enter data using BufferReader
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		// Reading data using readLine
		int action = Integer.parseInt(reader.readLine());
		
		while(action!=0) {
			switch (action) {
			case 0:
				break;
			case 1:
				getListTasks();
				break;
			case 2:
				createNewTask();
				break;
			case 3:
				changeUserTask();
				break;
			case 4:
				changeStateTask();
				break;
			default:
				System.out.println("Неверный пункт, попытайтесь еще раз.");
			}
		}
		
	}

	public static void getListTasks() throws IOException, ClassNotFoundException {
		Info inf = new Info(true, "get list tasks");
		inf.setLogin("a");
		String xml = serializeInfoToXML(inf);
		sendRequest(xml);
	}

	public static void createNewTask() {

	}

	public static void changeUserTask() {

	}

	public static void changeStateTask() {

	}

}
