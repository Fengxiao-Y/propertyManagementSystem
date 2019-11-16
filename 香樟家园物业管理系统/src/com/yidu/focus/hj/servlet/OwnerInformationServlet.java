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
import com.yidu.focus.hj.dao.OwnerInformationDao;
import com.yidu.focus.hj.dao.impl.OwnerInformationDaoImpl;
import com.yidu.focus.hj.domain.OwnerInformation;

/**
 * 
 * ���ܣ�ҵ����Ϣ
 * ��д�ߣ�����
 * �汾��1.0
 * ���ڣ�2019��10��21������10:58:48
 */
@WebServlet("/OwnerInformationServlet")
public class OwnerInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OwnerInformationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���������ַ�������
		request.setCharacterEncoding("utf-8");
		//������Ӧ��������
		response.setContentType("text/html");
		//������Ӧ�ַ�������
		response.setCharacterEncoding("utf-8");
		//����������ҳ ����
		String method = request.getParameter("method");
		//�ж�
		if(method.equals("findAll")){
			this.findAll(request,response);
		}else if(method.equals("add")){
			this.add(request,response);
		}else if(method.equals("update")){
			this.update(request,response);
		}else if(method.equals("delete")){
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
		//���������������
		PrintWriter out = response.getWriter();
		//����������ҳ����
		String ownerIdStr = request.getParameter("ownerIdStr");
		//���ַ�����"��"�ָ�
		String[] ownerIdS = ownerIdStr.split(",");
		//�������ݲ��������
		OwnerInformationDao ownerInformationDao = new OwnerInformationDaoImpl();
		try {
			for(int i = 0;i<ownerIdS.length;i++){
				String ownerId=ownerIdS[i];
				ownerInformationDao.delete(ownerId);
			
			}
			out.print("success");
		} catch (Exception e) {
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
		//�������������
		PrintWriter out = response.getWriter();
		//����������ҳ����
		String ownerId = request.getParameter("ownerId");
		String houseId = request.getParameter("houseId");
		String ownerName = request.getParameter("ownerName");
		String ownerSex = request.getParameter("ownerSex");
		String ownerIdcard = request.getParameter("ownerIdcard");
		String ownerTelphone = request.getParameter("ownerTelphone");
		String ownerEmail = request.getParameter("ownerEmail");
		String ownerAddress = request.getParameter("ownerAddress");
		//ҵ����
		//����ҵ�����󱣴�����
		OwnerInformation ownerInformation = new OwnerInformation(ownerId, houseId, ownerName, ownerSex, ownerIdcard, ownerTelphone, ownerEmail, ownerAddress);
		//�������ݿ����Ӷ���
		OwnerInformationDao ownerInformationDao = new OwnerInformationDaoImpl();
		//������ӷ��������շ���ֵ
		int rows = ownerInformationDao.update(ownerInformation);
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
		//�������������
		PrintWriter out = response.getWriter();
		//����������ҳ����
		String ownerId = request.getParameter("ownerId");
		String houseId = request.getParameter("houseId");
		String ownerName = request.getParameter("ownerName");
		String ownerSex = request.getParameter("ownerSex");
		String ownerIdcard = request.getParameter("ownerIdcard");
		String ownerTelphone = request.getParameter("ownerTelphone");
		String ownerEmail = request.getParameter("ownerEmail");
		String ownerAddress = request.getParameter("ownerAddress");
		//ҵ����
		//����һ��ҵ������
		OwnerInformation ownerInformation = new OwnerInformation(ownerId, houseId, ownerName, ownerSex, ownerIdcard, ownerTelphone, ownerEmail, ownerAddress);
		//�������ݲ����Ӷ���
		OwnerInformationDao ownerInformationDao = new OwnerInformationDaoImpl();
		//������ӷ��������շ���ֵ
		int rows = ownerInformationDao.add(ownerInformation);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		out.close();
		
		
	}
	/**
	 * ��ѯ����
	 * @param request ����
	 * @param response ��Ӧ
	 * @throws IOException �쳣
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//����������Զ���
		PrintWriter out = response.getWriter();
		//����������ҳ����
		String ownerId = request.getParameter("ownerId");
		String houseId = request.getParameter("houseId");
		String ownerName = request.getParameter("ownerName");
		String condition = " where 1=1 ";
		if(ownerId!=null&&!ownerId.equals("")){
			condition=condition+" and ownerId like '%"+ownerId+"%' ";
		}
		if(houseId!=null&&!houseId.equals("")){
			condition=condition+" and houseId like '%"+houseId+"%' ";
		}
		if(ownerName!=null&&!ownerName.equals("")){
			condition=condition+" and ownerName like '%"+ownerName+"%' ";
		}
		//����������ҳ���ݵ�page��rows����
		int rows = Integer.parseInt(request.getParameter("rows"));
		int page = Integer.parseInt(request.getParameter("page"));
		//�������ݲ����Ӷ���
		OwnerInformationDao ownerInformationDao = new OwnerInformationDaoImpl();
		//�������ݲ�ִ�з�ҳ��ѯ����������浽���� ��
		List<OwnerInformation> ownerInformationList = ownerInformationDao.findByPage(rows, page, condition);
		int totalRows = ownerInformationDao.count(condition);
		//����ӳ�伯�϶���
		Map<String, Object> mapData = new HashMap<>();
		mapData.put("total", totalRows);
		mapData.put("rows", ownerInformationList);
		Gson gson = new Gson();
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
