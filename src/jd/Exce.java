package jd;

import java.io.IOException;

public class Exce {

	public static void main(String[] args) {
		
		try {
			pow();
			throw new Error();
			//throw new IllegalArgumentException();
			//throw new IOException();
		} finally{
			throw new Error();
			//throw new IOException();
		}
		
	}
	
	static void pow(){
		//throw new IOException();
		throw new IllegalArgumentException();
	}
}
