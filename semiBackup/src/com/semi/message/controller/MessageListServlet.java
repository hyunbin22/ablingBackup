package com.semi.message.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.member.model.service.MemberService;
import com.semi.message.model.service.MessageService;
import com.semi.message.model.vo.Message;

/**
 * Servlet implementation class MessageSubmitServlet
 */
@WebServlet("/message/messageList.do")
public class MessageListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MessageListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String fromId = request.getParameter("fromId");
		String toId = request.getParameter("toId");
		String listType = request.getParameter("listType");
		
		String text = request.getParameter("chatContent");
		if(fromId == null || fromId.equals("") || toId == null || toId.equals("") || listType == null || listType.equals("")) {
			response.getWriter().write("");
		} 
		else if(listType.equals("ten")) response.getWriter().write(getTen(URLDecoder.decode(fromId,"UTF-8"), URLDecoder.decode(toId,"UTF-8")));
		else {
			try {
				response.getWriter().write(getId(URLDecoder.decode(fromId,"UTF-8"), URLDecoder.decode(toId,"UTF-8"), listType));
			} catch(Exception e) {
				response.getWriter().write("");
			}
		}	
	}
	
	public String getTen(String fromId, String toId) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		MessageService service = new MessageService();
		int fromMNum = new MemberService().selectMember(fromId).getmNum();
		int toMNum = new MemberService().selectMember(toId).getmNum();
		
		List<Message> chatList = service.messageListByRecent(fromMNum, toMNum, 100);
		if(chatList.size()==0) return "";
		for(int i = 0; i < chatList.size(); i++) {
			result.append("[{\"value\": \"" + chatList.get(i).getFromMember().getmId() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getToMember().getmId() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getMessageText() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getMessageSendDate() + "\"}]");
			if(i != chatList.size()-1) result.append(",");
			
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size()-1).getMessageNum() + "\"}");
		service.readChat(fromMNum, toMNum);
		return result.toString();
	}
	
	public String getId(String fromId, String toId, String chatId) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		MessageService service = new MessageService();
		int fromMNum = new MemberService().selectMember(fromId).getmNum();
		int toMNum = new MemberService().selectMember(toId).getmNum();
		
		List<Message> chatList = service.messageListById(fromMNum, toMNum, chatId);

		if(chatList.size()==0) return "";
		for(int i = 0; i < chatList.size(); i++) {
			result.append("[{\"value\": \"" + chatList.get(i).getFromMember().getmId() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getToMember().getmId() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getMessageText() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getMessageSendDate() + "\"}]");
			if(i != chatList.size()-1) result.append(",");
			
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size()-1).getMessageNum() + "\"}");
		service.readChat(fromMNum, toMNum);
		return result.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
