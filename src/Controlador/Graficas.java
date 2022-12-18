package Controlador;

import Vista.Contenido;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class Graficas extends javax.swing.JFrame {
    
    /* Graphics Comparison */
    private static final long serialVersionUID = 1L; 
 
    public Graficas(String title, int operation) {   
        super(title);
        
        // Create dataset 
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String operationTitle = "";
        switch(operation){
            case 1: operationTitle = "Operaciones Cifrado (Encrypt Operations)";
                dataset = createDataset1();
                break;
            case 2: operationTitle = "Operaciones Descifrado (Decrypt Operations)";
                dataset = createDataset2();
                break;
            case 3: operationTitle = "Operaciones Hash (Hash Operations)";
                dataset = createDataset3();
                break;
            case 4: operationTitle = "Operaciones Firma (Signing Operations)";
                dataset = createDataset4();
                break;
            case 5: operationTitle = "Operaciones Verificación (Verifying Operations)";
                dataset = createDataset5();
                break;
        }
        // Create chart 
        JFreeChart chart = ChartFactory.createLineChart( 
        operationTitle,
        "Vectores Prueba",
        "Tiempo (ms)",
        dataset 
        ); 

        ChartPanel panel = new ChartPanel(chart); 
        setContentPane(panel); 
        } 

    public static DefaultCategoryDataset createDataset1() { 
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        /*                Lectura de tiempo                  */
        for (String[] TestVectorEncryption : Algoritmos.TestVectorEncryption) {
            String testVector = "";
            for (int j = 0, aux = 0; j < TestVectorEncryption.length ; j++, aux++) {
                if(TestVectorEncryption[j].indexOf("Vector") != -1)
                    testVector = TestVectorEncryption[j];
                if(aux >= 5)
                    aux = 0;
                if(TestVectorEncryption[j].indexOf("Vector") == -1){
                    String time = TestVectorEncryption[j].replaceAll("[[a-z]+]", "");
                    dataset.addValue(Float.valueOf(time), Contenido.cabeceraEncryption[aux], testVector);
                }
            }
        }
        return dataset; 
    }
    
    public static DefaultCategoryDataset createDataset2() { 
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        /*                Lectura de tiempo                  */
        for (String[] TestVectorDecryption : Algoritmos.TestVectorDecryption) {
            String testVector = "";
            for (int j = 0, aux = 0; j < TestVectorDecryption.length ; j++, aux++) {
                if(TestVectorDecryption[j].indexOf("Vector") != -1)
                    testVector = TestVectorDecryption[j];
                if(aux >= 5)
                    aux = 0;
                if(TestVectorDecryption[j].indexOf("Vector") == -1){
                    String time = TestVectorDecryption[j].replaceAll("[[a-z]+]", "");
                    dataset.addValue(Float.valueOf(time), Contenido.cabeceraDecryption[aux], testVector);
                }
            }
        }
        return dataset; 
    }
    
    public static DefaultCategoryDataset createDataset3() { 
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        /*                Lectura de tiempo                  */
        for (String[] TestVectorHash : Algoritmos.TestVectorHash) {
            String testVector = "";
            for (int j = 0, aux = 0; j < TestVectorHash.length ; j++, aux++) {
                if(TestVectorHash[j].indexOf("Vector") != -1)
                    testVector = TestVectorHash[j];
                if(aux >= 5)
                    aux = 0;
                if(TestVectorHash[j].indexOf("Vector") == -1){
                    String time = TestVectorHash[j].replaceAll("[[a-z]+]", "");
                    dataset.addValue(Float.valueOf(time), Contenido.cabeceraHash[aux], testVector);
                }
            }
        }
        return dataset; 
    }
    
    public static DefaultCategoryDataset createDataset4() { 
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        /*                Lectura de tiempo                  */
        for (String[] TestVectorSigning : Algoritmos.TestVectorSigning) {
            String testVector = "";
            for (int j = 0, aux = 0; j < TestVectorSigning.length ; j++, aux++) {
                if(TestVectorSigning[j].indexOf("Vector") != -1)
                    testVector = TestVectorSigning[j];
                if(aux >= 4)
                    aux = 0;
                if(TestVectorSigning[j].indexOf("Vector") == -1){
                    String time = TestVectorSigning[j].replaceAll("[[a-z]+]", "");
                    dataset.addValue(Float.valueOf(time), Contenido.cabeceraSigning[aux], testVector);
                }
            }
        }
        return dataset; 
    }
    
    public static DefaultCategoryDataset createDataset5() { 
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        /*                Lectura de tiempo                  */
        for (String[] TestVectorVerifying : Algoritmos.TestVectorVerifying) {
            String testVector = "";
            for (int j = 0, aux = 0; j < TestVectorVerifying.length ; j++, aux++) {
                if(TestVectorVerifying[j].indexOf("Vector") != -1)
                    testVector = TestVectorVerifying[j];
                if(aux >= 4)
                    aux = 0;
                if(TestVectorVerifying[j].indexOf("Vector") == -1){
                    String time = TestVectorVerifying[j].replaceAll("[[a-z]+]", "");
                    dataset.addValue(Float.valueOf(time), Contenido.cabeceraVerifying[aux], testVector);
                }
            }
        }
        return dataset; 
    }
        
    public static void activarGrafico(int operation){
        SwingUtilities.invokeLater(() -> { 
            Graficas gCifrado= new Graficas("Comparative Graph", operation); 
            gCifrado.setAlwaysOnTop(true); 
            gCifrado.pack(); 
            gCifrado.setSize(600, 400); 
            gCifrado.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
            gCifrado.setVisible(true); 
        });
    }
}
