package requestPayload;

public class OrderPayload {
	
	OrderAddressPayload  address=new OrderAddressPayload();  
	String paymentMode;
	
	public OrderAddressPayload getOap() {
		return address;
	}
	public void setOap(OrderAddressPayload address) {
		this.address = address;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	
}
