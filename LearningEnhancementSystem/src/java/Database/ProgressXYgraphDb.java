/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.PrintWriter;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.awt.image.BufferedImage;
import java.sql.*;
import javax.servlet.http.HttpSession;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


/**
 *
 * @author ferra
 */
public class ProgressXYgraphDb extends Database {
    
    private static final String SLCT_DEL_WHERE_MODID1 = "select delivery_id from Delivery where module_id = 1;";
    private static final String SLCT_DEL_WHERE_MODID2 = "select delivery_id from Delivery where module_id = 2;";
    private static final String SLCT_DEL_WHERE_MODID3 = "select delivery_id from Delivery where module_id = 3;";
    private static final String SLCT_DEL_WHERE_MODID4 = "select delivery_id from Delivery where module_id = 4;";
    private static final String SLCT_DEL_WHERE_MODID5 = "select delivery_id from Delivery where module_id = 5;";
    private static final String SLCT_DEL_WHERE_MODID6 = "select delivery_id from Delivery where module_id = 6;";
    private static final String SLCT_DEL_WHERE_MODID7 = "select delivery_id from Delivery where module_id = 7;";
    private static final String SLCT_DEL_WHERE_MODID8 = "select delivery_id from Delivery where module_id = 8;";
    private static final String SLCT_DEL_WHERE_MODID9 = "select delivery_id from Delivery where module_id = 9;";
    private static final String SLCT_DEL_WHERE_MODID10 = "select delivery_id from Delivery where module_id = 10;";
    private static final String SLCT_DEL_WHERE_MODID11 = "select delivery_id from Delivery where module_id = 11;";
    private static final String SLCT_DEL_WHERE_MODID12 = "select delivery_id from Delivery where module_id = 12;";
    
    String modid1;
    int IntMod1 = Integer.parseInt(SLCT_DEL_WHERE_MODID1);
    String modid2;
    int Intmod2 = Integer.parseInt(SLCT_DEL_WHERE_MODID2);
    String modid3;
    int Intmod3 = Integer.parseInt(SLCT_DEL_WHERE_MODID3);
    String modid4;
    int Intmod4 = Integer.parseInt(SLCT_DEL_WHERE_MODID4);
    String modid5;
    int Intmod5 = Integer.parseInt(SLCT_DEL_WHERE_MODID5);
    String modid6;
    int Intmod6 = Integer.parseInt(SLCT_DEL_WHERE_MODID6);
    String modid7;
    int Intmod7 = Integer.parseInt(SLCT_DEL_WHERE_MODID7);
    String modid8;
    int Intmod8 = Integer.parseInt(SLCT_DEL_WHERE_MODID8);
    String modid9;
    int Intmod9 = Integer.parseInt(SLCT_DEL_WHERE_MODID9);
    String modid10;
    int Intmod10 = Integer.parseInt(SLCT_DEL_WHERE_MODID10);
    String modid11;
    int Intmod11 = Integer.parseInt(SLCT_DEL_WHERE_MODID11);
    String modid12;
    int Intmod12 = Integer.parseInt(SLCT_DEL_WHERE_MODID12);

    public ProgressXYgraphDb(String studentProgress) {
        super();
        
     /**
     * A demonstration application showing an XY series containing a null value.
     *
     * @param studentProgress  the frame title.
     */

        final XYSeries series = new XYSeries("Class Progress");
        series.add(1.0, IntMod1);
        series.add(2.0, Intmod2);
        series.add(3.0, Intmod3);
        series.add(4.0, Intmod4);
        series.add(5.0, Intmod5);
        series.add(6.0, Intmod6);
        series.add(7.0, Intmod7);
        series.add(8.0, Intmod8);
        series.add(9.0, Intmod9);
        series.add(10.0, Intmod10);
        series.add(11.0, Intmod11);
        series.add(12.0, Intmod12);
        final XYSeriesCollection data = new XYSeriesCollection(series);
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Student Progress",
                "Modules",
                "Student deliveries on module",
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        
BarRenderer r = (BarRenderer)chart.getCategoryPlot().getRenderer();
ChartRenderingInfo info = null;
 
HttpSession session = request.getSession();
try {
     // create RenderingInfo object
    Connection connection = getConnection();
     info = new ChartRenderingInfo(new StandardEntityCollection());
     BufferedImage chartImage = chart.createBufferedImage(640, 400, info);
     session.setAttribute("chartImage", chartImage);
     PrintWriter writer = new PrintWriter(request.getWriter());
     ChartUtilities.writeImageMap(writer, "imageMap", info, false);
     writer.flush();
} catch (Exception e) {
     e.printStackTrace();
}
        
}
    
}
