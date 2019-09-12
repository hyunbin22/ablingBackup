package com.semi.sms.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import api.etc.sms.Coolsms;
import api.etc.sms.SMS;


/**
 * Servlet implementation class SendSMSServlet
 */
@WebServlet("/sendSms.do")
public class SendSMSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SendSMSServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone = request.getParameter("phone");
		
		System.out.println("servlet" + phone);
		
		String api_key = "NCSOJCPKELHANSUP";
		String api_secret = "JTSLLFOAKZ3DIJBB1V8KPZSYT1S7GVBS";
		Coolsms coolsms = new Coolsms(api_key, api_secret);
		String key = Integer.toString((int)(Math.random()*9));			

		for(int i = 0; i < 5; i++) {
			key += Integer.toString((int)(Math.random()*9));
		}
		
		HashMap<String, String> set = new HashMap<String,String>();
		set.put("to", phone);
		set.put("from", "01074008665");
		set.put("text", "abling입니다. 인증번호는 [" + key + "] 입니다. 3분안에 입력해 주세요.");
		set.put("type", "sms");
		
		JSONObject result = coolsms.send(set); // 보내기&전송결과받기
		String resultStr = "";
		if ((boolean) result.get("status") == true) {
			// 메시지 보내기 성공 및 전송결과 출력
			System.out.println("성공");
			System.out.println(result.get("group_id")); // 그룹아이디
			System.out.println(result.get("result_code")); // 결과코드
			System.out.println(result.get("result_message")); // 결과 메시지
			System.out.println(result.get("success_count")); // 메시지아이디
			System.out.println(result.get("error_count")); // 여러개 보낼시 오류난 메시지 수
			resultStr = "success";
		} else {
			// 메시지 보내기 실패
			System.out.println("실패");
			System.out.println(result.get("code")); // REST API 에러코드
			System.out.println(result.get("message")); // 에러메시지
			resultStr =  "fail";
		}
		
		System.out.println(resultStr);
		
		response.setContentType("charset=UTF-8");
		response.getWriter().write(key); 

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
