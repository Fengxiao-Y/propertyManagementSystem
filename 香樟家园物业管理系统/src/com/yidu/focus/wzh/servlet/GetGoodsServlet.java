package com.yidu.focus.wzh.servlet;

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
import com.yidu.focus.wzh.dao.GetGoodsDao;
import com.yidu.focus.wzh.dao.impl.GetGoodsDaoImpl;
import com.yidu.focus.wzh.domain.GetGoods;

/**
 * 
 * ���ܣ���Ʒ���ñ� 
 * ���ߣ���־��
 * ���ڣ�2019��10��31������9:17:14
 * �汾��1.0
 */
@WebServlet("/GetGoodsServlet")
public class GetGoodsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 378250551211170613L;

	public GetGoodsServlet() {
		super();

	}

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
		}else if("update".equals(method)){
			this.update(request,response);
		}else if("delete".equals(method)){
			this.delete(request,response);
		}

	}

	/**
	 * ɾ������
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException  IO�쳣
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���������
		PrintWriter out=response.getWriter();
		//��ȡ�������������ݹ������û�����ַ���
		String ggIdStr=request.getParameter("ggIdStr");
		//���ַ������а�","(����)�ָ�����ַ�������
		String[] ggIds=ggIdStr.split(",");
		//�����������ݲ��������
		GetGoodsDao getGoodsDao=new GetGoodsDaoImpl();
		//����������ɾ�����п���ʧ�ܣ��˴�ʹ���쳣������ʵ��
		try{
			//ʹ��ѭ����������ɾ��
			for(int i=0;i<ggIds.length;i++){
				//��ȡ��ǰ���ַ���
				String tempId=ggIds[i];
				//���ַ������ת��Ϊ���͵�Ա�����
				int ggId=Integer.parseInt(tempId);
				//ִ��ɾ������
				getGoodsDao.delete(ggId);
			}
			out.print("success");
		}catch(Exception e){
			out.print("failure");
		}
		//�ر��������
		out.close();
	}

	/**
	 * ����
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException IO�쳣
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���������
		PrintWriter out=response.getWriter();
		//�������Կͻ��˵�����
		//���ü�¼���
		int ggId=Integer.parseInt(request.getParameter("ggId"));
		//��Ʒ����
		String goodsName=request.getParameter("goodsName");
		//��������
		int ggNum=Integer.parseInt(request.getParameter("ggNum"));
		//������
		String ggName=request.getParameter("ggName");
		//����ʱ��
		String ggTime=request.getParameter("ggTime");
		//������
		String ggHandli=request.getParameter("ggHandli");
		//���ݷ�װ�ɶ���
		GetGoods getgoods=new GetGoods(ggId, goodsName, ggNum, ggName, ggTime, ggHandli);
		//�����������ݲ��������
		GetGoodsDao getgoodsDao=new GetGoodsDaoImpl();
		//�������ݲ����ִ����ӷ���
		int rows=getgoodsDao.update(getgoods);
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
		//��Ʒ����
		String goodsName=request.getParameter("goodsName");
		//������
		String ggName=request.getParameter("ggName");

		//���ɲ�ѯ����
		String condition=" where 1=1 ";
		//���������ݽ����жϲ���ϳɲ�ѯ����
		//������Ʒ�����ֶ�ֵ�����жϣ���������
		if(goodsName!=null && !goodsName.equals("")){
			condition=condition+" and goodsName like '%"+goodsName+"%' ";
		}
		//�����������ֶ�ֵ�����жϣ���������
		if(ggName!=null && !ggName.equals("")){
			condition=condition+" and ggName like '%"+ggName+"%' ";
		}


		//�������Կͻ��˵�datagrid����Ĵ��ݹ�����page��rows����
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));

		//�����������ݲ�ִ�з�ҳ��ѯ
		GetGoodsDao getgoodsDao=new GetGoodsDaoImpl();
		//��õ�ǰҳ�����ݼ���
		List<GetGoods> getgoodsList=getgoodsDao.findByPage(rows, page,condition);
		//��ѯ��getgoods����ܼ�¼��
		int totalRows=getgoodsDao.count(condition);


		//����ӳ�伯�϶���
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", totalRows);
		mapData.put("rows", getgoodsList);
		//����Gson����
		Gson gson = new Gson();
		//ͨ��Gson����Map����ת����json���ݸ�ʽ
		String jsonData = gson.toJson(mapData);
		//��json����������ͻ���
		out.println(jsonData);
		out.close();

	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
