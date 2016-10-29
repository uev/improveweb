package improve.web.improve.jsf.misc;

import lombok.extern.java.Log;
import org.primefaces.context.PrimeFacesContextFactory;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import java.util.Map;

/**
 * Created by uev on 02.10.16.
 */
@Log
public class JSFPhaseListener implements PhaseListener {
    @Override
    public void afterPhase(PhaseEvent phaseEvent) {

    }

    @Override
    public void beforePhase(PhaseEvent phaseEvent) {
        log.info("ENTER");
        Map<String,String> params = phaseEvent.getFacesContext().getExternalContext().getRequestParameterMap();
        log.info("STOP");
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.PROCESS_VALIDATIONS;
    }
}
