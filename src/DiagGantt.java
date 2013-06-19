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


public class DiagGantt {

    private static Solution testSolution = new Solution();

    public DiagGantt(){
        DiagGantt test = new DiagGantt();
        test.creationChart("");
    }

    public DiagGantt(final String title, String nomFichier) {

        final IntervalCategoryDataset dataset = createDataset(nomFichier);
        final JFreeChart chart = createChart(dataset);

        final ChartPanel chartPanel = new ChartPanel(chart);


    }

    public JPanel creationChart(String nomFichier){
        JPanel PanelChart = new JPanel();
        final IntervalCategoryDataset dataset = createDataset(nomFichier);
        final JFreeChart chart = createChart(dataset);
        ChartPanel ChartP = new ChartPanel(chart);
        PanelChart.add(ChartP);
        return PanelChart;
    }

    public static IntervalCategoryDataset createDataset(String nomFichier) {


        testSolution.LectureSolution(nomFichier);

        final TaskSeriesCollection collection = new TaskSeriesCollection(); // Création de la collection

        if (nomFichier == "") {
            final TaskSeries nullTask = new TaskSeries("");
            collection.add(nullTask);
        }
        else {
            int i = 0;
            while (i < testSolution.getChauffeurs().size()){
                int j = 0;
                TaskSeries tempTask = new TaskSeries(Integer.toString(testSolution.getChauffeur(i).getNumeroChauffeur())); // Création d'une serie de tache
                while(j < testSolution.getChauffeurs().get(i).getTachesChauffeur().size()){
                    Task temp = new Task("Chauffeur numero " + Integer.toString(testSolution.getChauffeur(i).getNumeroChauffeur()),new SimpleTimePeriod(testSolution.getChauffeurs().get(i).getTachesChauffeur().get(j).getHeureDepart()*1000*60 /*Passage en minutes*/, testSolution.getChauffeurs().get(i).getTachesChauffeur().get(j).getHeureArrivee()*1000*60 /* Passage en minutes*/));
                    tempTask.add(temp); // Ajout de la tache a la serie de tache
                j++;
                }
                collection.add(tempTask); // Ajout de la serie de tache a la collection
                i++;
            }
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
                true,                // include legend
                true,                // tooltips
                false                // urls
        );

        return chart;
    }

}
