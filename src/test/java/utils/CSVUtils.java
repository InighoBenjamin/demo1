package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {

    public static Object[][] readCSV(String filePath) {

        List<Object[]> dataList = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {

                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] data = line.split(",");

                String email = data[0];
                String password = data[1];

                dataList.add(new Object[]{email, password});
            }

            br.close();

        } catch (Exception e) {
            System.out.println("CSV file reading error: " + e.getMessage());
        }

        return dataList.toArray(new Object[0][0]);
    }
}
