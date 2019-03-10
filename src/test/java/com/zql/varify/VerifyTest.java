package com.zql.varify;

import com.zql.verifycode.ImageUtils;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

/**
 * Created by 张启磊 on 2019-3-10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VerifyTest {
    @Test
    public void test() throws TesseractException {
        String in="C:\\Users\\38213\\Desktop\\wx\\verifycode\\verifycode5.jpg";
        String out="C:\\Users\\38213\\Desktop\\wx\\verifycode2\\verifycode5.jpg";
        ImageUtils.removeBackground(in,out);
        ImageUtils.cuttingImg(out);
        File image = new File(out);
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Users\\38213\\Desktop\\wx\\tessdata");
        String s = tesseract.doOCR(image);
        System.out.println("result:"+s);

    }
}
