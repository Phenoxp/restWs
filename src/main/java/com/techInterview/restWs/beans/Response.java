package com.techInterview.restWs.beans;

public class Response {
	private String content;
	
	public Response() {
		
	}

	public Response(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Response [content=" + content + "]";
	}
	
	
}
