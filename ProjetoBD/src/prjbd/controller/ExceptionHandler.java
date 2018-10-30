package prjbd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionHandler {
	public static void processExeption(HttpServletRequest request, HttpServletResponse response, Exception e)
			throws ServletException, IOException {
		e.printStackTrace(System.err);

		StringWriter writer = new StringWriter();
		PrintWriter pw = new PrintWriter(writer);
		e.printStackTrace(pw);
		
		request.setAttribute("exeption", e.toString());
		request.setAttribute("stackTrace", writer.toString());
		request.getRequestDispatcher("/erro.jsp").forward(request, response);
	}
}
