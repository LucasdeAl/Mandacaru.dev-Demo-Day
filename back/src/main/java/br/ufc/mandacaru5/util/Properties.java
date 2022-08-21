package br.ufc.mandacaru5.util;

import org.springframework.stereotype.Component;

@Component
public class Properties {

	private String post;
	
	public String getPost() {
		return post;
	}
	
	public void setPost(String post) {
		this.post = post;  
	}
}