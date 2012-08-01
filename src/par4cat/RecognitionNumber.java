/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package par4cat;

import java.awt.Container;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author 4ui
 */
public class RecognitionNumber {

    private static BufferedImage[] numbers = new BufferedImage[10];
    private static String[] URL = new String[10];
    private int w;
    private int h;
    private int[] pixels;
    private int[][] pixels2;

    public BufferedImage imgPhone;

    //номера в матрице
    public int[][] zero = new int[][]{
        {0, 1, 1, 1, 1, 0},
        {1, 1, 1, 1, 1, 1},
        {1, 1, 0, 0, 1, 1},
        {1, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 1},
        {1, 1, 0, 0, 1, 1},
        {1, 1, 1, 1, 1, 1},
        {0, 1, 1, 1, 1, 1}
    };
    public int[][] one = new int[][]{
        {0, 1, 1, 0, 0},
        {1, 1, 1, 0, 0},
        {0, 0, 1, 0, 0},
        {0, 0, 1, 0, 0},
        {0, 0, 1, 0, 0},
        {0, 0, 1, 0, 0},
        {0, 0, 1, 0, 0},
        {0, 0, 1, 0, 0},
        {1, 1, 1, 1, 1}
    };
    public int[][] two = new int[][]{
        {1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1},
        {1, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 1, 1},
        {0, 0, 0, 1, 1, 1},
        {0, 0, 1, 1, 1, 0},
        {0, 1, 1, 0, 0, 0},
        {1, 1, 1, 1, 1, 0},
        {1, 1, 1, 1, 1, 1}
    };
    public int[][] three = new int[][]{
        {1, 1, 1, 1, 1, 1},
        {1, 1, 0, 0, 1, 1},
        {0, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 1, 1},
        {0, 0, 1, 1, 1, 1},
        {0, 0, 0, 1, 1, 1},
        {0, 0, 0, 0, 0, 1},
        {1, 1, 0, 1, 1, 1},
        {1, 1, 1, 1, 1, 1}
    };
    public int[][] four = new int[][]{
        {0, 0, 0, 1, 1, 0, 0},
        {0, 0, 1, 1, 1, 0, 0},
        {0, 1, 1, 1, 1, 0, 0},
        {1, 1, 1, 0, 1, 0, 0},
        {1, 1, 0, 0, 1, 0, 0},
        {1, 1, 1, 1, 1, 1, 1},
        {0, 0, 0, 0, 1, 0, 0},
        {0, 0, 0, 0, 1, 0, 0},
        {0, 0, 0, 0, 1, 0, 0}
    };
    public int[][] five = new int[][]{
        {0, 1, 1, 1, 1, 1},
        {0, 1, 0, 0, 0, 0},
        {0, 1, 0, 0, 0, 0},
        {0, 1, 0, 0, 0, 0},
        {0, 1, 1, 1, 1, 1},
        {0, 0, 0, 0, 1, 1},
        {0, 0, 0, 0, 0, 1},
        {1, 1, 0, 1, 1, 1},
        {1, 1, 1, 1, 1, 1}
    };
    public int[][] six = new int[][]{
        {0, 1, 1, 1, 1, 0},
        {1, 1, 1, 0, 0, 0},
        {1, 1, 0, 0, 0, 0},
        {1, 1, 1, 1, 1, 1},
        {1, 1, 0, 0, 1, 1},
        {1, 0, 0, 0, 0, 1},
        {1, 1, 0, 0, 0, 1},
        {1, 1, 1, 0, 1, 1},
        {0, 1, 1, 1, 1, 0}
    };
    public int[][] seven = new int[][]{
        {1, 1, 1, 1, 1, 1},
        {0, 0, 0, 0, 1, 1},
        {0, 0, 0, 0, 1, 1},
        {0, 0, 0, 1, 1, 0},
        {0, 0, 0, 1, 1, 0},
        {0, 0, 0, 1, 0, 0},
        {0, 0, 1, 1, 0, 0},
        {0, 0, 1, 0, 0, 0},
        {0, 1, 1, 0, 0, 0}
    };
    public int[][] eight = new int[][]{
        {1, 1, 1, 1, 1, 1},
        {1, 1, 0, 1, 1, 1},
        {1, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1},
        {1, 1, 0, 0, 1, 1},
        {1, 0, 0, 0, 0, 1},
        {1, 1, 0, 1, 1, 1},
        {1, 1, 1, 1, 1, 1}
    };
    public int[][] nine = new int[][]{
        {0, 1, 1, 1, 1, 0},
        {1, 1, 0, 1, 1, 1},
        {1, 0, 0, 0, 1, 1},
        {1, 0, 0, 0, 0, 1},
        {1, 1, 0, 0, 1, 1},
        {1, 1, 1, 1, 1, 1},
        {0, 0, 0, 0, 1, 1},
        {0, 0, 0, 1, 1, 1},
        {0, 1, 1, 1, 1, 0}
    };

