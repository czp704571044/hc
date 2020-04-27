package cn.yfyue.comm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class KindEditorUpload extends HttpServlet {
	private PrintWriter out = null;
	public void init() throws ServletException {

	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// L.printRequest(null, request);
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		out = response.getWriter();
		String httpSubmitStr = null;
		try {
			httpSubmitStr = upload(request);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		out.print(httpSubmitStr);
		out.flush();
		out.close();
	}

	private static String upload(HttpServletRequest req) {
		InputStream in = null;
		String s = null;
		DataOutputStream ds = null;
		try {
			in = req.getInputStream();
			URL url = new URL(SetSys.kindEditorAttrUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setRequestMethod("POST");
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "UTF-8");
			con.setRequestProperty("Content-Type", req.getContentType());
			ds = new DataOutputStream(con.getOutputStream());
			ds.write(getBytes(in));
			ds.flush();
			InputStream is = con.getInputStream();
			StringBuffer b = new StringBuffer();
			int ch;
			while ((ch = is.read()) != -1) {
				b.append((char) ch);
			}
			s = b.toString();
		} catch (Exception e) {
			e.printStackTrace(System.err);

		} finally {
			try {
				if (ds != null) {
					ds.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	public static byte[] getBytes(InputStream is) throws Exception {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int n = 0;
		while (-1 != (n = is.read(buffer))) {
			output.write(buffer, 0, n);
		}
		return output.toByteArray();
	}
}
