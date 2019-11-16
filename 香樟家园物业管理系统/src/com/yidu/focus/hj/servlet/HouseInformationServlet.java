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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.yidu.focus.hj.dao.HouseInformationDao;
import com.yidu.focus.hj.dao.LesseeInformationDao;
import com.yidu.focus.hj.dao.OwnerInformationDao;
import com.yidu.focus.hj.dao.impl.HouseInformationDaoImpl;
import com.yidu.focus.hj.dao.impl.LesseeInformationDaoImpl;
import com.yidu.focus.hj.dao.impl.OwnerInformationDaoImpl;
import com.yidu.focus.hj.domain.HouseInformation;
import com.yidu.focus.hj.domain.LesseeInformation;
import com.yidu.focus.hj.domain.OwnerInformation;
import com.yidu.focus.wzh.domain.Users;

/**
 * 
 * ���ܣ�������Ϣ
 * ��д�ߣ�����
 * �汾��1.0
 * ���ڣ�2019��10��21������10:58:48
 */
@WebServlet("/HouseInformationServlet")
public class HouseInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HouseInformationServlet() {
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
		//����������ҳ����
		String method = request.getParameter("method");
		if(method.equals("findAll")){
			findAll(request,response);
		}else if(method.equals("add")){
			add(request,response);
		}else if(method.equals("update")){
			update(request,response);
		}else if(method.equals("delete")){
			delete(request,response);
		}else if(method.equals("findByName")){
			findByName(request,response);
		}else if(method.equals("lease")){
			lease(request,response);
		}else if(method.equals("alteration")){
			alteration(request,response);
		}
	}
	/**
	 * ��Ȩ���
	 * @param request ����
	 * @param response ��Ӧ
	 * @throws IOException �쳣
	 */
	private void alteration(HttpServletRequest request, HttpServletResponse response) throws IOException {

		//�������������
		PrintWriter out = response.getWriter();
		//����������ҳ����
		String houseId = request.getParameter("houseId");
		String ownerName = request.getParameter("ownerName");
		String name = request.getParameter("name");
		String houseType = request.getParameter("houseType");
		double houseArea = Double.parseDouble(request.getParameter("houseArea"));
		String houseNature = request.getParameter("houseNature");
		String houseState = "����";
		String houseAddress = request.getParameter("houseAddress");
		String ownerId = request.getParameter("ownerId");
		String ownerSex = request.getParameter("ownerSex");
		String ownerIdcard = request.getParameter("ownerIdcard");
		String ownerTelphone = request.getParameter("ownerTelphone");
		String ownerEmail = request.getParameter("ownerEmail");
		String ownerAddress = request.getParameter("ownerAddress");
		//ҵ����
		//�������ݶ��󱣴�����
		HouseInformationDao houseInformationDao = new HouseInformationDaoImpl();
		OwnerInformationDao ownerInformationDao = new OwnerInformationDaoImpl();
		
		HouseInformation houseInformation = new HouseInformation(houseId, ownerName, houseType, houseArea, houseNature, houseState, houseAddress);
		//�������ݲ���ӷ��������շ���ֵ
		
		//�������ݶ��󱣴�����
		OwnerInformation ownerInformation = new OwnerInformation(ownerId, houseId, ownerName, ownerSex, ownerIdcard, ownerTelphone, ownerEmail, ownerAddress);
		//�������ݲ���ӷ��������շ���ֵ
		ownerInformationDao.add(ownerInformation);
		int rows = houseInformationDao.update(houseInformation);
		ownerInformationDao.deleteName(name);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		out.close();
	}
	/**
	 * ����
	 * @param request ����
	 * @param response ��Ӧ
	 * @throws IOException �쳣
	 */
	private void lease(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�������������
		PrintWriter out = response.getWriter();
		//����������ҳ����
		String houseId = request.getParameter("houseId");
		String ownerName = request.getParameter("ownerName");
		String houseType = request.getParameter("houseType");
		double houseArea = Double.parseDouble(request.getParameter("houseArea"));
		String houseNature = request.getParameter("houseNature");
		String houseState = "����";
		String houseAddress = request.getParameter("houseAddress");
		String lesseeName = request.getParameter("lesseeName");
		String lesseeGender = request.getParameter("lesseeGender");
		String lesseeIdcard = request.getParameter("lesseeIdcard");
		String lesseeTelphone = request.getParameter("lesseeTelphone");
		String startTime = request.getParameter("startTime");
		String endTime =request.getParameter("endTime");
		//ҵ����
		//�������ݶ��󱣴�����
		HouseInformationDao houseInformationDao = new HouseInformationDaoImpl();
		LesseeInformationDao lesseeInformationDao = new LesseeInformationDaoImpl();
		
		HouseInformation houseInformation = new HouseInformation(houseId, ownerName, houseType, houseArea, houseNature, houseState, houseAddress);
		//�������ݲ���ӷ��������շ���ֵ
		
		//�������ݶ��󱣴�����
		LesseeInformation lesseeInformation = new LesseeInformation(houseId, lesseeName, lesseeGender, lesseeIdcard, lesseeTelphone, startTime, endTime);
		
		//�������ݲ���ӷ��������շ���ֵ
		lesseeInformationDao.add(lesseeInformation);
		int rows = houseInformationDao.update(houseInformation);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		out.close();
		
	}
	/**
	 * ͨ�����ֲ�ѯ
	 * @param request ����
	 * @param response ��Ӧ
	 * @throws IOException �쳣
	 */
	private void findByName(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//���������
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
		//����Gson����
		Gson gson=new Gson();
		//���л�
		String house=gson.toJson(houseInformation);
		out.print(house);
		
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
		String houseIdStr = request.getParameter("houseIdStr");
		//���ַ�����"��"�ָ�
		String[] houseIdS = houseIdStr.split(",");
		//�������ݲ��������
		HouseInformationDao houseInformationDao = new HouseInformationDaoImpl();
		try {
			for(int i = 0;i<houseIdS.length;i++){
				String houseId=houseIdS[i];
				houseInformationDao.delete(houseId);
				
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
		String houseId = request.getParameter("houseId");
		String ownerName = request.getParameter("ownerName");
		String houseType = request.getParameter("houseType");
		double houseArea = Double.parseDouble(request.getParameter("houseArea"));
		String houseNature = request.getParameter("houseNature");
		String houseState = request.getParameter("houseState");
		String houseAddress = request.getParameter("houseAddress");
		//ҵ����
		//�������ݲ����Ӷ���
		if(!houseState.equals("����")){
			LesseeInformationDao lesseeInformationDao = new LesseeInformationDaoImpl();
			lesseeInformationDao.deleteA(houseId);
		}
		HouseInformationDao houseInformationDao = new HouseInformationDaoImpl();
		//�������ݶ��󱣴�����
		HouseInformation houseInformation = new HouseInformation(houseId, ownerName, houseType, houseArea, houseNature, houseState, houseAddress);
		//�������ݲ���ӷ��������շ���ֵ
		int rows = houseInformationDao.update(houseInformation);
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
		String houseId = request.getParameter("houseId");
		String ownerName = request.getParameter("ownerName");
		String houseType = request.getParameter("houseType");
		double houseArea = Double.parseDouble(request.getParameter("houseArea"));
		String houseNature = request.getParameter("houseNature");
		String houseState = request.getParameter("houseState");
		String houseAddress = request.getParameter("houseAddress");
		//ҵ����
		//�������ݲ����Ӷ���
		HouseInformationDao houseInformationDao = new HouseInformationDaoImpl();
		//�������ݶ��󱣴�����
		HouseInformation houseInformation = new HouseInformation(houseId, ownerName, houseType, houseArea, houseNature, houseState, houseAddress);
		//�������ݲ���ӷ��������շ���ֵ
		int rows = houseInformationDao.add(houseInformation);
		//�жϷ���ֵ
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
		PrintWriter out = response.getWriter();
		//����������ҳ����
		String houseId = request.getParameter("houseId");
		String ownerName = request.getParameter("ownerName");
		String houseType = request.getParameter("houseType");
		String houseNature = request.getParameter("houseNature");
		String houseState = request.getParameter("houseState");
		String condition = " where 1=1 ";
		//���������ݽ����жϲ���ϳɲ�ѯ����
		if(houseId!=null&&!houseId.equals("")){
			condition=condition+" and houseId like '%"+houseId+"%' ";
		}
		if(ownerName!=null&&!ownerName.equals("")){
			condition = condition+" and ownerName like '%"+ownerName+"%' ";
		}
		if(houseType!=null&&!houseType.equals("")){
			condition = condition+" and houseType like '%"+houseType+"%' ";
		}
		if(houseNature!=null&&!houseNature.equals("")){
			condition = condition+" and houseNature like '%"+houseNature+"%' ";
		}
		if(houseState!=null&&!houseState.equals("")){
			condition = condition+" and houseState like '%"+houseState+"%' ";
		}
		//�������Կͻ��˴��ݹ�����page��rows����
		int rows = Integer.parseInt(request.getParameter("rows"));
		int page = Integer.parseInt(request.getParameter("page"));
		//�������ݲ�����
		HouseInformationDao houseInformationDao = new HouseInformationDaoImpl();
		//�������ݲ�ִ�з�ҳ��ѯ����������浽������
		List<HouseInformation> houseInformationsList = houseInformationDao.findByPage(rows, page, condition);
		int totalRows = houseInformationDao.count(condition);
		//����ӳ�伯�϶���
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", houseInformationsList);
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
