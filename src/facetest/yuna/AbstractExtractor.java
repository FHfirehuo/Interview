package facetest.yuna;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import facetest.yuna.dto.Article;

public abstract class AbstractExtractor {

	final static Logger logger = LoggerFactory.getLogger(AbstractExtractor.class);
	
	EmailService emailService;
	
	ConfluenceService confluenceService;
	
	String getExtractorName(){
		
		return this.getClass().getName();
		
	}
	
	EmailService getEmailService(){
		return emailService;
	}
	
	abstract List<Article> extractEmail();

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
}
