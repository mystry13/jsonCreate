import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonUtil <T> {
    LinkedList<String> linkedList = new LinkedList<>();

    public T serializeJson(T object) throws Exception {
        try {
            linkedList.add("{,");
            Class<? extends Object> clazz = object.getClass();
            // Iterate the attribute array of the class
            for (Field attribute : clazz.getDeclaredFields()) {
                attribute.setAccessible(true);
                //System.out.println(attribute.getType());
                if (attribute.getType().equals(String.class)) {
                    addPrimitive(attribute, object);
                } else if(attribute.getType().equals(List.class)) {
                    addListElems(attribute, object);
                }

                attribute.setAccessible(false);
            }
            linkedList.add(",}");
            return (T) linkedList.stream().collect(Collectors.joining(",")).replaceAll(",,", "");
        } catch (Exception e) {
            throw new Exception("JSON serialization exception");
        }
    }

    private void addListElems(Field attribute, T object) throws IllegalAccessException {
        String attributeName = attribute.getName();
        LinkedList<String> listVals = new LinkedList<>();
        List<String> attributeValue = (List<String>) attribute.get(object);
        if(attributeValue != null){
            listVals.add("\"" + attributeName + "\": [");
            for(Object val : attributeValue) {
                listVals.add("\"" + val + "\"");
            }
            listVals.add(",]");
        }
        linkedList.add(listVals.stream().collect(Collectors.joining(",")).replaceAll(",,", ""));
    }

    private void addPrimitive(Field attribute, T object) throws IllegalAccessException {
        Object attributeValue = attribute.get(object);
        String attributeName = attribute.getName();
        if(attributeValue != null){
            linkedList.add("\"" + attributeName + "\":\"" + attributeValue + "\"");
        }
    }
}
