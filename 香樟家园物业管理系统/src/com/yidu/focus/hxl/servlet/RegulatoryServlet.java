package com.yidu.focus.hxl.servlet;

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
import com.yidu.focus.hxl.dao.RegulatoryDao;
import com.yidu.focus.hxl.dao.impl.RegulatoryDaoImpl;
import com.yidu.focus.hxl.domain.Regulatory;

/**
 * 
 * ���ܣ�������Ϣ�� 
 * ���ߣ���ϣ��
 * ���ڣ�2019��10��31������9:08:45
 * �汾��1.0
 */
@WebServlet("/RegulatoryServlet")
public class RegulatoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegulatoryServlet() {
		super();
		// TODO Auto-generated constructor stub
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
	 * ɾ��
	 * @param request ����
	 * @param response ��Ӧ
	 * @throws IOException �쳣
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		//��ȡ�������������ݹ���������ַ���
		String fileIdStr=request.getParameter("fileIdStr");
		
		//���ַ������а�","(����)�ָ�����ַ�������
		String[] fileIds=fileIdStr.split(",");

		//�������ݲ��������
		RegulatoryDao regulatorydao=new RegulatoryDaoImpl();

		//����������ɾ�����п���ʧ�ܣ��˴�ʹ���쳣������ʵ��
		try{
			//ʹ��ѭ����������ɾ��
			for(int i=0;i<fileIds.length;i++){
				//��ȡ��ǰ���ַ���
				String fileId=fileIds[i];
				//ִ��ɾ������
				regulatorydao.deleteById(fileId);
			}
			out.print("success");

		}catch(Exception e){
			out.print("failure");
		}	
		out.close();
		
	}
	/**
	 * �޸�
	 * @param request ����
	 * @param response ��Ӧ
	 * @throws IOException �쳣
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();	
		//�������Կͻ��˵�����
		String fileId=request.getParameter("fileId");
		String fileTitle=request.getParameter("fileTitle");		
		String fileTheme=request.getParameter("fileTheme");
		String fileState=request.getParameter("fileState");
		String executionTime=request.getParameter("executionTime");
		
		//���ݷ�װ�ɶ���
		Regulatory regulatory=new Regulatory(fileId, fileTitle, fileTheme, fileState, executionTime);

		//�������ݲ����ִ����ӷ���
		RegulatoryDao regulatoryDao=new RegulatoryDaoImpl();
		int rows=regulatoryDao.update(regulatory);
		//�ж�����Ƿ�ɹ�
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}

		out.close();
		
	}
	/**
	 * ���
	 * @param request ����
	 * @param response ��Ӧ
	 * @throws IOException �쳣
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();

		//�������Կͻ��˵�����
		String fileId=request.getParameter("fileId");
		String fileTitle=request.getParameter("fileTitle");		
		String fileTheme=request.getParameter("fileTheme");
		String fileState=request.getParameter("fileState");
		String executionTime=request.getParameter("executionTime");	

		//���ݷ�װ�ɶ���
		Regulatory regulatory=new Regulatory(fileId, fileTitle, fileTheme, fileState, executionTime);

		//�������ݲ����ִ����ӷ���
		RegulatoryDao regulatoryDao=new RegulatoryDaoImpl();
		int rows=regulatoryDao.add(regulatory);
		//�ж�����Ƿ�ɹ�
		if(rows>0){
			out.print("success");
		
		}else{
			out.print("failure");
			
		}

		out.close();
		
	}
	/**
	 * ��ѯȫ��
	 * @param request ����
	 * @param response ��Ӧ
	 * @throws IOException �쳣
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���������
		PrintWriter out=response.getWriter();
		
		//�õ����������е���������
		String fileTheme=request.getParameter("fileTheme");
		String fileState=request.getParameter("fileState");
		
		//���ɲ�ѯ����
		//select * from ���� where ���� limit n1,n2 
		//where �ֶ���=? and �ֶ���=? and �ֶ���=?
		String condition=" where 1=1 ";
		//���������ݽ����жϲ���ϳɲ�ѯ����
		if(fileState!=null && !fileState.equals("")){
			condition=condition+"and fileState like '%"+fileState+"%' ";
		}
		
		if(fileTheme!=null && !fileTheme.equals("")){
			condition=condition+"and fileTheme like '%"+fileTheme+"%' ";
		}
		
		//�������Կͻ��˵�datagrid����Ĵ��ݹ�����page��rows����
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));

		//�������ݲ�ִ�з�ҳ��ѯ
		RegulatoryDao regulatorydao=new RegulatoryDaoImpl();
		//��õ�ǰҳ�����ݼ���
		List<Regulatory> regulatoryList=regulatorydao.findByPage(rows, page, condition);

		//��ѯ��regulatory����ܼ�¼��
		int totalRows=regulatorydao.count();
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", regulatoryList);
		//����Gson����
		Gson gson = new Gson();
		//ͨ��Gson����Map����ת����json���ݸ�ʽ
		String jsonData = gson.toJson(mapData);
		//��json����������ͻ���
		out.println(jsonData);
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
