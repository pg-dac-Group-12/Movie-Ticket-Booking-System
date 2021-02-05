package com.app.bookmymovie.dto;

public class RazorpayDTO {
	@Override
	public String toString() {
		return "RazorpayDTO [razorpayPaymentId=" + razorpayPaymentId + ", razorpayOrderId=" + razorpayOrderId
				+ ", razorpaySignature=" + razorpaySignature + "]";
	}
	String razorpayPaymentId ;
	String razorpayOrderId ;
	String razorpaySignature ;

	public RazorpayDTO() {
		super();
	}
	public
	RazorpayDTO(String razorpayPaymentId, String razorpayOrderId, String razorpaySignature) {
		super();
		this.razorpayPaymentId = razorpayPaymentId;
		this.razorpayOrderId = razorpayOrderId;
		this.razorpaySignature = razorpaySignature;
	}
	public String getRazorpayPaymentId() {
		return razorpayPaymentId;
	}
	public void setRazorpayPaymentId(String razorpayPaymentId) {
		this.razorpayPaymentId = razorpayPaymentId;
	}
	public String getRazorpayOrderId() {
		return razorpayOrderId;
	}
	public void setRazorpayOrderId(String razorpayOrderId) {
		this.razorpayOrderId = razorpayOrderId;
	}
	public String getRazorpaySignature() {
		return razorpaySignature;
	}
	public void setRazorpaySignature(String razorpaySignature) {
		this.razorpaySignature = razorpaySignature;
	}
}
