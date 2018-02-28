package facetest.yuna.dto;

import java.util.concurrent.ArrayBlockingQueue;

public class ArticleBlockingQueue<T> extends ArrayBlockingQueue<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArticleBlockingQueue() {
		super(1);
	}
	
}
