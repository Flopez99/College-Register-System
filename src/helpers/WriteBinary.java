package helpers;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.College;

public class WriteBinary {
	public static void writeBinary(College college) throws IOException {
		College c = college;
		FileOutputStream dos = new FileOutputStream("RawData/Data/data.dat");
		ObjectOutputStream oos = new ObjectOutputStream(dos);
		oos.writeObject(c);
		oos.close();
		
		System.out.println("Done writing!");
	}
	
	public static void readBinary(College college) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("RawData/Data/data.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
		College c = (College)(ois.readObject());
		System.out.println(c);
		ois.close();
	}
}
