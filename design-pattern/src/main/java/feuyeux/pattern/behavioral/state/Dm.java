package feuyeux.pattern.behavioral.state;

import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

@Log4j2
public class Dm {
    private final Map<String, Context> contextMap = new HashMap<>();

    public void dialog(String session, String query) {
        Context context = getContext(session);
        log.info(context.hello());
        log.info("processing {} ...", query);
    }

    private Context getContext(String session) {
        Context context = contextMap.get(session);
        if (context == null) {
            context = Context.builder().state(State.NEW).build();
            contextMap.put(session, context);
        } else {
            context.setState(State.RUNNING);
        }
        return context;
    }
}
