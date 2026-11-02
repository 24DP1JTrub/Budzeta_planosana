import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderWriter {
    // metode nolasa csv failu un atgriez ka sarakstu ar string masivu
    public static List<String[]> readCSV(String filePath) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); 
                data.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    // metode, lai ierakstit csv failaa
    public static void writeCSV(String filePath, String[] header, List<String[]> data) {
        try (FileWriter fw = new FileWriter(filePath)) {
            
            fw.write(String.join(",", header) + "\n");
            // ierakstam datus
            for (String[] row : data) {
                fw.write(String.join(",", row) + "\n"); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
