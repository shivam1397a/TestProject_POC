package Steps;

import io.cucumber.java.an.E;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class orTEST {
    public static void main(String[] args) throws Throwable {
        InputStream inputStream = new FileInputStream(new File("src/test/resources/OR.yml"));
        Yaml yaml = new Yaml();
        Map<String, Object> data = yaml.load(inputStream);
        String value = data.get("HomePage").toString();
        if (value.startsWith("{") && value.endsWith("}")) {
            value = value.substring(1, value.length()-1);
            System.out.println(value);
            String[] loc= value.split("=",2);
            String locs = loc[1];
            locs = locs.substring(1, locs.length()-1);
            System.out.println(locs);
            String[] vals = locs.split(", ",2);
            System.out.println(vals.length);

        }
    }

}
