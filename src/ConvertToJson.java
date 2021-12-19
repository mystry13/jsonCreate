import java.util.ArrayList;
import java.util.List;

public class ConvertToJson {
    public static void main(String[] args) throws Exception {
        List<String> nums = new ArrayList<>();
        nums.add("1234");
        nums.add("3421");

        Person p = new Person("Deepika", "Goel", nums);
        JsonUtil j = new JsonUtil();
        System.out.println(j.serializeJson(p));
    }
}
