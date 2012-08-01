/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package par4cat;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author 4ui
 */

/*
 * menupnz
 */
public class MenuPNZ {
    //"?orgItemListPager=" +n "+&event=paginate&sender=orgItemListPager"
    public MenuPNZ() {
    }

    public void start() throws IOException{
        FileWriter fstreamPNZ = new FileWriter("res/MenuPNZ_strip.csv");
        BufferedWriter outPNZ = new BufferedWriter(fstreamPNZ);
            for (int n = 1 ; n < 2; n++) {
                try {
                Document doc = Jsoup.connect("http://menupnz.ru/iwant/striptease/?orgItemListPager=" + n + "+&event=paginate&sender=orgItemListPager").timeout(0).get();

                Element content = doc.getElementById("contentPlace");
                Elements linksH2 = content.select("h2").select("a[href]");

                for (Element link : linksH2) {
                    Document doc2 = Jsoup.connect("http://menupnz.ru/" + link.attr("href")).timeout(0).get();
                    Elements html = doc2.getElementsByClass("annotation");
                    Elements header = doc2.getElementById("contentHeader").select("h1");
                    
                    System.out.println(header.text() + " " + html.text());
                    outPNZ.write(header.text() + "; ");
                    outPNZ.write(html.text());
                    outPNZ.write("\n");
                    outPNZ.flush();
                }


                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        
        
        outPNZ.close();
    }
}
