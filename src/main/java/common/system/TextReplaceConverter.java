package common.system;



import org.apache.log4j.Logger;

import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;


@Service
public class TextReplaceConverter {
    Logger logger = Logger.getLogger(TextReplaceConverter.class);
    List<AbstractMap.SimpleEntry<String, String>> replace_dict = new ArrayList<>();
    public TextReplaceConverter( String replace_dict_str) {
        List<String> lines = FileUtils.getFileLines(replace_dict_str);
        for(String line: lines) {
            String[] arr = line.split(",");
            arr[0] = arr[0].replace("\\n","\n");
            if(arr.length == 1)replace_dict.add(new AbstractMap.SimpleEntry<>(arr[0], ""));
            else if(arr.length == 2) {
                arr[1] = arr[1].replace("\\n", "\n");
                replace_dict.add(new AbstractMap.SimpleEntry<>(arr[0], arr[1]));
            }
        }
    }

    public String converter(String text) {
        for(AbstractMap.SimpleEntry<String, String> entry: replace_dict) {
            text = text.replace(entry.getKey(), entry.getValue());
        }
        return text;
    }
}
