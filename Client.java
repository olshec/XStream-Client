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

	public static void sendRequest(String str) {

		try {
			Socket s = new Socket("localhost", 6666);
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			dout.writeUTF(str);
			// dout.write(bos.toByteArray());
			dout.flush();
			dout.close();
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

	public static void getListTasks() throws IOException, ClassNotFoundException {
//		Info inf = new Info(true,"get tasks");
//		 //Serialization of object
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        ObjectOutputStream out = new ObjectOutputStream(bos);
//        out.writeObject(inf);
// 
//        //De-serialization of object
//        ByteArrayInputStream bis = new   ByteArrayInputStream(bos.toByteArray());
//        ObjectInputStream in = new ObjectInputStream(bis);
//        Info inf2 = (Info) in.readObject();
//        System.out.println("Info message = "+ inf2.getMessage());

		XStream xstream = new XStream();
		Info inf = new Info(true, "end");
		String xml = xstream.toXML(inf);
		sendRequest(xml);
	}

	public static void createNewTask() {

	}

	public static void changeUserTask() {

	}

	public static void changeStateTask() {

	}

}
