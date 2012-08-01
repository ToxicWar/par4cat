/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package par4cat;


import java.awt.Container;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import javax.imageio.ImageIO;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class Par4cat {
    static BufferedImage imgPhones;

    public static void main(String[] args) throws IOException {
        FileWriter fstream = new FileWriter("res/test.csv");
        BufferedWriter out = new BufferedWriter(fstream);
        for (int i = 0; i < 12700; i++) {

            try {
                Document doc = Jsoup.connect("http://e58.ru/firms/"+ i).timeout(0).get();
                Element h1 = doc.select("h1").first();
                Elements director = doc.getElementsByClass("director");
                Elements address = doc.getElementsByClass("address");

                Element link = doc.getElementsByClass("phones").first();
                Element img = link.select("img").first();
                String hrefPhones = img.attr("src");

                Elements email = doc.getElementsByClass("email");
                Elements www = doc.getElementsByClass("www");
                Elements work = doc.getElementsByClass("work");
                Elements rubriks = doc.getElementsByClass("rubriks");

                String URL = "http://e58.ru/firms/5755/" + hrefPhones;
                imgPhones = ImageIO.read(new URL(URL));

                MediaTracker mediaTracker = new MediaTracker(new Container());
                mediaTracker.addImage(imgPhones, 0);
                mediaTracker.waitForAll();

                RecognitionNumber rn = new RecognitionNumber(imgPhones);

                out.write(h1.text()+';');
                out.write(director.text()+';');
                out.write(address.text()+';');
                out.write(rn.decipher()+ ';');
                out.write(email.text()+';');
                out.write(www.text()+';');
                out.write(work.text()+';');
                out.write(rubriks.text()+'\n');


                /*
                System.out.println(h1.text());
                System.out.println(director.text());
                System.out.println(address.text());
                System.out.println(rn.decipher());
                System.out.println(email.text());
                System.out.println(www.text());
                System.out.println(work.text());
                System.out.println(rubriks.text());
                */


                out.flush();
                System.out.print(i +" успешно завершена   ");
                System.out.print(rn.decipher()+"   ");
                System.out.println();
            }
            catch (Exception e) {
            }

        }
        out.close();

        //Парс другого сайта
        //MenuPNZ menu = new MenuPNZ();
        //menu.start();
    }


}