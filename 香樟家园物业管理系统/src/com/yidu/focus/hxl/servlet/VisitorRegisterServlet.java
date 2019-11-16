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
import com.yidu.focus.hxl.dao.VisitorRegisterDao;
import com.yidu.focus.hxl.dao.impl.VisitorRegisterDaoImpl;
import com.yidu.focus.hxl.domain.VisitorRegister;

/**
 * 
 * ���ܣ����õǼǱ� 
 * ���ߣ���ϡ��
 * ���ڣ�2019��10��31������9:09:11
 * �汾��1.0
 */
@WebServlet("/VisitorRegisterServlet")
public class VisitorRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisitorRegisterServlet() {
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
		String visitorIdStr=request.getParameter("visitorIdStr");
		
		//���ַ������а�","(����)�ָ�����ַ�������
		String[] visitorIds=visitorIdStr.split(",");

		//�������ݲ��������
		VisitorRegisterDao visitorRegisterdao=new VisitorRegisterDaoImpl();

		//����������ɾ�����п���ʧ�ܣ��˴�ʹ���쳣������ʵ��
		try{
			//ʹ��ѭ����������ɾ��
			for(int i=0;i<visitorIds.length;i++){
				//��ȡ��ǰ���ַ���
				String tvisitorIdStr=visitorIds[i];
				//���ַ������ת��Ϊ���͵����
				int visitorId=Integer.parseInt(tvisitorIdStr);
				//ִ��ɾ������
				visitorRegisterdao.deleteById(visitorId);
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
		int visitorId=Integer.parseInt(request.getParameter("visitorId"));
		String visitorName=request.getParameter("visitorName");		
		String visitorGender="";
		int sex=Integer.parseInt(request.getParameter("visitorGender"));
		if(sex==1){
			visitorGender="��";
		}else{
			visitorGender="Ů";
		}
		String visitorIdNum=request.getParameter("visitorTelphone");
		String visitorTelphone=request.getParameter("visitorIdNum");
		String vistiAddress=request.getParameter("vistiAddress");
		String vistiReason=request.getParameter("vistiReason");
		String cometime=request.getParameter("cometime");
		String leaveTime=request.getParameter("leaveTime");
		String empNo=request.getParameter("empNo");
		//���ݷ�װ�ɶ���
		VisitorRegister visitorRegister=new VisitorRegister(visitorId,visitorName, visitorGender, 
				    visitorIdNum, visitorTelphone, vistiAddress, vistiReason, cometime, leaveTime, empNo);

		//�������ݲ����ִ����ӷ���
		VisitorRegisterDao visitorRegisterDao=new VisitorRegisterDaoImpl();
		int rows=visitorRegisterDao.update(visitorRegister);
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
		String visitorName=request.getParameter("visitorName");		
		String visitorGender="";
		int sex=Integer.parseInt(request.getParameter("visitorGender"));
		if(sex==1){
			visitorGender="��";
		}else{
			visitorGender="Ů";
		}
		String visitorIdNum=request.getParameter("visitorTelphone");
		String visitorTelphone=request.getParameter("visitorIdNum");
		String vistiAddress=request.getParameter("vistiAddress");
		String vistiReason=request.getParameter("vistiReason");
		String cometime=request.getParameter("cometime");
		String leaveTime=request.getParameter("leaveTime");
		String empNo=request.getParameter("empNo");
		//���ݷ�װ�ɶ���
		VisitorRegister visitorRegister=new VisitorRegister(visitorName, visitorGender, visitorIdNum, visitorTelphone, vistiAddress, vistiReason, cometime, leaveTime, empNo);

		//�������ݲ����ִ����ӷ���
		VisitorRegisterDao visitorRegisterDao=new VisitorRegisterDaoImpl();
		int rows=visitorRegisterDao.add(visitorRegister);
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
		String cometime=request.getParameter("cometime");
		String visitorName=request.getParameter("visitorName");
		String vistiAddress=request.getParameter("vistiAddress");
		//���ɲ�ѯ����
		//select * from ���� where ���� limit n1,n2 
		//where �ֶ���=? and �ֶ���=? and �ֶ���=?
		String condition=" where 1=1 ";
		//���������ݽ����жϲ���ϳɲ�ѯ����
		if(cometime!=null && !cometime.equals("")){
			condition=condition+"and cometime like '%"+cometime+"%' ";
		}
		if(visitorName!=null && !visitorName.equals("")){
			condition=condition+"and visitorName like '%"+visitorName+"%' ";
		}
		if(vistiAddress!=null && !vistiAddress.equals("")){
			condition=condition+"and vistiAddress like '%"+vistiAddress+"%' ";
		}
		
		//�������Կͻ��˵�datagrid����Ĵ��ݹ�����page��rows����
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));

		//�������ݲ�ִ�з�ҳ��ѯ
		VisitorRegisterDao visitorRegisterdao=new VisitorRegisterDaoImpl();
		//��õ�ǰҳ�����ݼ���
		List<VisitorRegister> visitorRegisterList=visitorRegisterdao.findByPage(rows, page, condition);

		//��ѯ��visitorRegister����ܼ�¼��
		int totalRows=visitorRegisterdao.count();
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", visitorRegisterList);
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
