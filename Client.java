package pr4;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;


public class Client {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		menu();
	}

	public static Info getStringFromXML(String str) {
		XStream xstream = new XStream();
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

	public static Info getResponce(Info inf) {
		String xml = serializeInfoToXML(inf);
		Info response = sendRequest(xml);
		System.out.println("Ответ сервера: " + response.getMessage());
		return response;
	}
	
	public static void getListTasks() throws IOException, ClassNotFoundException {
		Info response = getResponce(new Info("log1","pas1", "get list tasks"));
		System.out.println("Список задач: ");
		@SuppressWarnings("unchecked")
		ArrayList<Task> masTask=(ArrayList<Task>)response.getResultObject();
		for (int i = 0; i < masTask.size(); i++) {
			System.out.println((i+1)+": " + masTask.get(i).getNameTask());
		}
	}

	public static void createNewTask() {
		Task t1=new Task("task111","description for task111","log1","40%");
		Info inf = getResponce(new Info("log1","pas1", "add new task",t1));
		inf.setResultObject(t1);
	}

	public static void changeUserTask() {
		Task t1=new Task("task1","","log2","");
		Info inf = getResponce(new Info("log1","pas1", "change owner",t1));
		inf.setResultObject(t1);
	}

	public static void changeStateTask() {

		
		Task t1=new Task();
		t1.setNameTask("task111");
		t1.setState("80%");
		Info inf = getResponce(new Info("log1","pas1", "change state",t1));
		inf.setResultObject(t1);
		
	}

}
