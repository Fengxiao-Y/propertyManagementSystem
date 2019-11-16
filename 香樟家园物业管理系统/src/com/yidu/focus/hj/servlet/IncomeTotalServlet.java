package com.yidu.focus.hj.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yidu.focus.hj.dao.IncomeTotalDao;
import com.yidu.focus.hj.dao.impl.IncomeTotalDaoImpl;
import com.yidu.focus.hj.domain.IncomeTotal;

/**
 * 
 * ���ܣ�ʵ�������������
 * ��д�ߣ�����
 * �汾��1.0
 * ���ڣ�2019��10��31������8:53:23
 */
@WebServlet("/IncomeTotalServlet")
public class IncomeTotalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncomeTotalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������Ӧ�ַ�������
		response.setCharacterEncoding("utf-8");
		//������Ӧ��������
		response.setContentType("text/html");
		//���������ַ�������
		request.setCharacterEncoding("utf-8");
		//�������������
		PrintWriter out = response.getWriter();
		//����������ҳ����
		String datego = "";
		String datestop="";
		String source = request.getParameter("source");
		datego = request.getParameter("datego");
		datestop = request.getParameter("datestop");
		
		
		String month=request.getParameter("month");
		String condition = " where 1=1 ";
		if(datestop==null || datestop.equals("")){
			datestop="2099-12-30";
		}
		if(source!=null&&!source.equals("")){
			condition=condition+" and source like '%"+source+"%' ";
		}
		if(datego!=null&&!datego.equals("")){
			condition=condition+" and source_date between '"+datego+"' and '"+datestop+"'";
		}
		if(month!=null&&!month.equals("")){
			condition=condition+" and source_date like '%"+month+"%' ";
		}
		
		//����������ҳ��rows��page
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		int page = Integer.parseInt(request.getParameter("page"));
		
		//�������ݲ�����
		IncomeTotalDao incomeTotalDao = new IncomeTotalDaoImpl();
		//�������ݲ㷽��ִ�з�ҳ��ѯ����������浽������
		List<IncomeTotal> incomeTotalList = incomeTotalDao.findByPage(rows, page, condition);
		int totalRows = incomeTotalDao.count(condition);
		double sum = 0;
		for (IncomeTotal incomeTotal : incomeTotalList) {
			sum = incomeTotal.getMoneySum()+sum;
		}
		//����ӳ�伯�϶���
	
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", incomeTotalList);
		List<IncomeTotal> footer = new ArrayList<>();
		IncomeTotal incomeTotal = new IncomeTotal();
		
		incomeTotal.setSource("�ܼƣ�");
		incomeTotal.setMoney(sum);
		footer.add(incomeTotal);
		mapData.put("footer", footer);
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
