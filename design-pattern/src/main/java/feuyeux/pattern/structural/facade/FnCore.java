package feuyeux.pattern.structural.facade;

public class FnCore {

    public static final String QAS = "qas";

    public String fn(String result) {
        if (result.contains(QAS)) {
            return "action of fn :" + result;
        }
        return "action[] of fn :" + result;
    }
}
