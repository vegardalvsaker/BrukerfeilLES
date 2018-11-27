/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.PrintWriter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.http.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Espen
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

    public void getProgressXYgraphDb(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws IOException {
        String ModID1 = (SLCT_DEL_WHERE_MODID1);
        String ModID2 = (SLCT_DEL_WHERE_MODID2);
        String ModID3 = (SLCT_DEL_WHERE_MODID3);
        String ModID4 = (SLCT_DEL_WHERE_MODID4);
        String ModID5 = (SLCT_DEL_WHERE_MODID5);
        String ModID6 = (SLCT_DEL_WHERE_MODID6);
        String ModID7 = (SLCT_DEL_WHERE_MODID7);
        String ModID8 = (SLCT_DEL_WHERE_MODID8);
        String ModID9 = (SLCT_DEL_WHERE_MODID9);
        String ModID10 = (SLCT_DEL_WHERE_MODID10);
        String ModID11 = (SLCT_DEL_WHERE_MODID11);
        String ModID12 = (SLCT_DEL_WHERE_MODID12);

        HttpSession session = request.getSession();
        try {
            Connection connection = getConnection();
            PreparedStatement prepStatement = connection.prepareStatement(toString());
            ResultSet rset;
            rset = prepStatement.executeQuery();
            {

                out.println("<h1>Student Progress:</h1>");

                while (rset.next()) {

                   /* String getModID1 = rset.getString("getModID1");
                    String getModID2 = rset.getString("getModID2");
                    String getModID3 = rset.getString("getModID3");
                    String getModID4 = rset.getString("getModID4");
                    String getModID5 = rset.getString("getModID5");
                    String getModID6 = rset.getString("getModID6");
                    String getModID7 = rset.getString("getModID7");
                    String getModID8 = rset.getString("getModID8");
                    String getModID9 = rset.getString("getModID9");
                    String getModID10 = rset.getString("getModID10");
                    String getModID11 = rset.getString("getModID11");
                    String getModID12 = rset.getString("getModID12");*/

                    int IntMod1 = Integer.parseInt(ModID1);
                    int Intmod2 = Integer.parseInt(ModID2);
                    int Intmod3 = Integer.parseInt(ModID3);
                    int Intmod4 = Integer.parseInt(ModID4);
                    int Intmod5 = Integer.parseInt(ModID5);
                    int Intmod6 = Integer.parseInt(ModID6);
                    int Intmod7 = Integer.parseInt(ModID7);
                    int Intmod8 = Integer.parseInt(ModID8);
                    int Intmod9 = Integer.parseInt(ModID9);
                    int Intmod10 = Integer.parseInt(ModID10);
                    int Intmod11 = Integer.parseInt(ModID11);
                    int Intmod12 = Integer.parseInt(ModID12);
                    {
                    XYSeries series = new XYSeries("Class Progress");
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
                            "Students deliveries on module",
                            data,
                            PlotOrientation.VERTICAL,
                            true,
                            true,
                            false);
                    ChartRenderingInfo info = null;

                    // create RenderingInfo object
                    response.setContentType("text.html");
                    info = new ChartRenderingInfo(new StandardEntityCollection());
                    BufferedImage chartImage = chart.createBufferedImage(1280, 720, info);
                    session.setAttribute("chartImage", chartImage);
                    PrintWriter writer = new PrintWriter(response.getWriter());
                    ChartUtilities.writeImageMap(writer, "imageMap", info, false);
                    writer.flush();
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProgressXYgraphDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
