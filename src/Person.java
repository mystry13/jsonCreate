import java.util.List;
import java.util.Map;

public class Person {
    private String fName;
    private String sName;
    private List<String> phnList;
    private Map<String, String> familyMemToRelation;

    public Person(String fName, String sName, List<String> phnList){//, List<String> phnList, Map<String, String> familyMemToRelation) {
        this.fName = fName;
        this.sName = sName;
        this.phnList = phnList;
        //this.familyMemToRelation = familyMemToRelation;
    }

    public String getfName() {
        return fName;
    }

    public String getsName() {
        return sName;
    }

    public List<String> getPhnList() {
        return phnList;
    }

    public Map<String, String> getFamilyMemToRelation() {
        return familyMemToRelation;
    }
}
