package org.openmrs.module.muzimaconsultation.handler;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Encounter;
import org.openmrs.annotation.Handler;
import org.openmrs.api.context.Context;
import org.openmrs.module.muzima.exception.QueueProcessorException;
import org.openmrs.module.muzima.model.QueueData;
import org.openmrs.module.muzima.model.handler.QueueDataHandler;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Component
@Handler(supports = QueueData.class, order = 50)
public class MessageQueueDataHandler implements QueueDataHandler {

    private static final String DISCRIMINATOR_VALUE = "json-message";

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private final Log log = LogFactory.getLog(MessageQueueDataHandler.class);

    private QueueProcessorException queueProcessorException;

    private String payload;

    private Encounter encounter;


    @Override
    public void process(QueueData queueData) throws QueueProcessorException {
        payload = queueData.getPayload();

        if (validate(queueData)){
            Context.getEncounterService().saveEncounter(encounter);
        }
    }

    @Override
    public boolean accept(QueueData queueData) {
        return StringUtils.equals(DISCRIMINATOR_VALUE, queueData.getDiscriminator());
    }

    @Override
    public boolean validate(QueueData queueData) {
        return StringUtils.equals(DISCRIMINATOR_VALUE, queueData.getDiscriminator());
    }

    @Override
    public String getDiscriminator() {
        return DISCRIMINATOR_VALUE;
    }
}
