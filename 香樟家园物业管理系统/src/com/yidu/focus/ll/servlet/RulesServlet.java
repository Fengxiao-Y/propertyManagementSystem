package com.yidu.focus.ll.servlet;

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
import com.yidu.focus.ll.dao.RulesDao;
import com.yidu.focus.ll.dao.impl.RulesDaoImpl;
import com.yidu.focus.ll.domain.Rules;
/**
 * 
 * ���ܣ������ƶȱ� 
 * ���ߣ�����
 * ���ڣ�2019��10��31������9:12:00
 * �汾��1.0
 */
@WebServlet("/RulesServlet")
public class RulesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RulesServlet() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����������Ӧ���ַ�������������
		//����������ַ���
		request.setCharacterEncoding("utf-8");
		//������Ӧ���ַ�������������
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		//��ȡ����·���еĲ�������ֵ
		String method=request.getParameter("method");
	
		//�ж�
		if("findAll".equals(method)){
			this.findAll(request,response);
		}else if("add".equals(method)){
			this.add(request,response);
		}else if("update".equals(method)){
			this.update(request,response);
		}else if("delete".equals(method)){
			this.delete(request,response);
		}	
	}

	/**
	 * ��������ɾ������
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException �׳��쳣
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���������
		PrintWriter out=response.getWriter();
		//��ȡ�������������ݹ������ļ�����ַ���
		String rulesIdStr=request.getParameter("rulesIdStr");
		//���ַ������а�","(����)�ָ�����ַ�������
		String[] rulesIds=rulesIdStr.split(",");

		//�������ݲ��������
		RulesDao rulesdao=new RulesDaoImpl();

		//����������ɾ�����п���ʧ�ܣ��˴�ʹ���쳣������ʵ��
		try{
			//ʹ��ѭ����������ɾ��
			for(int i=0;i<rulesIds.length;i++){
				//��ȡ��ǰ���ַ���
				String rulesId=rulesIds[i];
				//ִ��ɾ������
				rulesdao.delete(rulesId);
			}
			out.print("success");
		}catch(Exception e){
			out.print("failure");
		}

		//�ر��������
		out.close();		
	}

	/**
	 * �޸�
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException �׳��쳣
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���������
		PrintWriter out=response.getWriter();
		//�������Կͻ��˵�����
		//���
		String rulesId=request.getParameter("rulesId");
		//����
		String rulesTitle=request.getParameter("rulesTitle");
		//����
		String rulesTheme=request.getParameter("rulesTheme");
		//״̬
		String rulesStatus=request.getParameter("rulesStatus");
		//ִ��ʱ��
		String rulesTime=request.getParameter("rulesTime");

		//���ݷ�װ�ɶ���
		Rules rules=new Rules(rulesId, rulesTitle, rulesTheme, rulesStatus, rulesTime);

		//�������ݲ����ִ����ӷ���
		RulesDao rulesdao=new RulesDaoImpl();
		int rows=rulesdao.update(rules);
		//�ж�����Ƿ�ɹ�
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		//�ر������
		out.close();		
	}

	/**
	 * ����
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException �׳��쳣
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�������������
		PrintWriter out=response.getWriter();
		//�������Կͻ��˵�����
		//���
		String rulesId=request.getParameter("rulesId");
		//����
		String rulesTitle=request.getParameter("rulesTitle");
		//����
		String rulesTheme=request.getParameter("rulesTheme");
		//״̬
		String rulesStatus=request.getParameter("rulesStatus");
		//ִ��ʱ��
		String rulesTime=request.getParameter("rulesTime");

		//���ݷ�װ�ɶ���
		Rules rules=new Rules(rulesId, rulesTitle, rulesTheme, rulesStatus, rulesTime);

		//�������ݲ����ִ����ӷ���
		RulesDao rulesdao=new RulesDaoImpl();
		int rows=rulesdao.add(rules);
		//�ж�����Ƿ�ɹ�
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		//�ر������
		out.close();		
	}

	/**
	 *��ҳ��ʾ
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException  �׳�����
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���������
		PrintWriter out=response.getWriter();

		//�õ����������е���������
		//���
		String rulesId=request.getParameter("rulesId");
		//����
		String rulesTheme=request.getParameter("rulesTheme");
		//״̬
		String rulesStatus=request.getParameter("rulesStatus");

		//���ɲ�ѯ����
		String condition=" where 1=1 ";
		//���ݱ��ģ��ѯ
		if(rulesId!=null && !rulesId.equals("")){
			condition=condition+"and rulesId like '%"+rulesId+"%' ";
		}
		//��������ģ����ѯ
		if(rulesTheme!=null && !rulesTheme.equals("")){
			condition=condition+"and rulesTheme like '%"+rulesTheme+"%' ";
		}
		//����״̬ģ����ѯ
		if(rulesStatus!=null && !rulesStatus.equals("")){
			condition=condition+"and rulesStatus like '%"+rulesStatus+"%' ";
		}
		
		//�������Կͻ��˵�datagrid����Ĵ��ݹ�����page��rows����
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));


		//�������ݲ�ִ�з�ҳ��ѯ
		RulesDao rulesdao=new RulesDaoImpl();
		//��õ�ǰҳ�����ݼ���
		List<Rules> ruleslist=rulesdao.findByPage(rows, page, condition);
		//��ѯ��rules����ܼ�¼��
		int totalRows=rulesdao.count();
		//����map����
		Map<String, Object> mapData = new HashMap<String, Object>();
		//�����ݱ��浽map������
		mapData.put("total", totalRows);
		mapData.put("rows", ruleslist);
		//����Gson����
		Gson gson = new Gson();
		//ͨ��Gson����Map����ת����json���ݸ�ʽ
		String jsonData = gson.toJson(mapData);
		//��json����������ͻ���
		out.println(jsonData);
		//�ر������
		out.close();		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