    public RecognitionNumber(BufferedImage imgP) {

        this.w = imgP.getWidth(null);
        this.h = imgP.getHeight(null);
        this.imgPhone = imgP;

        //получение массива пикселей
        pixels = new int[w*h];
        imgPhone.getRGB(0, 0, w, h, this.pixels, 0, w);

        //Создание массива из 0 и 1
        int[] data = new int[pixels.length];
        for (int i = 0; i < pixels.length; i++) {
            if (pixels[i] != 12632256)
                data[i] = 1;
            else data[i] = 0;
        }

        pixels2 = new int[h][w];
        //перевод массива в матрицу
        for ( int i = 0; i < h; i ++ )
            for ( int j = 0; j < w; j ++ )
                pixels2[i][j] = data[i*w+j];

    }

    private static void printArray(int[][] array) {
        for (int[] line : array) {
            for (int element : line) {
                System.out.print(element);
            }
            System.out.println();
        }
    }

    public String decipher() {
        String str = "";
        //перебор pixels2
        for (int pixels2LineNumer = 0; pixels2LineNumer <= pixels2.length - zero.length; pixels2LineNumer++) {
            for (int pixels2ColumnNumber = 0; pixels2ColumnNumber <= pixels2[pixels2LineNumer].length - zero[0].length; pixels2ColumnNumber++) {

                boolean zeroEq = true;

                //перебор zero
                for (int lineNumber = 0; lineNumber < zero.length && zeroEq; lineNumber++) {
                    for (int zeroColumnNumber = 0; zeroColumnNumber < zero[lineNumber].length && zeroEq; zeroColumnNumber++) {
                        if (pixels2[pixels2LineNumer + lineNumber][pixels2ColumnNumber + zeroColumnNumber] != zero[lineNumber][zeroColumnNumber]) {
                            zeroEq = false;   //неправильно выбран верхний левый элемент
                        }
                    }
                }
                if (zeroEq) {
                    str+="0";
                }


                boolean oneEq = true;

                //перебор one
                for (int lineNumber = 0; lineNumber < one.length && oneEq; lineNumber++) {
                    for (int oneColumnNumber = 0; oneColumnNumber < one[lineNumber].length && oneEq; oneColumnNumber++) {
                        if (pixels2[pixels2LineNumer + lineNumber][pixels2ColumnNumber + oneColumnNumber] != one[lineNumber][oneColumnNumber]) {
                            oneEq = false;   //неправильно выбран верхний левый элемент
                        }
                    }
                }
                if (oneEq) {
                    str+="1";
                }

                boolean twoEq = true;

                //перебор two
                for (int lineNumber = 0; lineNumber < two.length && twoEq; lineNumber++) {
                    for (int twoColumnNumber = 0; twoColumnNumber < two[lineNumber].length && twoEq; twoColumnNumber++) {
                        if (pixels2[pixels2LineNumer + lineNumber][pixels2ColumnNumber + twoColumnNumber] != two[lineNumber][twoColumnNumber]) {
                            twoEq = false;   //неправильно выбран верхний левый элемент
                        }
                    }
                }
                if (twoEq) {
                    str+="2";
                }


                boolean threeEq = true;

                //перебор three
                for (int lineNumber = 0; lineNumber < three.length && threeEq; lineNumber++) {
                    for (int threeColumnNumber = 0; threeColumnNumber < three[lineNumber].length && threeEq; threeColumnNumber++) {
                        if (pixels2[pixels2LineNumer + lineNumber][pixels2ColumnNumber + threeColumnNumber] != three[lineNumber][threeColumnNumber]) {
                            threeEq = false;   //неправильно выбран верхний левый элемент
                        }
                    }
                }
                if (threeEq) {
                    str+="3";
                }


                boolean fourEq = true;

                //перебор four
                for (int lineNumber = 0; lineNumber < four.length && fourEq; lineNumber++) {
                    for (int fourColumnNumber = 0; fourColumnNumber < four[lineNumber].length && fourEq; fourColumnNumber++) {
                        if (pixels2[pixels2LineNumer + lineNumber][pixels2ColumnNumber + fourColumnNumber] != four[lineNumber][fourColumnNumber]) {
                            fourEq = false;   //неправильно выбран верхний левый элемент
                        }
                    }
                }
                if (fourEq) {
                    str+="4";
                }


                boolean fiveEq = true;

                //перебор five
                for (int lineNumber = 0; lineNumber < five.length && fiveEq; lineNumber++) {
                    for (int fiveColumnNumber = 0; fiveColumnNumber < five[lineNumber].length && fiveEq; fiveColumnNumber++) {
                        if (pixels2[pixels2LineNumer + lineNumber][pixels2ColumnNumber + fiveColumnNumber] != five[lineNumber][fiveColumnNumber]) {
                            fiveEq = false;   //неправильно выбран верхний левый элемент
                        }
                    }
                }
                if (fiveEq) {
                    str+="5";
                }

                boolean sixEq = true;

                //перебор six
                for (int lineNumber = 0; lineNumber < six.length && sixEq; lineNumber++) {
                    for (int sixColumnNumber = 0; sixColumnNumber < six[lineNumber].length && sixEq; sixColumnNumber++) {
                        if (pixels2[pixels2LineNumer + lineNumber][pixels2ColumnNumber + sixColumnNumber] != six[lineNumber][sixColumnNumber]) {
                            sixEq = false;   //неправильно выбран верхний левый элемент
                        }
                    }
                }
                if (sixEq) {
                    str+="6";
                }


                boolean sevenEq = true;

                //перебор seven
                for (int lineNumber = 0; lineNumber < seven.length && sevenEq; lineNumber++) {
                    for (int sevenColumnNumber = 0; sevenColumnNumber < seven[lineNumber].length && sevenEq; sevenColumnNumber++) {
                        if (pixels2[pixels2LineNumer + lineNumber][pixels2ColumnNumber + sevenColumnNumber] != seven[lineNumber][sevenColumnNumber]) {
                            sevenEq = false;   //неправильно выбран верхний левый элемент
                        }
                    }
                }
                if (sevenEq) {
                    str+="7";
                }


                boolean eightEq = true;

                //перебор eight
                for (int lineNumber = 0; lineNumber < eight.length && eightEq; lineNumber++) {
                    for (int eightColumnNumber = 0; eightColumnNumber < eight[lineNumber].length && eightEq; eightColumnNumber++) {
                        if (pixels2[pixels2LineNumer + lineNumber][pixels2ColumnNumber + eightColumnNumber] != eight[lineNumber][eightColumnNumber]) {
                            eightEq = false;   //неправильно выбран верхний левый элемент
                        }
                    }
                }
                if (eightEq) {
                    str+="8";
                }


                boolean nineEq = true;

                //перебор nine
                for (int lineNumber = 0; lineNumber < nine.length && nineEq; lineNumber++) {
                    for (int nineColumnNumber = 0; nineColumnNumber < nine[lineNumber].length && nineEq; nineColumnNumber++) {
                        if (pixels2[pixels2LineNumer + lineNumber][pixels2ColumnNumber + nineColumnNumber] != nine[lineNumber][nineColumnNumber]) {
                            nineEq = false;   //неправильно выбран верхний левый элемент
                        }
                    }
                }
                if (nineEq) {
                    str+="9";
                }


            }
        }
        
        char[] cha = str.toCharArray();
        String out = "";
        for (int i = 0; i < cha.length; i++) {
            if ((i+1)%11 == 0)
                out+=cha[i]+"  ";
            else
                out+=cha[i];
        }

        return out;
    }
}
