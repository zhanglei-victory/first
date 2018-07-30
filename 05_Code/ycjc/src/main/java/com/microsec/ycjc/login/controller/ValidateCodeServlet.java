package com.microsec.ycjc.login.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ValidateCodeServlet
 */
public class ValidateCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ValidateCodeServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// ��ֹͼ�񻺴�,ʹ�õ�����֤�����ˢ����֤��ͼƬ
		resp.setHeader("Pragma", "nocache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);
		resp.setContentType("image/jpeg");
		BufferedImage bim = new BufferedImage(70, 20,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D gc = bim.createGraphics();
		// ����ͼƬ�����ɫ
		gc.setColor(Color.LIGHT_GRAY);
		gc.fillRect(0, 0, 70, 20);
		// ���ñ߿���ɫ
		gc.setColor(Color.pink);
		gc.drawRect(0, 0, 69, 19);
		// ����4λ�����
		Random rand = new Random();
		StringBuffer sb = new StringBuffer();
		// ���ø�������ɫ
		gc.setColor(Color.cyan);
		for (int j = 0; j < 30; j++) {
			int x = rand.nextInt(70);
			int y = rand.nextInt(20);
			int x1 = rand.nextInt(6);
			int y1 = rand.nextInt(6);
			// ��ͼƬ���滭������
			gc.drawLine(x, y, x + x1, y + y1);
		}
		for (int i = 0; i < 4; i++) {
			int m = rand.nextInt(9);
			
			// �����ɵ�����д�뵽ͼƬ��ȥ,intת��string
			String str = String.valueOf(m);
			// ����������ɫ
			gc.setColor(Color.RED);
			gc.drawString(str, i * 10 + 20, 15);
			sb.append(m);
		}
		// ��stringbufferת��string
		String sb1 = String.valueOf(sb);
		// �����ɵ���֤���ֵ�ŵ�session��ȥ
		req.getSession().setAttribute("validateCodeInSession", sb1);
//		System.out.println("random:" + sb1);
		// ��ͼƬ��������ʽ���
		ServletOutputStream sos = resp.getOutputStream();
		ImageIO.write(bim, "jpg", sos);
		sos.close();
	}
}
