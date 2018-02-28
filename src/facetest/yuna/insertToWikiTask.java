package facetest.yuna;

import facetest.yuna.dto.ExchangeEmailShallowDTO;

public class insertToWikiTask implements Runnable{
	
	private ExchangeEmailShallowDTO email;
	
	public insertToWikiTask() {
		Thread t = new Thread(this);
		t.start();
	}

	public insertToWikiTask(ExchangeEmailShallowDTO email) {
		this.email = email;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		System.out.println("aa");
		
	}
}
