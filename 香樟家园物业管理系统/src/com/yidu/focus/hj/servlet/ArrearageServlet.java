package com.yidu.focus.hj.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.yidu.focus.hj.dao.ArrearageDao;
import com.yidu.focus.hj.dao.CarArrearageDao;
import com.yidu.focus.hj.dao.IncomeDao;
import com.yidu.focus.hj.dao.RecordDao;
import com.yidu.focus.hj.dao.impl.ArrearageDaoImpl;
import com.yidu.focus.hj.dao.impl.CarArrearageDaoImpl;
import com.yidu.focus.hj.dao.impl.IncomeDaoImpl;
import com.yidu.focus.hj.dao.impl.RecordDaoImpl;
import com.yidu.focus.hj.domain.Arrearage;
import com.yidu.focus.hj.domain.CarArrearage;
import com.yidu.focus.hj.domain.Income;
import com.yidu.focus.hj.domain.Record;
import com.yidu.focus.wzh.domain.Users;

/**
 * Servlet implementation class ArrearageServlet
 */
@WebServlet("/ArrearageServlet")
public class ArrearageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArrearageServlet() {
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
		}else if(method.equals("findByName")){
			this.findByName(request,response);
		}else if(method.equals("pay")){
			this.pay(request,response);
		}else if(method.equals("jiaofei")){
			this.jiaofei(request,response);
		}
	}


	private void jiaofei(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�������������
		PrintWriter out = response.getWriter();
		String houseId = request.getParameter("houseId");
		String ownerName = request.getParameter("ownerName");
		String ownerTelphone = request.getParameter("ownerTelphone");		
		//�ı�״̬���ѽ��塱
		String state="�ѽ���";
		//Ƿ�ѽ����0
		double arrearageMoney=0.0;
		//��ȡ��ǰϵͳʱ��
		Date date = new Date();
		//��ϵͳʱ��ת��Ϊ���ݿ��ʽ
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String arrearageDate=sdf.format(date);
		//��װ
		Arrearage arrearage=new Arrearage(houseId, ownerName, ownerTelphone, state, arrearageMoney, arrearageDate);
		//������ҵǷ�Ѷ���
		ArrearageDao arreargaDao=new ArrearageDaoImpl();
		//�����޸ķ���
		int rows=arreargaDao.update(arrearage);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		
		/*�����ҵ�ѵĽ��ɣ���ɷ������¼���������*/
		//����ɷ��������ݲ����
		RecordDao recordDao=new RecordDaoImpl();
		//�õ�����
		String recordType="��ҵ��";
		//�õ��ɷѽ��
		double recordMoney=Double.parseDouble(request.getParameter("arrearageMoney"));
		//�ɷ�ʱ��
		String recordDate=arrearageDate;
		//��װ
		Record record1 = new Record(ownerName, ownerTelphone, recordType, recordMoney, recordDate);
		recordDao.add(record1);
		
		
		/*���ܵ����뱨��*/
		//�õ�������Դ
		String source=recordType;
		//�õ��ɷѽ��
		double money=recordMoney;
		//��Դʱ��
		String source_date=arrearageDate;
		//��װ
		Income income=new Income(source, money, source_date);
		//�����������
		IncomeDao incomDao=new IncomeDaoImpl();
		//��ӵ�income����
		incomDao.add(income);
		//�ر������
		out.close();						
	}
	
	/**
	 * �ͻ��˽ɷѹ���
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException IO�쳣
	 */
	private void pay(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���������������
		PrintWriter out = response.getWriter();
		//����session����
		HttpSession session=request.getSession();
		//ȡ��session�еĶ���
		Users users=(Users)session.getAttribute("users");
		//ȡ������
		String ownerName=users.getuName();
		//������ҵ��Ƿ�ѱ����ݲ����Ӷ���
		ArrearageDao arreargeDao=new ArrearageDaoImpl();
		//�������ݲ㷽�����õ�����
		Arrearage arrearage=arreargeDao.getArrearageByName(ownerName);
		//��ȡ����
		String houseId=arrearage.getHouseId();
		String ownerTelphone=arrearage.getOwnerTelphone();
		String state="�ѽ���";
		Double arrearageMoney=0.0;
		
		//��ȡ��ǰϵͳʱ��
		Date date = new Date();
		//��ϵͳʱ��ת��Ϊ���ݿ��ʽ
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String arrearageDate=sdf.format(date);
		//��װ
		Arrearage arrea = new Arrearage(houseId, ownerName, ownerTelphone, state, arrearageMoney, arrearageDate);
		//�����޸ĵķ���
		int rows = arreargeDao.update(arrea);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		
		//����Ƿ����ϸ������ݲ����
		RecordDao recordDao=new RecordDaoImpl();
		
		String recordType="��ҵ��";
		Double recordMoney=arrearage.getArrearageMoney();
		String recordDate=arrearageDate;
		//��װ����
		Record record=new Record(ownerName, ownerTelphone, recordType, recordMoney, recordDate);
		//������ӵķ���
		recordDao.add(record);
		//�������������������ӽ�ȥ
		String source = recordType;
		double money = recordMoney;
		String source_date = arrearageDate;
		Income income = new Income(source, money, source_date);
		IncomeDao incomeDao = new IncomeDaoImpl();
		incomeDao.add(income);
		out.close();
		
	}

	private void findByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���������������
		PrintWriter out = response.getWriter();
		//����session����
		HttpSession session=request.getSession();
		//ȡ��session�еĶ���
		Users users=(Users)session.getAttribute("users");
		//ȡ������
		String ownerName=users.getuName();
		//������ҵ��Ƿ�ѱ����ݲ����Ӷ���
		ArrearageDao arreargeDao=new ArrearageDaoImpl();
		//�������ݲ㷽�����õ�����
		Arrearage arrearage=arreargeDao.getArrearageByName(ownerName);
		
		//����̶�ͣ�������ݲ����
		CarArrearageDao carArrearageDao=new CarArrearageDaoImpl();
		//���ð����ֲ�ѯ����
		CarArrearage carArrearage=carArrearageDao.getCarArrearageByName(ownerName);
		//����һ������
		List<Object> list=new ArrayList<>();
		list.add(arrearage);
		list.add(carArrearage);
		//����Gson����
		Gson gson=new Gson();
		//���л�
		String arrea=gson.toJson(list);
		out.print(arrea);
		out.close();
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���������������
		PrintWriter out = response.getWriter();
		//����������ҳ����
		String houseIdStr = request.getParameter("houseIdStr");
		//���ַ�����"��"�ָ�
		String[] houseIdS = houseIdStr.split(",");
		//�������ݲ��������
		ArrearageDao arrearageDao = new ArrearageDaoImpl();
		try {
			for(int i = 0;i<houseIdS.length;i++){
				String houseId=houseIdS[i];
				arrearageDao.delete(houseId);
				
			}
			out.print("success");
		} catch (Exception e) {
			out.print("failure");
		}
		out.close();
		
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String houseId = request.getParameter("houseId");
		String ownerName = request.getParameter("ownerName");
		String ownerTelphone = request.getParameter("ownerTelphone");
		String state = request.getParameter("state");
		double arrearageMoney = Double.parseDouble(request.getParameter("arrearageMoney"));
		String arrearageDate = request.getParameter("arrearageDate");
		ArrearageDao arrearageDao = new ArrearageDaoImpl();
		Arrearage arrearage = new Arrearage(houseId, ownerName, ownerTelphone, state, arrearageMoney, arrearageDate);
		int rows = arrearageDao.update(arrearage);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		out.close();
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String houseId = request.getParameter("houseId");
		String ownerName = request.getParameter("ownerName");
		String ownerTelphone = request.getParameter("ownerTelphone");
		String state = request.getParameter("state");
		double arrearageMoney = Double.parseDouble(request.getParameter("arrearageMoney"));
		String arrearageDate = request.getParameter("arrearageDate");
		ArrearageDao arrearageDao = new ArrearageDaoImpl();
		Arrearage arrearage = new Arrearage(houseId, ownerName, ownerTelphone, state, arrearageMoney, arrearageDate);
		int rows = arrearageDao.add(arrearage);
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
		String houseId = request.getParameter("houseId");
		String ownerName = request.getParameter("ownerName");
		String state = request.getParameter("state");
		String condition = " where 1=1 ";
		//���������ݽ����жϲ���ϳɲ�ѯ����
		if(houseId!=null&&!houseId.equals("")){
			condition=condition+" and houseId like '%"+houseId+"%' ";
		}
		if(ownerName!=null&&!ownerName.equals("")){
			condition = condition+" and ownerName like '%"+ownerName+"%' ";
		}
		if(state!=null&&!state.equals("")){
			condition = condition+" and state like '%"+state+"%' ";
		}
		//�������Կͻ��˴��ݹ�����page��rows����
		int rows = Integer.parseInt(request.getParameter("rows"));
		int page = Integer.parseInt(request.getParameter("page"));
		//�������ݲ�����
		ArrearageDao arrearageDao = new ArrearageDaoImpl();
		//�������ݲ�ִ�з�ҳ��ѯ����������浽������
		List<Arrearage> arrearageList = arrearageDao.findByPage(rows, page, condition);
		int totalRows = arrearageDao.count(condition);
		//����ӳ�伯�϶���
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", arrearageList);
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
