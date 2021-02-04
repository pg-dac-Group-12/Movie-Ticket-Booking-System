package com.app.bookmymovie.dto;

import java.util.ArrayList;
import java.util.List;

public class ImageDTO {

	private String name, type;
	//private List<byte[]> data = new ArrayList<byte[]>();
	private byte[] data ;
	//byte[] data;

	public ImageDTO(byte[] data, String type) {
		this.data = data ;
		//System.out.println(data.getContentType());
		this .type = type ;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data ;
	}

}
