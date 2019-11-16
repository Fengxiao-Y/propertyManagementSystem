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
import com.yidu.focus.hxl.dao.EqCheckDao;
import com.yidu.focus.hxl.dao.impl.EqCheckDaoImpl;
import com.yidu.focus.hxl.domain.EqCheck;


/**
 * 
 * ���ܣ��豸Ѳ��� 
 * ���ߣ���ϣ��
 * ���ڣ�2019��10��31������9:07:09
 * �汾��1.0
 */
@WebServlet("/EqCheckServlet")
public class EqCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EqCheckServlet() {
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
		String checkIdStr=request.getParameter("checkIdStr");
		//���ַ������а�","(����)�ָ�����ַ�������
		String[] checkIds=checkIdStr.split(",");
		
		//�������ݲ��������
		EqCheckDao eqCheckDao=new EqCheckDaoImpl();
		
		//����������ɾ�����п���ʧ�ܣ��˴�ʹ���쳣������ʵ��
		try{
			//ʹ��ѭ����������ɾ��
			for(int i=0;i<checkIds.length;i++){
				//��ȡ��ǰ���ַ���
				String temp=checkIds[i];
				int checkId=Integer.parseInt(temp);
				//ִ��ɾ������
				eqCheckDao.deleteById(checkId);
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
		//eqId,checkId,checkStatues,checkDate,empNo    eqCheck
		//�������Կͻ��˵�����
		
		String eqId=request.getParameter("eqId");
		int checkId=Integer.parseInt(request.getParameter("checkId"));
		String checkStatues=request.getParameter("checkStatues");
		String checkDate=request.getParameter("checkDate");
		String empNo=request.getParameter("empNo");
		
		//���ݷ�װ�ɶ���
		EqCheck eqCheck=new EqCheck(checkId, eqId, checkStatues, checkDate, empNo);
		
		//�������ݲ����ִ����ӷ���
		EqCheckDao eqCheckDao=new EqCheckDaoImpl();
		int rows=eqCheckDao.update(eqCheck);
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
		String eqId=request.getParameter("eqId");
		
		String checkStatues=request.getParameter("checkStatues");
		String checkDate=request.getParameter("checkDate");
		String empNo=request.getParameter("empNo");
		
		//���ݷ�װ�ɶ���
		EqCheck eqCheck=new EqCheck(eqId, checkStatues, checkDate, empNo);
	
		//�������ݲ����ִ����ӷ���
		EqCheckDao eqCheckDao=new EqCheckDaoImpl();
		int rows=eqCheckDao.add(eqCheck);
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
		String checkDate=request.getParameter("checkDate");
		String checkStatues=request.getParameter("checkStatues");
		
		//���ɲ�ѯ����
		//select * from ���� where ���� limit n1,n2 
		//where �ֶ���=? and �ֶ���=? and �ֶ���=?
		String condition=" where 1=1 ";
		//���������ݽ����жϲ���ϳɲ�ѯ����
		if(eqId!=null && !eqId.equals("")){
			condition=condition+" and eqId like '%"+eqId+"%' ";
		}
		//�����ֶ�ֵ�����жϣ���������
		if(checkDate!=null && !checkDate.equals("")){
			condition=condition+" and checkDate like '%"+checkDate+"%' ";
		}
		
		if(checkStatues!=null && !checkStatues.equals("")){
			//��ӵ�������
			condition=condition+" and checkStatues like'%"+checkStatues+"%' ";
		}

		
		//�������Կͻ��˵�datagrid����Ĵ��ݹ�����page��rows����
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		
		//�������ݲ�ִ�з�ҳ��ѯ
		EqCheckDao eqCheckDao=new EqCheckDaoImpl();
		//��õ�ǰҳ�����ݼ���
		List<EqCheck> eqCheckList=eqCheckDao.findByPage(rows, page,condition);
		//��ѯ��emp����ܼ�¼��
		int totalRows=eqCheckDao.count(condition);
		
		//����ӳ�伯�϶���
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", eqCheckList);
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
