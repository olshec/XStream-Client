package pr4;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

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
	
	
	public static Info sendRequest(String str) {
		Info inf = null;
		try {
			Socket s = new Socket("localhost", 6666);
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			dout.writeUTF(str);
			// dout.write(bos.toByteArray());
			//dout.flush();
			DataInputStream din = new DataInputStream(s.getInputStream());
			String strInput = (String) din.readUTF();
			inf = getStringFromXML(strInput);
			dout.close();
			din.close();
			s.close();
		} catch (

		Exception e) {
			System.out.println(e);
		}
		return inf;
	}

	
	public static void interfaceMenu() {
		System.out.println("Выберите действие:");
		System.out.println("0 - Выход");
		System.out.println("1 - Получить список закрепленных задач");
		System.out.println("2 - Создать новую задачу");
		System.out.println("3 - Назначить задачу пользователю");
		System.out.println("4 - Изменить статус задачи");
		System.out.print("?: ");
	}
	
	public static void menu() throws IOException, ClassNotFoundException {
		
		// Enter data using BufferReader
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		//interfaceMenu();
		int action = 1;
		
		while(action!=0) {
			interfaceMenu();
			action = Integer.parseInt(reader.readLine());
			switch (action) {
			case 0:
				System.out.println("До свидания!");
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
			System.out.println("\n");
		}
		
	}

	public static void getListTasks() throws IOException, ClassNotFoundException {
		Info inf = new Info("log1","pas1", "get list tasks");
		String xml = serializeInfoToXML(inf);
		Info response = sendRequest(xml);
		System.out.println("Ответ сервера: " + response.getMessage());
		System.out.println("Список задач: ");
		@SuppressWarnings("unchecked")
		ArrayList<Task> masTask=(ArrayList<Task>)response.getResultObject();

		//Task []mast=(Task [])inf.getResultObject();
		for (int i = 0; i < masTask.size(); i++) {
			System.out.println((i+1)+": " + masTask.get(i).getNameTask());
		}
		
	}

	public static void createNewTask() {

	}

	public static void changeUserTask() {

	}

	public static void changeStateTask() {

	}

}
