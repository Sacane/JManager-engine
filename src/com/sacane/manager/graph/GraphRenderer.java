package com.sacane.manager.graph;

import com.sacane.manager.wrapper.ModelWrapper;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.data.comparators.Ascending;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
import javax.swing.*;
import java.awt.*;



public class GraphRenderer extends JFrame {

    private final ModelWrapper wrapper;

    public GraphRenderer(ModelWrapper wrapper){
        this.wrapper = wrapper;
        setSize(800, 600);
        createTable();

    }

    private void createTable(){
        DataSet dataSet= new DataSet(wrapper);

        //Initialization of the table containing the data
        DataTable table = dataSet.getTableFromDate();
        table.sort(new Ascending(0));
        XYPlot plot = new XYPlot(table);


        //Configuration of the plot
        LineRenderer lines = new DefaultLineRenderer2D();

        Color color = new Color(0.0f, 0.3f, 1.0f);
        plot.setLineRenderers(table, lines);
        plot.getPointRenderers(table).get(0).setColor(color);
        plot.getLineRenderers(table).get(0).setColor(color);
        getContentPane().add(new InteractivePanel(plot));
    }

    public static void render(ModelWrapper wrapper){
        var render = new GraphRenderer(wrapper);
        render.setVisible(true);
    }

}
