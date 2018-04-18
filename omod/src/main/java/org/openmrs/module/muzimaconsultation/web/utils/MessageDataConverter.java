package org.openmrs.module.muzimaconsultation.web.utils;

import org.openmrs.Person;
import org.openmrs.module.muzima.model.MessageData;

import java.util.HashMap;
import java.util.Map;

public class MessageDataConverter {

    public static Map<String,Object> convert(final MessageData messageData){
        Map<String,Object> converted = new HashMap<String, Object>();
        if (messageData!=null){
            converted.put("uuid",messageData.getUuid());
            converted.put("subject",messageData.getSubject());
            converted.put("body",messageData.getBody());
            converted.put("sendDate",messageData.getSenderDate());
            converted.put("sendTime",messageData.getSenderTime());
            converted.put("voided",messageData.getVoided());

            Map<String,Object> senderMap = new HashMap<String, Object>();
            Person sender = messageData.getSender();

            if (sender != null){
                senderMap.put("uuid",sender.getUuid());
                senderMap.put("name",sender.getPersonName().getFullName());
                converted.put("sender",senderMap);
            }


            Map<String,Object> receiverMap = new HashMap<String, Object>();
            Person receiver = messageData.getReceiver();

            if (receiver != null){
                receiverMap.put("uuid",receiver.getUuid());
                receiverMap.put("name",receiver.getPersonName().getFullName());
                converted.put("receiver",receiverMap);
            }

        }
        return converted;
    }
}
