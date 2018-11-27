package csv;
import exceptions.IncorrectDataException;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    public static void parseDocument(@NotNull File csvFile, @NotNull CSVReaderCallback callback) {
        String line = "";
        String cvsSplitBy = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            Integer counter = 0;
            while ((line = br.readLine()) != null) {
                CSVwrapper wrapper = null;
                if (counter >0) {
                    String[] arg = line.split(cvsSplitBy);
                    try {
                        wrapper = new CSVwrapper(counter, arg[0], arg[1], arg[2], arg[3], arg[4], arg[5], arg[6], arg[7], arg[8], arg[9], arg[10], arg[11]);
                        callback.process(wrapper);
                    } catch (IncorrectDataException ex){
                        System.out.println("CSVReader: Error while parsing csv file at line " + counter.toString() );
                    }
                }
                counter++;
            }
            System.out.println("Document was read");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public interface CSVReaderCallback {
        void process(CSVwrapper wrapper);
    }
}
