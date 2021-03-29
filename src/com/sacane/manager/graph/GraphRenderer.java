package com.sacane.manager.graph;

import com.sacane.manager.wrapper.ModelWrapper;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.ui.InteractivePanel;
import javax.swing.*;
import java.awt.*;



public class GraphRenderer extends JFrame {

    private final ModelWrapper wrapper;
    public GraphRenderer(ModelWrapper wrapper){
        this.wrapper = wrapper;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        createTable();

    }

    private void createTable(){
        DataSet dataSet= new DataSet(wrapper);

        //Initialization of the table containing the data
        DataTable table = dataSet.getTableFromDate();

        System.out.println(table.getRowCount());
        XYPlot plot = new XYPlot(table);


        //Configuration of the plot

        Color color = new Color(0.0f, 0.3f, 1.0f);
        plot.getPointRenderers(table).get(0).setColor(color);
        getContentPane().add(new InteractivePanel(plot));
    }

    public static void renderer(ModelWrapper wrapper){
        var render = new GraphRenderer(wrapper);
        render.setVisible(true);
    }

}
