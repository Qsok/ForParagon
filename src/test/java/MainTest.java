/**
 * Created by Kru on 30.03.2016.
 */

import org.junit.Test;
import java.util.*;

import org.apache.commons.io.IOUtils;
import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void readFromFileToStr() throws Exception {
        String str1 = IOUtils.toString(
                this.getClass().getResourceAsStream("abc.txt"),
                "UTF-8").replace("\uFEFF","");
        String str2 = Main.readFromFileToStr("src\\test\\resources\\abc.txt");
        assertEquals("Strings should be equals:",str1.trim(), str2.trim());
    }

    @Test
    public void sortByValue() {
        Map hashMap = new HashMap<String, Integer>();
        String str;
        int value;
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < 100; i++) {
            value = r.nextInt(30);
            str = "randString" + String.valueOf(value);
            if (hashMap.containsValue(value))
                str += "uniqName" + String.valueOf(i);
            hashMap.put(str, value);
        }

        List<Map.Entry<String, Integer>> list = Main.sortByValue(hashMap);
        boolean correctOrder = true;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).getValue() < list.get(i + 1).getValue()) {
                correctOrder = false;
                break;
            }
        }
        assertEquals("In hashMap should be descending order by value ", correctOrder, true);
    }


}
