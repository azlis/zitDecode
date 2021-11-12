package com.webdao.analyze;

import com.webdao.model.SamplingParams;
import java.util.Random;

/* loaded from: classes.dex */
public class MainText {
    public static void main(String[] args) {
        Analyze a = new Analyze();
        float[] fArr = new float[SamplingParams.POINT_1024_SAMPLING];
        Random random = new Random(1024);
        for (int i = 0; i < 1024; i++) {
            fArr[i] = random.nextFloat();
        }
        ANALYZE_PARAMTER paramter = new ANALYZE_PARAMTER();
        for (int i2 = 0; i2 < 1024; i2++) {
            if (i2 % 10 == 0) {
                System.out.println("      ");
            }
            System.out.print(fArr[i2] + "\t");
        }
        float f2 = (float) a.analyzeOneWave(fArr, paramter);
        System.out.println("===============================");
        for (int i3 = 0; i3 < 1024; i3++) {
            if (i3 % 10 == 0) {
                System.out.println("      ");
            }
            System.out.print(fArr[i3] + "\t");
        }
        System.out.println(f2);
    }
}
