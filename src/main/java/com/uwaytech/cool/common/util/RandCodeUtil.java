package com.uwaytech.cool.common.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

/**
 * 负责产生验证码
 *
 */
public class RandCodeUtil implements Serializable {
    private static final long serialVersionUID = 1L;
    private final static String DEFAULT_RANDSTR = "ABCDEFGHIGKLMNOPQRSTUVWXYZ";
    private final int width = 600;
    private final int height = 200;
    private final int fontsize = 140;
    private final int fontnum = 4;
    private Random rand = new Random();
    private String randcode = null;
    private String imgType = "png";

    private BufferedImage bi;

    public RandCodeUtil(String imgType) {
        this.imgType = imgType;
        this.randcode = this.createRandcode();
        if (imgType.equals("PNG")) {
            initPNG();
        } else {
            initJPG();
        }
    }

    /**
     * 初始化JPG图片
     *
     * @param
     * @return
     * @author lijian
     * @date 2013-8-12
     */
    private void initJPG() {
        this.bi = new BufferedImage(width - 50, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.fillRect(0, 0, width, height);
        this.draw(g);
    }

    /**
     * 初始化PNG图片
     *
     * @param
     * @return
     * @author lijian
     * @date 2013-8-9
     */
    private void initPNG() {
        this.bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //创建画布
        Graphics2D g2d = bi.createGraphics();
        //增加下面的代码使得背景透明
        bi = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        //释放对象
        g2d.dispose();
        //创建画布
        g2d = bi.createGraphics();
        //绘制验证码
        draw(g2d);
        //释放对象
        g2d.dispose();
    }


    /**
     * 绘制验证码和干扰线
     */
    public void draw(Graphics2D g) {
        this.drawCode(g);
        this.drawLine(g);
        this.bi = this.reduce(bi);

    }

    /**
     * 返回图片输入流
     *
     * @return
     * @throws java.io.IOException
     */
    public InputStream getInputStream() throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ImageOutputStream ios = ImageIO.createImageOutputStream(bs);
        ImageIO.write(bi, imgType, ios);
        ios.close();
        bs.close();
        return new ByteArrayInputStream(bs.toByteArray());
    }


    /**
     * 绘制随即字母
     *
     * @return 绘制的随机字母
     */
    private void drawCode(Graphics2D g) {
        char[] ch = this.randcode.toCharArray();
        for (int i = 0; i < fontnum; i++) {
            int t_width = (this.width - 80) / 4 * i + 20;
            float rotateVal = (float) (rand.nextInt(30)) / 100;
            rotateVal = rand.nextBoolean() ? rotateVal * -1 : rotateVal;
            Point center = new Point(t_width, height / 2);//旋转的中心坐标
            Font font = new Font("宋体", Font.BOLD, rand.nextInt(50) + fontsize);
            Color color = new Color(rand.nextInt(200), rand.nextInt(200), rand.nextInt(200));
            g.setFont(font);
            g.setColor(color);
            g.rotate(rotateVal, center.x, center.y);
            g.drawString(String.valueOf(ch[i]), center.x, (int) (fontsize * 1.25));
            g.rotate(-rotateVal, center.x, center.y);
        }
    }

    /**
     * 绘制干扰线
     */
    private void drawLine(Graphics2D g) {
        int widths[] = new int[width];
        int heights[] = new int[width];
        Point p0 = new Point(0, rand.nextInt(height));
        Point p1 = new Point(rand.nextInt(width), rand.nextInt(height));
        Point p2 = new Point(rand.nextInt(width), rand.nextInt(height));
        Point p3 = new Point(rand.nextInt(width), rand.nextInt(height));
        Point p4 = new Point(600, rand.nextInt(height));

        for (int i = 0; i < width; i++) {
            double t = ((double) i) / width;
            Point temp = get(t, p0, p1, p2, p3, p4);
            widths[i] = temp.x;
            heights[i] = temp.y;
        }
        g.setStroke(new BasicStroke(10));
        g.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
        g.drawPolyline(widths, heights, width);
    }

    /**
     * 把图片缩小
     *
     * @param bufferedimage
     * @return
     */
    private BufferedImage reduce(BufferedImage bufferedimage) //把图片缩小
    {
        int width = 120;
        int height = 40;

        if (imgType.equals("PNG")) {
            //缩放图片
            Image compressImage = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            //图像透明设置
            BufferedImage bufferedImage = new BufferedImage(width, height, this.bi.getType());
            Graphics graphics = bufferedImage.getGraphics();
            Graphics2D graphics2d = (Graphics2D) graphics;
            //绘制边框颜色
//	        graphics2d.setColor(Color.GREEN);
//	        graphics2d.drawRect(0, 0, width - 1, height - 1);   
            //绘制图片
            graphics2d.drawImage(compressImage, 0, 0, null);
            return bufferedImage;
        } else {
            //输出缩放后的图片
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);//把大图片缩放成小图片
            tag.getGraphics().drawImage(bufferedimage, 0, 0, width, height, null); //绘制缩小后的图
            return tag;
        }


    }

    /**
     * 根据横坐标t得到曲线上的一系列点
     *
     * @param t
     * @param points
     * @return
     */
    private Point get(double t, Point... points) {
        t = formart(t, 2);
        int n = points.length - 1;
        double x = 0;
        double y = 0;
        for (int i = 0; i <= n; i++) {
            x += factorial(n) / (factorial(n - i) * factorial(i)) * points[i].x * Math.pow(1 - t, n - i) * Math.pow(t, i);
            y += factorial(n) / (factorial(n - i) * factorial(i)) * points[i].y * Math.pow(1 - t, n - i) * Math.pow(t, i);
        }
        int X = Integer.parseInt("" + (long) x);
        int Y = Integer.parseInt("" + (long) y);
        return new Point(X, Y);
    }

    private double formart(double arg, int num) {
        double multiplicand = Math.pow(10, num);
        return Math.round(arg * multiplicand) / multiplicand;
    }

    /**
     * 得到一个数的阶乘
     */
    private int factorial(int num) {
        int value = 1;
        for (int i = 1; i <= num; i++) {
            value *= i;
        }
        return value;
    }


    public String getRandcode() {
        return randcode;
    }

    /**
     * 创建将要画在图片上的随即字符
     */
    private String createRandcode() {
        String str = "";
        for (int i = 0; i < fontnum; i++) {
            str += String.valueOf(DEFAULT_RANDSTR.charAt(rand.nextInt(DEFAULT_RANDSTR.length())));
        }
        return str;
    }
}
