import java.io.*;
import java.util.ArrayList;

public class AppData {
    private String[] header;
    private Integer[][] data;

    public AppData() {
    }

    public String[] getHeader() {
        return header;
    }

    public void setHead(String[] header) {
        this.header = header;
    }

    public Integer[][] getData() {
        return data;
    }

    public void setBody(Integer[][] data) {
        this.data = data;
    }

    public void save(String file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            String result = "";
            for (int i = 0; i < header.length; i++) {
                result = result + header[i].toString();
                if (i != header.length - 1) {
                    result += ";";
                }
            }
            result += "\n";
            writer.write(result);
            for (Integer[] row : data) {
                String resultData = "";
                for (int i = 0; i < data.length; i++) {
                    resultData = resultData + data[i].toString();
                    if (i != data.length - 1) {
                        resultData += ";";
                    }
                }
                resultData += "\n";
                writer.write(resultData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(String file) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            header = bufferedReader.readLine().split(";");
            ArrayList<Integer[]> dataAppData = new ArrayList<>();
            String tempString;
            while ((tempString = bufferedReader.readLine()) != null) {
                String[] strData = tempString.split(";");
                Integer[] intData = new Integer[strData.length];
                for (int i = 0; i < strData.length; i++) {
                    intData[i] = Integer.parseInt(strData[i]);
                }
                dataAppData.add(intData);
            }
            data = dataAppData.toArray(new Integer[][]{{}});
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
