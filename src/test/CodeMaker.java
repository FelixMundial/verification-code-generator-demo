package test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

class CodeMaker {
    private final int width = 70;
    private final int height = 35;
    private final Random random = new Random();
    private String text;

    private final String[] fontNames =
            {"宋体", "华文新魏", "华文隶书", "微软雅黑", "楷体_GB2312"};
    private final String codes =
            "23456789abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";
    private final Color bgColor = new Color(255, 255, 255);

    private Color randomColor() {
        int red = random.nextInt(150);
        int green = random.nextInt(150);
        int blue = random.nextInt(150);
        return new Color(red, green, blue);
    }

    private Font randomFont() {
        int index = random.nextInt(fontNames.length);
        String fontName = fontNames[index];
        int fontStyle = random.nextInt(4);
        int fontSize = random.nextInt(5) + 24;
        return new Font(fontName, fontStyle, fontSize);
    }

    private void drawLine(BufferedImage image) {
        int num = 3;
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        for (int i = 0; i < num; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            g2.setStroke(new BasicStroke(1.5F));
            g2.setColor(Color.BLUE);
            g2.drawLine(x1, y1, x2, y2);
        }
    }

    private char randomChar() {
        int index = random.nextInt(codes.length());
        return codes.charAt(index);
    }

    private BufferedImage createImage() {
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        g2.setColor(this.bgColor);
        g2.fillRect(0, 0, width, height);
        return image;
    }

    public BufferedImage getImage() {
        BufferedImage image = createImage();
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            String s = randomChar() + "";
            sb.append(s);
            g2.setFont(randomFont());
            g2.setColor(randomColor());
            float w = i * 1.0F * width / 4;
            g2.drawString(s, w, height - 5);
        }

        this.text = sb.toString();
        drawLine(image);
        return image;
    }

    public String getText() {
        return text;
    }

    public static void outputImage(BufferedImage image, OutputStream os)
            throws IOException {
        ImageIO.write(image, "JPEG", os);
    }
}
