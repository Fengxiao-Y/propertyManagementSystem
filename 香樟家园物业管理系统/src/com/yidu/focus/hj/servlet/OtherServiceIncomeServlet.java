package com.yidu.focus.hj.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yidu.focus.hj.dao.LesseeInformationDao;
import com.yidu.focus.hj.dao.impl.LesseeInformationDaoImpl;
import com.yidu.focus.hj.domain.LesseeInformation;

/**
 * Servlet implementation class OtherServiceIncomeServlet
 */
@WebServlet("/OtherServiceIncomeServlet")
public class OtherServiceIncomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OtherServiceIncomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������Ӧ��������
		response.setContentType("text/html");
		//������Ӧ�ַ�������
		response.setCharacterEncoding("utf-8");
		//���������ַ�������
		request.setCharacterEncoding("utf-8");
		//����������ҳ������
		String method = request.getParameter("method");
		if(method.equals("findAll")){
			findAll(request,response);
		}else if(method.equals("add")){
			add(request,response);
		}else if(method.equals("update")){
			update(request,response);
		}else if(method.equals("delete")){
			delete(request,response);
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�������������
		PrintWriter out = response.getWriter();
		//����������ҳ����
		String leaseContractId = request.getParameter("leaseContractId");
		String houseId = request.getParameter("houseId");
		String lesseeName = request.getParameter("lesseeName");
		String condition = " where 1=1 ";
		if(leaseContractId!=null&&!leaseContractId.equals("")){
			condition=condition+" and leaseContractId like '%"+leaseContractId+"%' ";
		}
		if(houseId!=null&&!houseId.equals("")){
			condition=condition+" and houseId like '%"+houseId+"%' ";
		}
		if(lesseeName!=null&&!lesseeName.equals("")){
			condition=condition+" and lesseeName like '%"+lesseeName+"%' ";
		}
		//����������ҳ��rows��page
		int rows = Integer.parseInt(request.getParameter("rows"));
		int page = Integer.parseInt(request.getParameter("page"));
		//�������ݲ�����
		LesseeInformationDao lesseeInformationDao = new LesseeInformationDaoImpl();
		//�������ݲ�ִ�з�ҳ��ѯ����������浽������
		List<LesseeInformation> lesseeInformationList = lesseeInformationDao.findByPage(rows, page, condition);
		int totalRows = lesseeInformationDao.count(condition);
		//����ӳ�伯�϶���
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", lesseeInformationList);
		//����Gson����
		Gson gson = new Gson();
		//ͨ��Gson����Map����ת����Json���ݸ�ʽ
		String jsonData = gson.toJson(mapData);
		out.print(jsonData);
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
