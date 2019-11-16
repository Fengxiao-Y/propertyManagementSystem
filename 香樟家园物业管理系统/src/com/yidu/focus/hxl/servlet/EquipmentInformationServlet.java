package com.yidu.focus.hxl.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yidu.focus.hxl.dao.EquipmentInformationDao;
import com.yidu.focus.hxl.dao.impl.EquipmentInformationDaoImpl;
import com.yidu.focus.hxl.domain.EquipmentInformation;

/**
 * 
 * ���ܣ��豸������Ϣ 
 * ���ߣ���ϣ��
 * ���ڣ�2019��10��31������9:08:04
 * �汾��1.0
 */
@WebServlet("/EquipmentInformationServlet")
public class EquipmentInformationServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EquipmentInformationServlet() {
		super();
		
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
		String eqIdStr=request.getParameter("eqIdStr");
		//���ַ������а�","(����)�ָ�����ַ�������
		String[] eqIds=eqIdStr.split(",");
		//�������ݲ��������
		EquipmentInformationDao equipmentInformationdao=new EquipmentInformationDaoImpl();

		//����������ɾ�����п���ʧ�ܣ��˴�ʹ���쳣������ʵ��
		try{
			//ʹ��ѭ����������ɾ��
			for(int i=0;i<eqIds.length;i++){
				//��ȡ��ǰ���ַ���
				String eqId=eqIds[i];
				//ִ��ɾ������
				equipmentInformationdao.deleteById(eqId);
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
		//���������
		PrintWriter out=response.getWriter();
		//�������Կͻ��˵�����
		String eqId=request.getParameter("eqId");
		String eqName=request.getParameter("eqName");	
		String eqPurpose=request.getParameter("eqPurpose");
		String manufacturer=request.getParameter("manufacturer");
		String producedDate=request.getParameter("producedDate");
		int eqNum=Integer.parseInt(request.getParameter("eqNum"));
		String usePosition=request.getParameter("usePosition");
		String eqStatus=request.getParameter("eqStatus");

		//���ݷ�װ�ɶ���
		EquipmentInformation EquipmentInformation=new EquipmentInformation(eqId, eqName, eqPurpose, manufacturer, producedDate, eqNum, usePosition, eqStatus);

		//�������ݲ����ִ����ӷ���
		EquipmentInformationDao equipmentInformationdao=new EquipmentInformationDaoImpl();
		int rows=equipmentInformationdao.update(EquipmentInformation);
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
		//���������
		PrintWriter out=response.getWriter();

		//�������Կͻ��˵�����
		String eqId=request.getParameter("eqId");
		String eqName=request.getParameter("eqName");	
		String eqPurpose=request.getParameter("eqPurpose");
		String manufacturer=request.getParameter("manufacturer");
		String producedDate=request.getParameter("producedDate");
		int eqNum=Integer.parseInt(request.getParameter("eqNum"));
		String usePosition=request.getParameter("usePosition");
		String eqStatus=request.getParameter("eqStatus");

		//���ݷ�װ�ɶ���
		EquipmentInformation EquipmentInformation=new EquipmentInformation(eqId, eqName, eqPurpose, manufacturer, producedDate, eqNum, usePosition, eqStatus);

		//�������ݲ����ִ����ӷ���
		EquipmentInformationDao equipmentInformationdao=new EquipmentInformationDaoImpl();
		int rows=equipmentInformationdao.add(EquipmentInformation);
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
		String eqId=request.getParameter("eqId");
        String eqName=request.getParameter("eqName");
        String manufacturer=request.getParameter("manufacturer");
		//���ɲ�ѯ����
		//select * from ���� where ���� limit n1,n2 
		//where �ֶ���=? and �ֶ���=? and �ֶ���=?
		String condition=" where 1=1 ";
		//���������ݽ����жϲ���ϳɲ�ѯ����
		if(eqId!=null && !eqId.equals("")){
			condition=condition+"and eqId like '%"+eqId+"%' ";
		}
		
		if(eqName!=null && !eqName.equals("")){
			condition=condition+"and eqName like '%"+eqName+"%' ";
		}
		
		if(manufacturer!=null && !manufacturer.equals("")){
			condition=condition+"and manufacturer like '%"+manufacturer+"%' ";
		}
		
		//�������Կͻ��˵�datagrid����Ĵ��ݹ�����page��rows����
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));


		//�������ݲ�ִ�з�ҳ��ѯ
		EquipmentInformationDao equipmentInformationdao=new EquipmentInformationDaoImpl();
		//��õ�ǰҳ�����ݼ���
		List<EquipmentInformation> equipmentInformationlist=equipmentInformationdao.findByPage(rows, page, condition);

		//��ѯ��ParkingInformation����ܼ�¼��
		int totalRows=equipmentInformationdao.count();
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", equipmentInformationlist);
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
