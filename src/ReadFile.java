
/**
*helper class to readfiles
*
* @author Celimpilo Manqele
* @version 1.0
*/
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadFile {
    File myObj;
    Scanner myReader;
    Float[] list;
    int arraySize = 0;

    /**
     * constructor method to open files
     *
     * @param file name
     */

    ReadFile(String fileName) throws FileNotFoundException {
        myObj = new File("Files/" + fileName);
        myReader = new Scanner(myObj);

    }

    /**
     * method to read in the array
     */
    void array() throws FileNotFoundException {
        if (myReader.hasNextLine()) {
            String sizeString = myReader.nextLine();
            arraySize = Integer.parseInt(sizeString);
        }
        list = new Float[arraySize];
        int position = 0;
        while (myReader.hasNextLine() && position < arraySize) {
            String data = myReader.nextLine();
            int indspace = data.indexOf(" ");
            int size = data.length();
            this.list[position++] = Float.parseFloat(data.substring(indspace + 1, size).replace(",", "."));
        }
        myReader.close();
    }

    /**
     * Method to get the array
     *
     * returns tree
     */
    public Float[] getList() throws FileNotFoundException {
        array();
        return this.list;
    }

}