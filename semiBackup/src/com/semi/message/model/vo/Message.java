package com.semi.message.model.vo;

import java.sql.Date;

import com.semi.member.model.vo.Member;
import com.semi.mento.model.vo.Mento;

public class Message {
	
	private int messageNum;
	private int fromMNum;
	private int toMNum;
	private String messageText;
	private String messageSendDate;
	private int messageReadCount;
	private Member fromMember;
	private Member toMember;
	
	public Message() {
		// TODO Auto-generated constructor stub
	}

	public Message(int messageNum, int fromMNum, int toMNum, String messageText, String messageSendDate,
			int messageReadCount, Member fromMember, Member toMember) {
		super();
		this.messageNum = messageNum;
		this.fromMNum = fromMNum;
		this.toMNum = toMNum;
		this.messageText = messageText;
		this.messageSendDate = messageSendDate;
		this.messageReadCount = messageReadCount;
		this.fromMember = fromMember;
		this.toMember = toMember;
	}

	public int getMessageNum() {
		return messageNum;
	}

	public void setMessageNum(int messageNum) {
		this.messageNum = messageNum;
	}

	public int getFromMNum() {
		return fromMNum;
	}

	public void setFromMNum(int fromMNum) {
		this.fromMNum = fromMNum;
	}

	public int getToMNum() {
		return toMNum;
	}

	public void setToMNum(int toMNum) {
		this.toMNum = toMNum;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public String getMessageSendDate() {
		return messageSendDate;
	}

	public void setMessageSendDate(String messageSendDate) {
		this.messageSendDate = messageSendDate;
	}

	public int getMessageReadCount() {
		return messageReadCount;
	}

	public void setMessageReadCount(int messageReadCount) {
		this.messageReadCount = messageReadCount;
	}

	public Member getFromMember() {
		return fromMember;
	}

	public void setFromMember(Member fromMember) {
		this.fromMember = fromMember;
	}

	public Member getToMember() {
		return toMember;
	}

	public void setToMember(Member toMember) {
		this.toMember = toMember;
	}

	@Override
	public String toString() {
		return "Message [messageNum=" + messageNum + ", fromMNum=" + fromMNum + ", toMNum=" + toMNum + ", messageText="
				+ messageText + ", messageSendDate=" + messageSendDate + ", messageReadCount=" + messageReadCount
				+ ", fromMember=" + fromMember + ", toMember=" + toMember + "]";
	}

	
	
}
