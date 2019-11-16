package com.yidu.focus.ll.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yidu.focus.hj.dao.IncomeDao;
import com.yidu.focus.hj.dao.impl.IncomeDaoImpl;
import com.yidu.focus.hj.domain.Income;
import com.yidu.focus.ll.dao.CarTimeDao;
import com.yidu.focus.ll.dao.impl.CarTimeDaoImpl;
import com.yidu.focus.ll.domain.CarTime;
/**
 * 
 * ���ܣ���ʱͣ�� 
 * ���ߣ�����
 * ���ڣ�2019��10��31������9:10:15
 * �汾��1.0
 */
@WebServlet("/CarTimeServlet")
public class CarTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CarTimeServlet() {

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
		}else if("delete".equals(method)){
			this.delete(request,response);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		String NubStr=request.getParameter("NubStr");		
		//���ַ������а�","(����)�ָ�����ַ�������
		String[] Nubs=NubStr.split(",");
		//�������ݲ��������
		CarTimeDao carTimedao=new CarTimeDaoImpl();
		//����������ɾ�����п���ʧ�ܣ��˴�ʹ���쳣������ʵ��
		try{
			//ʹ��ѭ����������ɾ��
			for(int i=0;i<Nubs.length;i++){
				//��ȡ��ǰ���ַ���
				String tNubStr=Nubs[i];
				//���ַ������ת��Ϊ���͵����
				int Nub=Integer.parseInt(tNubStr);
				//ִ��ɾ������
				carTimedao.delete(Nub);
			}
			out.print("success");

		}catch(Exception e){
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
			//�������������
			PrintWriter out=response.getWriter();
			//�������Կͻ��˵�����
			//���ƺ�
			String carId=request.getParameter("carId");
			//����ʱ��
			String goTime=request.getParameter("goTime");
			//�뿪ʱ��
			String toTime=request.getParameter("toTime");
			//������
			double Money = 0;
			//ֵ��Ա
			String empNo=request.getParameter("empNo");
			//�ַ���ת��Ϊʱ��
			Timestamp goTimes = Timestamp.valueOf(goTime);
			Timestamp toTimes = Timestamp.valueOf(toTime);
			//����ʱ�������
			long result = toTimes.getTime()-goTimes.getTime();
			//�õ�������
			result = result/1000;
			//�жϣ����н����շ�
			if(result>60*60*24){
				Money = 40;
			}else if(result>60*60*12){
				Money = 30;
			}else if(result>60*60*4){
				Money = 20;
			}else if(result>60*60){
				Money = 10;
			}else{
				Money=0;
			}
			//���ݷ�װ�ɶ���
			CarTime carTime=new CarTime(carId,goTime,toTime,Money,empNo);
			//�������ݲ����ִ����ӷ���
			CarTimeDao carTimeDao=new CarTimeDaoImpl();
			int rows=carTimeDao.add(carTime);
			//�ж�����Ƿ�ɹ�
			if(rows>0){
				out.print("success");
			
			}else{
				out.print("failure");	
			}
			
			/*������ͬ�����������*/
			//����������Դ
			String source = "��ʱͣ���շ�";
			//���
			double money = Money;
			//�շ�ʱ��
			String source_date = toTime;
			//������������ݲ����Ӷ���
			IncomeDao incomeDao = new IncomeDaoImpl();			
			//�����������
			Income income = new Income(source, money, source_date);
			//������ӵķ���
			incomeDao.add(income);
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
			String carId=request.getParameter("carId");		
			//���ɲ�ѯ����
			String condition=" where 1=1 ";
			//���ݳ��ƺŽ���ģ����ѯ
			if(carId!=null && !carId.equals("")){
				condition=condition+"and carId like '%"+carId+"%' ";
			}
			//�������Կͻ��˵�datagrid����Ĵ��ݹ�����page��rows����
			int rows=Integer.parseInt(request.getParameter("rows"));
			int page=Integer.parseInt(request.getParameter("page"));
			//�������ݲ�ִ�з�ҳ��ѯ
			CarTimeDao carTimedao=new CarTimeDaoImpl();
			//��õ�ǰҳ�����ݼ���
			List<CarTime> carTimeList=carTimedao.findByPage(rows, page, condition);
			//��ѯ��CarTime����ܼ�¼��
			int totalRows=carTimedao.count();
			//����ӳ�伯�϶���
			Map<String, Object> mapData = new HashMap<String, Object>();
			//�����ݱ��浽map������
			mapData.put("total", totalRows);
			mapData.put("rows", carTimeList);
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
			doGet(request, response);
		}

	}
