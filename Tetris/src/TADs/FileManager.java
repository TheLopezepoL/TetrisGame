package TADs;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


public class FileManager {

    // read txt file, para ller el query
    public static String readFile(String path) throws FileNotFoundException, IOException {
        String everything;

        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
        }
        return everything;
    }

    // escribe un objeto
    public static void writeObject(Object obj, String filePath) {
        try {
            //use buffering
            OutputStream file = new FileOutputStream(filePath);
            OutputStream buffer = new BufferedOutputStream(file);
            try (ObjectOutput output = new ObjectOutputStream(buffer)) {
                // escribe el objeto
                output.writeObject(obj);
            }
        }
        catch(IOException ignored){}
    }

    // escribe un objeto
    public static Object readObject(String filePath) {
        try {
            //use buffering
            InputStream file = new FileInputStream(filePath);
            InputStream buffer = new BufferedInputStream(file);
            try (ObjectInput input = new ObjectInputStream(buffer)) {
                //deserialize the List
                return input.readObject();
            }
        }
        catch(ClassNotFoundException | IOException ignored){}
        return null;
    }
}
