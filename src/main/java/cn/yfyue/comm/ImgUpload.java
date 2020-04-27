package cn.yfyue.comm;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImgUpload extends HttpServlet {
	@SuppressWarnings("finally")
	public static String upload(HttpServletRequest req) {
		InputStream in = null;
		String s = null;
		DataOutputStream ds = null;
		try {
			in = req.getInputStream();
			URL url = new URL(SetSys.imgUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			// 发送POST请求必须设置如下两行
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
			// 定义BufferedReader输入流来读取URL的响应
			InputStream is = con.getInputStream();
			int ch;
			StringBuffer b = new StringBuffer();
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
					ds = null;
				}
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return s;
		}
	}

	public static byte[] getBytes(InputStream is) throws Exception {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			byte[] buffer = new byte[4096];
			int n = 0;
			while (-1 != (n = is.read(buffer))) {
				output.write(buffer, 0, n);
			}
			return output.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			output.close();
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.getWriter().write(upload(req));
		resp.getWriter().flush();
	}
}
