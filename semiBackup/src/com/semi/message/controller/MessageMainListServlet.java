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
 * Servlet implementation class MessageMainListServlet
 */
@WebServlet("/message/messageMainList.do")
public class MessageMainListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MessageMainListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String userId = request.getParameter("userId");

		try {
			userId = URLDecoder.decode(userId,"UTF-8");
			response.getWriter().write(getBox(userId));
			
		} catch(Exception e) {
			System.out.println("오류발생");
			e.printStackTrace();
			response.getWriter().write(new MessageService().noReadCount(userId)+"");				
		}
	}

	
	public String getBox(String userId) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		MessageService service = new MessageService();
		
		List<Message> chatList = service.getMessageBox(userId);
		if(chatList.size()==0) return "";
		for(int i = chatList.size()-1; i >= 0; i--) {
			String unread = "";
			if(userId.equals(chatList.get(i).getToMember().getmId())) {	//로그인한 사람이랑 채팅리스트에서 받는사람이 같을때
				unread = service.noReadCountById(chatList.get(i).getFromMember().getmId(), userId)+"";
				if(unread.equals("0")) unread = "";
			}
			result.append("[{\"value\": \"" + chatList.get(i).getFromMember().getmId() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getToMember().getmId() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getMessageText() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getMessageSendDate() + "\"},");
			result.append("{\"value\": \"" + unread + "\"}]");
			if(i != 0) result.append(",");
			
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size()-1).getMessageNum() + "\"}");
		return result.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
