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
import com.yidu.focus.hxl.dao.CarInformationDao;
import com.yidu.focus.hxl.dao.impl.CarInformationDaoImpl;
import com.yidu.focus.hxl.domain.CarInformation;


/**
 * 
 * ���ܣ�������Ϣ�� 
 * ���ߣ���ϣ��
 * ���ڣ�2019��10��31������9:06:34
 * �汾��1.0
 */
@WebServlet("/CarInformationServlet")
public class CarInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarInformationServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������Ӧ������ַ���
		response.setCharacterEncoding("utf-8");
		//������Ӧ�������������
		response.setContentType("text/html");
		//�������������ַ���
		request.setCharacterEncoding("utf-8");
		
		String method=request.getParameter("method");
	
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
		//��ȡ�������������ݹ�����Ա������ַ���
		//carId,carName,carTelphone,carportId   carInformation
		String carIdStr=request.getParameter("carIdStr");
		//���ַ������а�","(����)�ָ�����ַ�������
		String[] carIds=carIdStr.split(",");
		
		//�������ݲ��������
		CarInformationDao carInformationDao=new CarInformationDaoImpl();
		
		//����������ɾ�����п���ʧ�ܣ��˴�ʹ���쳣������ʵ��
		try{
			//ʹ��ѭ����������ɾ��
			for(int i=0;i<carIds.length;i++){
				//��ȡ��ǰ���ַ���
				String eqId=carIds[i];
				//ִ��ɾ������
				carInformationDao.deleteById(eqId);
			}
			out.print("success");
		}catch(Exception e){
			out.print("failure");
		}
		
		//�ر��������
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
		//carId,carName,carTelphone,carportId   carInformation
		//�������Կͻ��˵�����
		String carId=request.getParameter("carId");
		String carName=request.getParameter("carName");
		String carTelphone=request.getParameter("carTelphone");
		String carportId=request.getParameter("carportId");
		
		//���ݷ�װ�ɶ���
		CarInformation carInformation=new CarInformation(carId,carName,carTelphone,carportId);
		
		//�������ݲ����ִ����ӷ���
		CarInformationDao carInformationDao=new CarInformationDaoImpl();
		int rows=carInformationDao.update(carInformation);
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
		String carId=request.getParameter("carId");
		String carName=request.getParameter("carName");
		String carTelphone=request.getParameter("carTelphone");
		String carportId=request.getParameter("carportId");
		
		//���ݷ�װ�ɶ���
		CarInformation carInformation=new CarInformation(carId,carName,carTelphone,carportId);
	
		//�������ݲ����ִ����ӷ���
		CarInformationDao carInformationDao=new CarInformationDaoImpl();
		int rows=carInformationDao.add(carInformation);
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
		PrintWriter out=response.getWriter();
		
		//�õ����������е���������
		String carId=request.getParameter("carId");
		
		String carportId=request.getParameter("carportId");
		
		//���ɲ�ѯ����
		//select * from ���� where ���� limit n1,n2 
		//where �ֶ���=? and �ֶ���=? and �ֶ���=?
		String condition=" where 1=1 ";
		//���������ݽ����жϲ���ϳɲ�ѯ����
		if(carId!=null && !carId.equals("")){
			condition=condition+" and carId like '%"+carId+"%' ";
		}
		
		if(carportId!=null && !carportId.equals("")){
			//��ӵ�������
			condition=condition+" and carportId like '%"+carportId+"%' ";
		}


		
		//�������Կͻ��˵�datagrid����Ĵ��ݹ�����page��rows����
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		
		//�������ݲ�ִ�з�ҳ��ѯ
		CarInformationDao carInformationDao=new CarInformationDaoImpl();
		//��õ�ǰҳ�����ݼ���
		List<CarInformation> carInformationList=carInformationDao.findByPage(rows, page,condition);
		//��ѯ��emp����ܼ�¼��
		int totalRows=carInformationDao.count(condition);
		
		//����ӳ�伯�϶���
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", carInformationList);
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
		
		doGet(request, response);
	}

}
