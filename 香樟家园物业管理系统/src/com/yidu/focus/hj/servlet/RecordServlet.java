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
import com.yidu.focus.hj.dao.ArrearageDao;
import com.yidu.focus.hj.dao.CarArrearageDao;
import com.yidu.focus.hj.dao.IncomeDao;
import com.yidu.focus.hj.dao.OwnerInformationDao;
import com.yidu.focus.hj.dao.RecordDao;
import com.yidu.focus.hj.dao.impl.ArrearageDaoImpl;
import com.yidu.focus.hj.dao.impl.CarArrearageDaoImpl;
import com.yidu.focus.hj.dao.impl.IncomeDaoImpl;
import com.yidu.focus.hj.dao.impl.OwnerInformationDaoImpl;
import com.yidu.focus.hj.dao.impl.RecordDaoImpl;
import com.yidu.focus.hj.domain.Arrearage;
import com.yidu.focus.hj.domain.CarArrearage;
import com.yidu.focus.hj.domain.Income;
import com.yidu.focus.hj.domain.OwnerInformation;
import com.yidu.focus.hj.domain.Record;
import com.yidu.focus.wzh.domain.Users;

/**
 * Servlet implementation class RecordServlet
 */
@WebServlet("/RecordServlet")
public class RecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String String = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordServlet() {
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
		}else if(method.equals("jiaofei")){
			this.jiaofei(request,response);
		}
	}

	private void jiaofei(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�������������
		PrintWriter out = response.getWriter();
		//����session����
		HttpSession session=request.getSession();
		//ȡ��session�е�users����
		Users user=(Users) session.getAttribute("users");
		//ȡ����ʱ��¼���û���
		String ownerName=user.getuName();
		//����Ƿ����ϸ���ݲ����
		RecordDao recordDao=new RecordDaoImpl();
		//�������ݲ㰴���ֲ�ѯ�ķ���
		Record record=(Record) recordDao.getRecordByName(ownerName);
		//�õ����
		int recordId=record.getRecordId();
		//�õ��绰
		String ownerTelphone=record.getOwnerTelphone();
		//�õ�����
		String recordType=record.getRecordType();
		//����������
		double recordMoney=0.0;
		//��ȡ��ǰϵͳʱ��
		Date date = new Date();
		//��ϵͳʱ��ת��Ϊ���ݿ��ʽ
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String recordDate=sdf.format(date);
		//��װ
		Record record1 = new Record(ownerName, ownerTelphone, recordType, recordMoney, recordDate);
		int rows = recordDao.add(record1);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		
		//����ҵ����Ϣ����
		OwnerInformationDao ownerInformationDao=new OwnerInformationDaoImpl();
		//����ͨ�����ֲ�ѯ�ķ���
		OwnerInformation ownerInformation=ownerInformationDao.findOwnerInformationByName(ownerName);
		//�õ�����
		String houseId=ownerInformation.getHouseId();
		//�ı�״̬��δǷ�ѡ�
		String state="δǷ��";
		//Ƿ�ѽ����0
		double arrearageMoney=recordMoney;
		//����ɷ�ʱ��
		String arrearageDate=recordDate;
		//��װ
		Arrearage arrearage=new Arrearage(houseId, ownerName, ownerTelphone, state, arrearageMoney, arrearageDate);
		//������ҵǷ�Ѷ���
		ArrearageDao arreargaDao=new ArrearageDaoImpl();
		//�����޸ķ���
		int rowswuye=arreargaDao.update(arrearage);
		
		//����ͣ����Ƿ�Ѷ���
		CarArrearageDao carArreargaDao=new CarArrearageDaoImpl();
		//���ð����ֲ�ѯ�ķ���
		CarArrearage carArrearage=carArreargaDao.getCarArrearageByName(ownerName);
		//��λ���
		String parkId=carArrearage.getParkId();
		//��λ״̬
		String parkStatus=carArrearage.getParkStatus();
		//Ƿ����0
		double carMoney=0.0;
		//�ɷ�ʱ��
		String carDate=recordDate;
		//��װ
		CarArrearage carArrearage2=new CarArrearage(parkId, ownerName, ownerTelphone, parkStatus, carMoney, carDate);
		int rowscar=carArreargaDao.update(carArrearage2);
		
		if(rowswuye>0){
			//�õ�������Դ
			String source="��ҵ��";
			//�õ��ɷѽ��
			double money=record.getRecordMoney();
			//��Դʱ��
			String source_date=recordDate;
			//��װ
			Income income=new Income(source, money, source_date);
			//�����������
			IncomeDao incomDao=new IncomeDaoImpl();
			//��ӵ�income����
			incomDao.add(income);
		}else if(rowscar>0){
			//�õ�������Դ
			String source="ͣ����";
			//�õ��ɷѽ��
			double money=record.getRecordMoney();
			//��Դʱ��
			String source_date=recordDate;
			//��װ
			Income income=new Income(source, money, source_date);
			//�����������
			IncomeDao incomDao=new IncomeDaoImpl();
			//��ӵ�income����
			incomDao.add(income);
		}
		
		
		out.close();
		
	}

	private void findByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�������������
		PrintWriter out=response.getWriter();
		//����session����
		HttpSession session=request.getSession();
		//ȡ��session�е�users
		Users user=(Users) session.getAttribute("users");
		//ȡ����ʱ��¼���û���
		String ownerName=user.getuName();
		//�������ݲ����
		RecordDao recordDao=new RecordDaoImpl();
		//�������ݲ㷽���õ�����
		List<Record> recordList=recordDao.getRecordByName(ownerName);
		//����Gson����
		Gson gson = new Gson();
		//ͨ��Gson����Map����ת����Json���ݸ�ʽ
		String reclist = gson.toJson(recordList);
		
		out.print(reclist);
		out.close();
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���������������
		PrintWriter out = response.getWriter();
		//����������ҳ����
		String recordIdStr = request.getParameter("recordIdStr");
		//���ַ�����"��"�ָ�
		String[] recordIdS = recordIdStr.split(",");
		//�������ݲ��������
		RecordDao recordDao = new RecordDaoImpl();
		try {
			for(int i = 0;i<recordIdS.length;i++){
				String trecordId=recordIdS[i];
				int recordId = Integer.parseInt(trecordId);
				recordDao.delete(recordId);
				
			}
			out.print("success");
		} catch (Exception e) {
			out.print("failure");
		}
		out.close();
		
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		int recordId = Integer.parseInt(request.getParameter("recordId"));
		String ownerName = request.getParameter("ownerName");
		String ownerTelphone = request.getParameter("ownerTelphone");
		String recordType = request.getParameter("recordType");
		double recordMoney = Double.parseDouble(request.getParameter("recordMoney"));
		String recordDate = request.getParameter("recordDate");
		RecordDao recordDao = new RecordDaoImpl();
		Record record = new Record(recordId, ownerName, ownerTelphone, recordType, recordMoney, recordDate);
		int rows = recordDao.update(record);
		if(rows>0){
			out.print("success");
		}else{
			out.print("failure");
		}
		out.close();
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String ownerName = request.getParameter("ownerName");
		String ownerTelphone = request.getParameter("ownerTelphone");
		String recordType = request.getParameter("recordType");
		double recordMoney = Double.parseDouble(request.getParameter("recordMoney"));
		String recordDate = request.getParameter("recordDate");
		RecordDao recordDao = new RecordDaoImpl();
		Record record = new Record(ownerName, ownerTelphone, recordType, recordMoney, recordDate);
		int rows = recordDao.add(record);
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
		String recordId = request.getParameter("recordId");
		String ownerName = request.getParameter("ownerName");
		String recordType = request.getParameter("recordType");
		String condition = " where 1=1 ";
		//���������ݽ����жϲ���ϳɲ�ѯ����
		if(recordId!=null&&!recordId.equals("")){
			condition=condition+" and recordId like '%"+recordId+"%' ";
		}
		if(ownerName!=null&&!ownerName.equals("")){
			condition = condition+" and ownerName like '%"+ownerName+"%' ";
		}
		if(recordType!=null&&!recordType.equals("")){
			condition = condition+" and recordType like '%"+recordType+"%' ";
		}
		//�������Կͻ��˴��ݹ�����page��rows����
		int rows = Integer.parseInt(request.getParameter("rows"));
		int page = Integer.parseInt(request.getParameter("page"));
		//�������ݲ�����
		RecordDao recordDao = new RecordDaoImpl();
		//�������ݲ�ִ�з�ҳ��ѯ����������浽������
		List<Record> recordList = recordDao.findByPage(rows, page, condition);
		int totalRows = recordDao.count(condition);
		//����ӳ�伯�϶���
		Map<String, Object> mapData = new HashMap<String, Object>();
		//�����ݱ��浽map������
		mapData.put("total", totalRows);
		mapData.put("rows", recordList);
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
