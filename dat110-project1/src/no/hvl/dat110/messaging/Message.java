package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class Message {

	private byte[] payload;

	public Message(byte[] payload) {
		this.payload = payload; // TODO: check for length within boundary
		if(payload.length>MessageConfig.SEGMENTSIZE-1) {
			payload=null;
		}
	}

	public Message() {
		super();
	}

	public byte[] getData() {
		return this.payload; 
	}

	public byte[] encapsulate() {
		
		byte[] encoded = new byte[MessageConfig.SEGMENTSIZE];
	
		encoded[0] = (byte) payload.length;
		for(int i = 0; i<payload.length;i++) {
			encoded[i+1]=payload[i];
		}
		
		return encoded;
		
	}

	public void decapsulate(byte[] received) {

		int payloadLen = received[0];
		byte [] payloadReceived = new byte[payloadLen];
		for(int i=0; i<payloadLen; i++) {
			payloadReceived[i]=received[i+1];
		}
		payload= payloadReceived;
	}
}
