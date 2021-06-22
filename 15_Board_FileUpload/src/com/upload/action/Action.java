package com.upload.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {

	// 반환타입이 ActionFoward인 추상 메서드
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
