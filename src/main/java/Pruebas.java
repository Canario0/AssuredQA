import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.*;

public class Pruebas {
    public static void main (String args[]){
        try {
            InputStream z =  new FileInputStream(new File("C:\\Users\\pablo.renero\\IdeaProjects\\AssuredQA\\src\\test\\resources\\requests\\put_pet.json"));
            JSONObject aux = new JSONObject(IOUtils.toString(z, "UTF-8"));
            aux.getJSONArray("tags").put("hola");
            aux.getJSONArray("photoUrls").put("hola3");
            System.out.println(aux.toString());
            aux.put("id", 300);
            System.out.println(aux.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
