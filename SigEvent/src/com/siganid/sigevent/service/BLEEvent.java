package com.siganid.sigevent.service;

import android.bluetooth.BluetoothDevice;

public class BLEEvent extends Event {

	public BLEEvent(String type) {
		super(type);
	}

	public Object[] args;

	String address;
	BluetoothDevice device;
	String typeCode;
	String deviceID;

	/**
	 * 发送设备
	 * 
	 * @param type
	 * @param device
	 */
	public BLEEvent(String type, BluetoothDevice device) {
		super(type);
		this.type = type;
		this.device = device;
	}

	/**
	 * 发送事件带 信息或者地址字符串
	 * 
	 * @param type
	 * @param address
	 */
	public BLEEvent(String type, String address) {
		super(type);
		this.type = type;
		this.address = address;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BluetoothDevice getDevice() {
		return device;
	}

	public void setDevice(BluetoothDevice device) {
		this.device = device;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	/**
	 * 发送事件带typecode & deviceid
	 * 
	 * @param type
	 * @param address
	 */
	public BLEEvent(String type, String typeCode, String deviceID) {
		super(type);
		this.type = type;
		this.typeCode = typeCode;
		this.deviceID = deviceID;
	}

}
