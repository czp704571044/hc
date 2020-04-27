package cn.yfyue.comm;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class DynaValiImg extends HttpServlet {
    public DynaValiImg() {
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException {

       response.setContentType("image/jpeg");
       response.setHeader("Pragma","No-cache");
       response.setHeader("Cache-Control","no-cache");
       response.setDateHeader("Expires", 0);

    // 在内存中创建图象
       int width=60, height=25;
       BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    // 获取图形上下文
       Graphics g = image.getGraphics();

     //生成随机类
       Random random = new Random();

    // 设定背景色
       g.setColor(getRandColor(250,255));
       g.fillRect(0, 0, width, height);

       //设定字体




     //画边框
//g.setColor(new Color());
//g.drawRect(0,0,width-1,height-1);


    // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
       g.setColor(getRandColor(100,160));
       for (int i=0;i<11;i++) {
       int x = random.nextInt(width);
       int y = random.nextInt(height);
       int xl = random.nextInt(12);
       int yl = random.nextInt(12);
       g.drawLine(x,y,x+xl,y+yl);
       }
    // 取随机产生的认证码(4位数字)
       String sRand="";
       for (int i=0;i<4;i++){
       String rand=String.valueOf(random.nextInt(10));
       sRand+=rand;
       g.setFont(new Font("Courier",Font.BOLD,19+random.nextInt(4)));
    // 将认证码显示到图象中
       g.setColor(new Color(0,0,0));//调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
       g.drawString(rand,13*i+random.nextInt(5)+1,(18+random.nextInt(5)));
       }

    // 将认证码存入SESSION
       request.getSession().removeAttribute("DynamicValidateNum");
       request.getSession().setAttribute("DynamicValidateNum",sRand);
    // 图象生效
       g.dispose();
       ServletOutputStream responseOutputStream =response.getOutputStream();
    // 输出图象到页面
       ImageIO.write(image, "JPEG", responseOutputStream);

     //以下关闭输入流！
       responseOutputStream.flush();
       responseOutputStream.close();
       }
       Color getRandColor(int fc,int bc){//给定范围获得随机颜色
       Random random = new Random();
       if(fc>255) fc=255;
       if(bc>255) bc=255;
       int r=fc+random.nextInt(bc-fc);
       int g=fc+random.nextInt(bc-fc);
       int b=fc+random.nextInt(bc-fc);
       return new Color(r,g,b);
       }

       protected void doGet(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException {
       processRequest(request, response);

       }

       protected void doPost(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException {
       processRequest(request, response);
       }

       public String getServletInfo() {
       return "Short description";
   }
}
