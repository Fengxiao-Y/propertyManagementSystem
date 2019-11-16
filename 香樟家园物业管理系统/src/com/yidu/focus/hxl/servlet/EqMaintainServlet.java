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
import com.yidu.focus.hxl.dao.EqMaintainDao;
import com.yidu.focus.hxl.dao.impl.EqMaintainDaoImpl;
import com.yidu.focus.hxl.domain.EqMaintain;

/**
 * 
 * ���ܣ��豸ά�ޱ� 
 * ���ߣ���ϣ��
 * ���ڣ�2019��10��31������9:07:36
 * �汾��1.0
 */
@WebServlet("/EqMaintainServlet")
public class EqMaintainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EqMaintainServlet() {
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
		String mainIdStr=request.getParameter("mainIdStr");
		//���ַ������а�","(����)�ָ�����ַ�������
		String[] mainIds=mainIdStr.split(",");
		
		//�������ݲ��������
		EqMaintainDao eqMaintainDao=new EqMaintainDaoImpl();
		
		//����������ɾ�����п���ʧ�ܣ��˴�ʹ���쳣������ʵ��
		try{
			//ʹ��ѭ����������ɾ��
			for(int i=0;i<mainIds.length;i++){
				//��ȡ��ǰ���ַ���
				String mainId=mainIds[i];
				//ִ��ɾ������
				eqMaintainDao.deleteById(mainId);
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
		PrintWriter out=response.getWriter();
		
		//�������Կͻ��˵�����
		String maintainId=request.getParameter("maintainId");
		String eqId=request.getParameter("eqId");
		String maintainStatues=request.getParameter("maintainStatues");
		String maintainDate=request.getParameter("maintainDate");
		String consumable=request.getParameter("consumable");
		int consumNum=Integer.parseInt(request.getParameter("consumNum"));
		double checkCost=Double.parseDouble(request.getParameter("checkCost"));
		String empNo=request.getParameter("empNo");
		
		//���ݷ�װ�ɶ���
		EqMaintain eqMaintain=new EqMaintain(maintainId,eqId, maintainStatues, maintainDate, consumable,consumNum, checkCost, empNo);
		
		//�������ݲ����ִ����ӷ���
		EqMaintainDao eqMaintainDao=new EqMaintainDaoImpl();
		int rows=eqMaintainDao.update(eqMaintain);
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
		String maintainId=request.getParameter("maintainId");
		String eqId=request.getParameter("eqId");
		String maintainStatues=request.getParameter("maintainStatues");
		String maintainDate=request.getParameter("maintainDate");
		String consumable=request.getParameter("consumable");
		
		int consumNum=Integer.parseInt(request.getParameter("consumNum"));
		
		double checkCost=Double.parseDouble(request.getParameter("checkCost"));
		String empNo=request.getParameter("empNo");
		
		//���ݷ�װ�ɶ���
		EqMaintain eqMaintain=new EqMaintain(maintainId,eqId, maintainStatues, maintainDate, consumable,consumNum, checkCost, empNo);
		
		//�������ݲ����ִ����ӷ���
		EqMaintainDao eqMaintainDao=new EqMaintainDaoImpl();
		int rows=eqMaintainDao.add(eqMaintain);
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
		
		String eqId=request.getParameter("eqId");
		String maintainStatues=request.getParameter("maintainStatues");
		String maintainDate=request.getParameter("maintainDate");
		String empNo=request.getParameter("empNo");
		
		//���ɲ�ѯ����
		//select * from ���� where ���� limit n1,n2 
		//where �ֶ���=? and �ֶ���=? and �ֶ���=?
		String condition=" where 1=1 ";
		//���������ݽ����жϲ���ϳɲ�ѯ����
		if(eqId!=null && !eqId.equals("")){
			condition=condition+" and eqId like '%"+eqId+"%' ";
		}
		//�����ֶ�ֵ�����жϣ���������
		if(maintainStatues!=null && !maintainStatues.equals("")){
			condition=condition+" and maintainStatues like '%"+maintainStatues+"%' ";
		}
		
		if(maintainDate!=null && !maintainDate.equals("")){
			//��ӵ�������
			condition=condition+" and maintainDate like '%"+maintainDate+"%' ";
		}
		
		if(empNo!=null && !empNo.equals("")){
			//��ӵ�������
			condition=condition+" and empNo like '%"+empNo+"%' ";
		}
	
		
		
		//�������Կͻ��˵�datagrid����Ĵ��ݹ�����page��rows����
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		
		//�������ݲ�ִ�з�ҳ��ѯ
		EqMaintainDao eqMaintainDao=new EqMaintainDaoImpl();
		//��õ�ǰҳ�����ݼ���
		List<EqMaintain> eqMaintainList=eqMaintainDao.findByPage(rows, page,condition);
		//��ѯ��emp����ܼ�¼��
		int totalRows=eqMaintainDao.count(condition);
		
		//����ӳ�伯�϶���
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", eqMaintainList);
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
