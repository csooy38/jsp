package com.paging.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paging.model.BoardDAO;
import com.paging.model.BoardDTO;

public class BoardSearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String find_field = request.getParameter("find_field");
		String find_text = request.getParameter("find_text");

		BoardDAO dao = BoardDAO.getInstance();
		// List<BoardDTO> list = dao.searchBoard(find_field, find_text);

		//request.setAttribute("search", list);
		request.setAttribute("find_field", find_field);
		request.setAttribute("find_text", find_text);

		// 페이징 작업
		int rowsize = 5; // 한 페이지당 보여질 게시물의 수
		int block = 3; // 아래에 보여질 페이지의 최대 수 예) [1][2][3] / [4][5][6]
		int totalRecord = 0;// DB상의 게시물 전체 수
		int allPage = 0; // 전체 페이지 수

		int page = 0; // 현재 페이지 변수

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {
			page = 1; // 처음으로 검색 결과가 생성된 경우
		}

		// 해당 페이지에서 시작 번호
		int startNo = (page * rowsize) - (rowsize - 1);

		// 해당 페이지에서 마지막 번호
		int endNo = (page * rowsize);

		// 해당 페이지의 시작 블럭
		int startBlock = (((page - 1) / block) * block) + 1;

		// 해당 페이지의 마지막 블럭
		int endBlock = (((page - 1) / block) * block) + block;

		// DB상의 전체 게시물의 수를 확인하는 메서드
		totalRecord = dao.getSearchListCount(find_field, find_text);

		// 전체 게시물의 수를 한 페이지당 보여질 게시물의 수로 나누어 주어야 한다.
		// 이 과정을 거치면 전체 페이지 수가 나온다.
		// 전체 페이지 수가 나올 때 나머지가 있으면 무조건 페이지 수를 1만큼 올려주어야 한다.

		allPage = (int) (Math.ceil(totalRecord / (double) rowsize)); // Math.ceil() : 나머지가 있으면 무조건 올림하는 메서드
		System.out.println("page >>> " + allPage);

		if (endBlock > allPage) { // 마지막 블럭 수를 최대 전체 페이지 수까지로 지정.
			endBlock = allPage;
		}

		// 페이지에 해당하는 게시물을 가져오는 메서드 호출
		List<BoardDTO> pageList = dao.getSearchBoardList(find_field, find_text, page, rowsize);

		// 지금까지 페이징 처리 시 작업했던 모든 값들을 키로 저장해서 view 페이지로 넘겨 주자.
		request.setAttribute("page", page);
		request.setAttribute("rowsize", rowsize);
		request.setAttribute("block", block);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("allPage", allPage);
		request.setAttribute("startNo", startNo);
		request.setAttribute("endNo", endNo);
		request.setAttribute("startBlock", startBlock);
		request.setAttribute("endBlock", endBlock);
		request.setAttribute("List", pageList);

	}

}
