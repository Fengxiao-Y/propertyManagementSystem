package com.yidu.focus.hj.servlet;

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
import com.yidu.focus.hj.dao.CarArrearageDao;
import com.yidu.focus.hj.dao.IncomeDao;
import com.yidu.focus.hj.dao.RecordDao;
import com.yidu.focus.hj.dao.impl.CarArrearageDaoImpl;
import com.yidu.focus.hj.dao.impl.IncomeDaoImpl;
import com.yidu.focus.hj.dao.impl.RecordDaoImpl;
import com.yidu.focus.hj.domain.CarArrearage;
import com.yidu.focus.hj.domain.Income;
import com.yidu.focus.hj.domain.Record;
import com.yidu.focus.wzh.domain.Users;

/**
 * Servlet implementation class CarArrearageServlet
 */
@WebServlet("/CarArrearageServlet")
public class CarArrearageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarArrearageServlet() {
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
			this.findAll(request,response);
		}else if(method.equals("add")){
			this.add(request,response);
		}else if(method.equals("update")){
			this.update(request,response);
		}else if(method.equals("delete")){
			this.delete(request,response);
		}else if(method.equals("pay")){
			this.pay(request,response);
		}else if(method.equals("jiaofei")){
			this.jiaofei(request,response);
		}
	}

	

	private void jiaofei(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�������������
		PrintWriter out = response.getWriter();
		String parkId = request.getParameter("parkId");
		String ownerName = request.getParameter("ownerName");
		String ownerTelphone = request.getParameter("ownerTelphone");	
		//�ı�״̬���ѽ��塱
		String parkStatus="�ѽ���";
		//Ƿ�ѽ����0
		double carMoney=0.0;
		//��ȡ��ǰϵͳʱ��
		Date date = new Date();
		//��ϵͳʱ��ת��Ϊ���ݿ��ʽ
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String carDate=sdf.format(date);
		//��װ
		CarArrearage carArrearage=new CarArrearage(parkId, ownerName, ownerTelphone, parkStatus, carMoney, carDate);
		//����ͣ��Ƿ�Ѷ���
		CarArrearageDao carArreargaDao=new CarArrearageDaoImpl();
		//�����޸ķ���
		int rows=carArreargaDao.update(carArrearage);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		
		/*�����ҵ�ѵĽ��ɣ���ɷ������¼���������*/
		//����ɷ��������ݲ����
		RecordDao recordDao=new RecordDaoImpl();
		//�õ�����
		String recordType="ͣ����";
		//�õ��ɷѽ��
		double recordMoney=Double.parseDouble(request.getParameter("carMoney"));
		//�ɷ�ʱ��
		String recordDate=carDate;
		//��װ
		Record record1 = new Record(ownerName, ownerTelphone, recordType, recordMoney, recordDate);
		recordDao.add(record1);
		
		
		/*���ܵ����뱨��*/
		//�õ�������Դ
		String source=recordType;
		//�õ��ɷѽ��
		double money=recordMoney;
		//��Դʱ��
		String source_date=carDate;
		//��װ
		Income income=new Income(source, money, source_date);
		//�����������
		IncomeDao incomDao=new IncomeDaoImpl();
		//��ӵ�income����
		incomDao.add(income);
		//�ر������
		out.close();
		
	}

	private void pay(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���������������
		PrintWriter out = response.getWriter();
		//����session����
		HttpSession session=request.getSession();
		//ȡ��session�еĶ���
		Users users=(Users)session.getAttribute("users");
		//ȡ������
		String ownerName=users.getuName();
		//����ͣ����Ƿ�ѱ����ݲ����Ӷ���
		CarArrearageDao carArreargeDao=new CarArrearageDaoImpl();
		//�������ݲ㷽�����õ�����
		CarArrearage carArrearage=carArreargeDao.getCarArrearageByName(ownerName);
		//��λ���
		String parkId=carArrearage.getParkId();
		String ownerTelphone=carArrearage.getOwnerTelphone();
		String parkStatus="�ѽ���";
		Double carMoney=0.0;
		
		//��ȡ��ǰϵͳʱ��
		Date date = new Date();
		//��ϵͳʱ��ת��Ϊ���ݿ��ʽ
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String carDate=sdf.format(date);
		//��װ
		CarArrearage carArrea = new CarArrearage(parkId, ownerName, ownerTelphone, parkStatus, carMoney, carDate);
		//�����޸ĵķ���
		int rows = carArreargeDao.update(carArrea);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		
		//����Ƿ����ϸ������ݲ����
		RecordDao recordDao=new RecordDaoImpl();
		//��������
		String recordType="ͣ����";
		//���
		Double recordMoney=carArrearage.getCarMoney();
		//ʱ��
		String recordDate=carDate;
		//��װ����
		Record record=new Record(ownerName, ownerTelphone, recordType, recordMoney, recordDate);
		//������ӵķ���
		recordDao.add(record);
		//���������ķ���
		String source = recordType;
		double money = recordMoney;
		String source_date = recordDate;
		Income income = new Income(source, money, source_date);
		IncomeDao incomeDao = new IncomeDaoImpl();
		incomeDao.add(income);
		out.close();
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���������������
		PrintWriter out = response.getWriter();
		//����������ҳ����
		String parkIdStr = request.getParameter("parkIdStr");
		//���ַ�����"��"�ָ�
		String[] parkIdS = parkIdStr.split(",");
		//�������ݲ��������
		CarArrearageDao carArrearageDao = new CarArrearageDaoImpl();
		try {
			for(int i = 0;i<parkIdS.length;i++){
				String parkId=parkIdS[i];
				carArrearageDao.delete(parkId);
				
			}
			out.print("success");
		} catch (Exception e) {
			out.print("failure");
		}
		out.close();
		
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String parkId = request.getParameter("parkId");
		String ownerName = request.getParameter("ownerName");
		String ownerTelphone = request.getParameter("ownerTelphone");
		String parkStatus = request.getParameter("parkStatus");
		double carMoney = Double.parseDouble(request.getParameter("carMoney"));
		String carDate = request.getParameter("carDate");
		CarArrearageDao carArrearageDao = new CarArrearageDaoImpl();
		CarArrearage carArrearage = new CarArrearage(parkId, ownerName, ownerTelphone, parkStatus, carMoney, carDate);
		int rows = carArrearageDao.update(carArrearage);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		out.close();
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String parkId = request.getParameter("parkId");
		String ownerName = request.getParameter("ownerName");
		String ownerTelphone = request.getParameter("ownerTelphone");
		String parkStatus = request.getParameter("parkStatus");
		double carMoney = Double.parseDouble(request.getParameter("carMoney"));
		String carDate = request.getParameter("carDate");
		CarArrearageDao carArrearageDao = new CarArrearageDaoImpl();
		CarArrearage carArrearage = new CarArrearage(parkId, ownerName, ownerTelphone, parkStatus, carMoney, carDate);
		int rows = carArrearageDao.add(carArrearage);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		out.close();
		
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		//����������ҳ����
		String parkId = request.getParameter("parkId");
		String ownerName = request.getParameter("ownerName");
		String parkStatus = request.getParameter("parkStatus");
		String condition = " where 1=1 ";
		//���������ݽ����жϲ���ϳɲ�ѯ����
		if(parkId!=null&&!parkId.equals("")){
			condition=condition+" and parkId like '%"+parkId+"%' ";
		}
		if(ownerName!=null&&!ownerName.equals("")){
			condition = condition+" and ownerName like '%"+ownerName+"%' ";
		}
		if(parkStatus!=null&&!parkStatus.equals("")){
			condition = condition+" and parkStatus like '%"+parkStatus+"%' ";
		}
		//�������Կͻ��˴��ݹ�����page��rows����
		int rows = Integer.parseInt(request.getParameter("rows"));
		int page = Integer.parseInt(request.getParameter("page"));
		//�������ݲ�����
		CarArrearageDao carArrearageDao = new CarArrearageDaoImpl();
		//�������ݲ�ִ�з�ҳ��ѯ����������浽������
		List<CarArrearage> carArrearageList = carArrearageDao.findByPage(rows, page, condition);
		int totalRows = carArrearageDao.count(condition);
		//����ӳ�伯�϶���
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", carArrearageList);
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
