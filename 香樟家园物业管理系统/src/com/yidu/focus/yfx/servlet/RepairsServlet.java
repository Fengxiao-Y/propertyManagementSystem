package com.yidu.focus.yfx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.yidu.focus.wzh.domain.Users;
import com.yidu.focus.yfx.dao.RepairsDao;
import com.yidu.focus.yfx.dao.impl.RepairsDaoImpl;
import com.yidu.focus.yfx.domain.Repairs;
/**
 * 
 * ���ܣ�Ͷ�߲����� 
 * ���ߣ��Ϸ�Т
 * ���ڣ�2019��10��31������8:51:48
 * �汾��1.0
 */
@WebServlet("/RepairsServlet")
public class RepairsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RepairsServlet() {
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���������ַ���
		request.setCharacterEncoding("utf-8");
		//������Ӧ��������
		response.setContentType("text/html");
		//������Ӧ�ַ��������ʽ
		response.setCharacterEncoding("utf-8");
		//���������еĲ���
		String method=request.getParameter("method");
		
		//�ж�·���еĲ���
		if("findAll".equals(method)){
			this.findAll(request,response);
		}else if("add".equals(method)){
			this.add(request,response);
		}else if("update".equals(method)){
			this.update(request,response);
		}else if("delete".equals(method)){
			this.delete(request,response);
		}else if("findByName".equals(method)){
			this.findByName(request,response);
		}else if("baoxiu".equals(method)){
			this.baoxiu(request,response);
		}
	}
	
	/**
	 * ҵ���ڿͻ��˱��޵ķ���
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException IO�쳣
	 */
	private void baoxiu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�������������
		PrintWriter out=response.getWriter();
		//����session����
		HttpSession session=request.getSession();
		//ȡ��session�е�user����
		Users user=(Users) session.getAttribute("users");
		//�õ���ǰ���û���
		String ownerName=user.getuName();
		//��������
		String repText=request.getParameter("repText");

		//��ȡ��ǰϵͳʱ��
		Date date = new Date();
		//��ϵͳʱ��ת��Ϊ���ݿ��ʽ
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//��ʱ��ת�����ַ�����ʽ
		String repTime=sdf.format(date);
		//Ĭ��Ա��
		String empName="����";
		//Ĭ�ϴ�����
		String repResult="δ����";
		//���ʱ��
		String repEndTime="2099-12-31";
		
		//���ݷ�װ�ɶ���
		Repairs repairs=new Repairs(ownerName, repText, repTime, empName, repResult, repEndTime);
		//�������ݲ����ִ����ӷ���,�õ�Ӱ������
		RepairsDao repairsDao=new RepairsDaoImpl();
		int rows=repairsDao.add(repairs);
		//�ж�����Ƿ�ɹ�
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		//�ر���
		out.close();
	}
	
	/**
	 * ͨ��ҵ���������ҵ����޼�¼
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException IO�쳣
	 */
	private void findByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�������������
		PrintWriter out=response.getWriter();
		//����session����
		HttpSession session=request.getSession();
		//ȡ��session�е�user����
		Users user=(Users) session.getAttribute("users");
		//�õ���ǰ���û���
		String ownerName=user.getuName();
		//�������ݲ����
		RepairsDao repairsDao=new RepairsDaoImpl();
		//�������ݲ㷽��
		List<Repairs> repairsList=repairsDao.findByName(ownerName);
		//����gson����
		Gson gson=new Gson();
		//���л�
		String repair=gson.toJson(repairsList);
		//���
		out.print(repair);
		//�ر���
		out.close();
	}

	/**
	 * ɾ�����ݿ���е�����
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws ServletException servlet�쳣
	 * @throws IOException IO���쳣
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�������������
		PrintWriter out=response.getWriter();
		//��ȡ�������������ݹ�����Ͷ�߱���ַ���
		String repStr=request.getParameter("repIdStr");	
		//���ַ������а�","(����)�ָ�����ַ�������
		String[] repIds=repStr.split(",");		
		//�������ݲ��������
		RepairsDao repairsDao=new RepairsDaoImpl();		
		//����������ɾ�����п���ʧ�ܣ��˴�ʹ���쳣������ʵ��
		try{
			//ʹ��ѭ����������ɾ��
			for(int i=0;i<repIds.length;i++){
				//��ȡ��ǰ���ַ���
				String temp=repIds[i];
				//���ַ���ת��������
				int repId=Integer.parseInt(temp);
				//ִ��ɾ������
				repairsDao.deleteById(repId);
			}
			out.print("success");
		}catch(Exception e){
			out.print("failure");
		}
		//�ر��������
		out.close();
	}

	/**
	 * �������ݿ���е�����
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws ServletException servlet�쳣
	 * @throws IOException IO���쳣
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�������������
		PrintWriter out=response.getWriter();		
		//�������Կͻ��˵�����
		//���ޱ��
		int repId=Integer.parseInt(request.getParameter("repId"));
		//ҵ������
		String ownerName=request.getParameter("ownerName");
		//��������
		String repText=request.getParameter("repText");
		//����ʱ��
		String repTime=request.getParameter("repTime");
		//������
		String empName=request.getParameter("empName");
		//������
		String repResult=request.getParameter("repResult");
		//�ط�ʱ��
		String repEndTime=request.getParameter("repEndTime");		
		//���ݷ�װ�ɶ���
		Repairs repairs=new Repairs(repId, ownerName, repText, repTime, empName, repResult, repEndTime);		
		//���屨�����ݲ��������
		RepairsDao repairsDao=new RepairsDaoImpl();
		//�������ݲ����ִ����ӷ������õ�Ӱ������
		int rows=repairsDao.update(repairs);
		//�ж��޸��Ƿ�ɹ�
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		//�ر������
		out.close();
	}

	/**
	 * �����ݿ������������
	 * @param request �������
	 * @param response ��Ӧ����
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�������������
		PrintWriter out=response.getWriter();
		//�������Կͻ��˵�����
		//ҵ������
		String ownerName=request.getParameter("ownerName");
		//��������
		String repText=request.getParameter("repText");
		//����ʱ��
		String repTime=request.getParameter("repTime");
		//������
		String empName=request.getParameter("empName");
		//������
		String repResult=request.getParameter("repResult");
		//�ط�ʱ��
		String repEndTime=request.getParameter("repEndTime");		
		//���ݷ�װ�ɶ���
		Repairs repairs=new Repairs(ownerName, repText, repTime, empName, repResult, repEndTime);
		//���屨�����ݲ��������
		RepairsDao repairsDao=new RepairsDaoImpl();
		//�������ݲ����ִ����ӷ���,�õ�Ӱ������
		int rows=repairsDao.add(repairs);
		//�ж�����Ƿ�ɹ�
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		//�ر���
		out.close();
	}
	/**
	 * �鿴ȫ������
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws ServletException servlet�쳣
	 * @throws IOException IO���쳣
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���������
		PrintWriter out=response.getWriter();		
		//�õ����������е���������
		//ҵ������
		String ownerName=request.getParameter("ownerName");
		//��������
		String repText=request.getParameter("repText");
		//����ʱ��
		String repTime=request.getParameter("repTime");	
		
		//���ɲ�ѯ����
		String condition=" where 1=1 ";
		//����ҵ������
		if(ownerName!=null && !ownerName.equals("")){
			//���ɸ���ҵ������ģ����ѯ������
			condition=condition+" and ownerName like '%"+ownerName+"%' ";
		}
		//���ݱ�������
		if(repText!=null && !repText.equals("")){
			//���ɸ��ݱ�������ģ����ѯ������
			condition=condition+" and repText like '%"+repText+"%' ";
		}
		//���ݱ���ʱ��
		if(repTime!=null && !repTime.equals("")){
			//���ɸ��ݱ���ʱ��ģ����ѯ������
			condition=condition+" and repTime like '%"+repTime+"%' ";
		}
		
		//�������Կͻ��˵�datagrid����Ĵ��ݹ�����page��rows����
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		
		//�������ݲ�ִ�з�ҳ��ѯ
		RepairsDao repairsDao=new RepairsDaoImpl();
		//��õ�ǰҳ�����ݼ���
		List<Repairs> repairsList=repairsDao.findByPage(rows, page,condition);
		//��ѯ��complain����ܼ�¼��
		int totalRows=repairsDao.count(condition);
		
		//����ӳ�伯�϶���
		Map<String, Object> mapData = new HashMap<String, Object>();
		//�����ݱ��浽map������
		mapData.put("total", totalRows);
		mapData.put("rows", repairsList);
		//����Gson����
		Gson gson = new Gson();
		//ͨ��Gson����Map����ת����json���ݸ�ʽ
		String jsonData = gson.toJson(mapData);
		//��json����������ͻ���
		out.println(jsonData);
		//�ر���
		out.close();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
