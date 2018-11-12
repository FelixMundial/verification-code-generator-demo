package test;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args)
            throws FileNotFoundException, IOException {
        CodeMaker codeMaker = new CodeMaker();
        BufferedImage bi = codeMaker.getImage();
        System.out.println(codeMaker.getText());

        CodeMaker.outputImage(bi, new FileOutputStream("./code_product.jpg"));
    }
}
