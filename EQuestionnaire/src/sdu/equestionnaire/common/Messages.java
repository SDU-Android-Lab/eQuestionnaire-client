package sdu.equestionnaire.common;

import java.io.Serializable;

public class Messages implements Serializable {
	/*
	 * messagetype对应的为所选择的类型，看message——type接口
	 */
	private int message_type;

	public Object getObj() {
		return obj;
	}

	public Messages(int message_type, Object obj) {
		this.message_type = message_type;
		this.obj = obj;

	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	private Object obj;

	public int getMessage_type() {
		return message_type;
	}

	public void setMessage_type(int message_type) {
		this.message_type = message_type;
	}

	public String toString() {
		return "" + message_type + obj;

	}
}
