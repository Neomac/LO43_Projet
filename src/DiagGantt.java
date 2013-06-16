import java.util.Calendar;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;


import javax.swing.*;


public class DiagGantt /*extends ApplicationFrame*/ {

    public DiagGantt(){
        DiagGantt test = new DiagGantt();
        test.creationChart();
    }

    public DiagGantt(final String title) {

        final IntervalCategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);// //

        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);


    }

    public JPanel creationChart(){
        JPanel PanelChart = new JPanel();
        final IntervalCategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        ChartPanel ChartP = new ChartPanel(chart);
        PanelChart.add(ChartP);
        return PanelChart;
    }

    public static IntervalCategoryDataset createDataset() {


        String fichierSolution = "Solution_3.txt";
        Solution testSolution = new Solution();
        testSolution.LectureSolution(fichierSolution);

        final TaskSeriesCollection collection = new TaskSeriesCollection();

        for (int i = 0; i < testSolution.getChauffeurs().size(); i++){
            final TaskSeries tempTask = new TaskSeries("Chauffeur numero " + Integer.toString(testSolution.getChauffeurs().get(i).getNumeroChauffeur())); // Création d'une serie de tache
            for (int j =1; j < testSolution.getChauffeurs().get(i).getTachesChauffeur().size(); j++){
               tempTask.add(new Task("",new SimpleTimePeriod(testSolution.getChauffeurs().get(i).getTachesChauffeur().get(j).getHeureDepart()*1000*60 /*Passage en minutes*/, testSolution.getChauffeurs().get(i).getTachesChauffeur().get(j).getHeureArrivee()*1000*60 /* Passage en minutes*/))); // Ajout de la tache a la serie de tache
            }
            final TaskSeriesCollection tempCol = new TaskSeriesCollection(); // Création de la collection
            collection.add(tempTask); // Ajout de la serie de tache a la collection
        }

        return collection;
    }

    private static Date date(final int day, final int month, final int year) {

        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        final Date result = calendar.getTime();
        return result;

    }

    private JFreeChart createChart(final IntervalCategoryDataset dataset) {
        final JFreeChart chart = ChartFactory.createGanttChart(
                "Diagramme de Gantt des chauffeurs",  // chart title
                "Chauffeurs",              // domain axis label
                "Heures",              // range axis label
                dataset,             // data
                false,                // include legend
                true,                // tooltips
                false                // urls
        );

        return chart;
    }

}
