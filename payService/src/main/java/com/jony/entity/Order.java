package com.jony.entity;

import java.io.Serializable;

public class Order implements Serializable{
    private static final long serialVersionUID = 3148176768559230888L;
    private String uid;
    private String orderId;


	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
