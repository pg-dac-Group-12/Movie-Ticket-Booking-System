package com.app.bookmymovie.pojo;

public class RazorpayClient {

	String keyId;
	String keySecret;
	
	public RazorpayClient(String keyId, String keySecret) {
		super();
		this.keyId = keyId;
		this.keySecret = keySecret;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKey_id(String keyId) {
		this.keyId = keyId;
	}

	public String getKeySecret() {
		return keySecret;
	}

	public void setKey_secret(String keySecret) {
		this.keySecret = keySecret;
	}
	
	
	
	

}
