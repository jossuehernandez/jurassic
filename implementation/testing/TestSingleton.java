package testing;

import mx.com.dalvik.Singleton;

public class TestSingleton {

		public static void main(String[] args) {
			
			Singleton Singleton1 = Singleton.getInstance();
			 
			System.out.println(Singleton1.getInfo()); //Initial class info
			 
			Singleton Singleton2 = Singleton.getInstance();
			Singleton2.setInfo("New class info");
			 
			System.out.println(Singleton1.getInfo()); //New class info
			System.out.println(Singleton2.getInfo()); //New class info
		}
}
