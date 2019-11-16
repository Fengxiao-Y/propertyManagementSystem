package com.yidu.focus.ll.servlet;

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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.yidu.focus.hj.dao.HouseInformationDao;
import com.yidu.focus.hj.dao.impl.HouseInformationDaoImpl;
import com.yidu.focus.hj.domain.HouseInformation;
import com.yidu.focus.ll.dao.ParkingInformationDao;
import com.yidu.focus.ll.dao.impl.ParkingInformationDaoImpl;
import com.yidu.focus.ll.domain.ParkingInformation;
import com.yidu.focus.wzh.domain.Users;

/**
 * 
 * ���ܣ���λ��Ϣ 
 * ���ߣ�����
 * ���ڣ�2019��10��31������9:11:19
 * �汾��1.0
 */
@WebServlet("/ParkingInformationServlet")
public class ParkingInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ParkingInformationServlet() {

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
		}else if("findByName".equals(method)){
			this.findByName(request,response);
		}
		
	}

	private void findByName(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		//�������������
		PrintWriter out=response.getWriter();
		//����session����
		HttpSession session=request.getSession();
		//ȡ��session�еĶ���
		Users users=(Users)session.getAttribute("users");
		//ȡ������
		String ownerName=users.getuName();
		//�������ݲ�����
		HouseInformationDao houseInformationDao = new HouseInformationDaoImpl();
		//ͨ��ҵ�����ֻ�ȡ��ҵ����Ϣ����
		HouseInformation houseInformation=houseInformationDao.getHouseInformationByName(ownerName);
		//�õ�����
		String houseId=houseInformation.getHouseId();		
		//�������ݲ����
		ParkingInformationDao parkingInformationDao=new ParkingInformationDaoImpl();
		//�������ݲ㷽����ȡ����
		ParkingInformation parkingInformation=parkingInformationDao.findByName(houseId);
		//����һ�����϶���
		List<Object> list=new ArrayList<>();
		//�����󱣴浽������
		list.add(houseInformation);
		list.add(parkingInformation);
		//����Gson����
		Gson gson=new Gson();
		//���л�����
		String park=gson.toJson(list);		
		//������ͻ���
		out.print(park);
		//�ر������
		out.close();
	}

	/**
	 * ��������ɾ������
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException �׳��쳣
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		//��ȡ�������������ݹ���������ַ���
		String parkIdStr=request.getParameter("parkIdStr");
		//���ַ������а�","(����)�ָ�����ַ�������
		String[] parkIds=parkIdStr.split(",");
		//�������ݲ��������
		ParkingInformationDao parkingInformationdao=new ParkingInformationDaoImpl();
		//����������ɾ�����п���ʧ�ܣ��˴�ʹ���쳣������ʵ��
		try{
			//ʹ��ѭ����������ɾ��
			for(int i=0;i<parkIds.length;i++){
				//��ȡ��ǰ���ַ���
				String parkId=parkIds[i];
				//ִ��ɾ������
				parkingInformationdao.delete(parkId);
			}
			out.print("success");
		}catch(Exception e){
			out.print("failure");
		}
		//�ر������
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
		String parkId=request.getParameter("parkId");
		//״̬
		String parkStatus=request.getParameter("parkStatus");	
		//����
		double parkMoney=Double.parseDouble(request.getParameter("parkMoney"));
		//����
		String houseId=request.getParameter("houseId");
		//���ݷ�װ�ɶ���
		ParkingInformation parkingInformation=new ParkingInformation(parkId, parkStatus, parkMoney, houseId);
		//�������ݲ����ִ����ӷ���
		ParkingInformationDao parkingInformationdao=new ParkingInformationDaoImpl();
		int rows=parkingInformationdao.update(parkingInformation);
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
	 * ���
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException �׳��쳣
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���������
		PrintWriter out=response.getWriter();
		//�������Կͻ��˵�����
		//���
		String parkId=request.getParameter("parkId");
		//״̬
		String parkStatus=request.getParameter("parkStatus");	
		//����
		double parkMoney=Double.parseDouble(request.getParameter("parkMoney"));
		//����
		String houseId=request.getParameter("houseId");

		//���ݷ�װ�ɶ���
		ParkingInformation parkingInformation=new ParkingInformation(parkId, parkStatus, parkMoney, houseId);

		//�������ݲ����ִ����ӷ���
		ParkingInformationDao parkingInformationdao=new ParkingInformationDaoImpl();
		int rows=parkingInformationdao.add(parkingInformation);
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
		String parkId=request.getParameter("parkId");
		
		//���ɲ�ѯ����
		String condition=" where 1=1 ";
		//���ݱ�Ž���ģ����ѯ
		if(parkId!=null && !parkId.equals("")){
			condition=condition+"and parkId like '%"+parkId+"%' ";
		}		
		//�������Կͻ��˵�datagrid����Ĵ��ݹ�����page��rows����
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));

		//�������ݲ�ִ�з�ҳ��ѯ
		ParkingInformationDao parkingInformationdao=new ParkingInformationDaoImpl();
		//��õ�ǰҳ�����ݼ���
		List<ParkingInformation> parkingInformationlist=parkingInformationdao.findByPage(rows, page, condition);

		//��ѯ��ParkingInformation����ܼ�¼��
		int totalRows=parkingInformationdao.count();
		//����ӳ�伯��
		Map<String, Object> mapData = new HashMap<String, Object>();
		//�����ݱ��浽map������
		mapData.put("total", totalRows);
		mapData.put("rows", parkingInformationlist);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
