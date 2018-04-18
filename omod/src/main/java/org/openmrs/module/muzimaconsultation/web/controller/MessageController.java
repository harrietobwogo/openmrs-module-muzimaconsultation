package org.openmrs.module.muzimaconsultation.web.controller;

import org.openmrs.Person;
import org.openmrs.api.context.Context;
import org.openmrs.module.muzima.api.service.DataService;
import org.openmrs.module.muzima.model.MessageData;
import org.openmrs.module.muzimaconsultation.web.utils.MessageDataConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("module/muzimaconsulation/message")
public class MessageController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private  Integer tempCount = 0;


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getMessagesByUuid(final @RequestParam(required = true) String uuid){
        DataService dataService = Context.getService(DataService.class);
        MessageData messageData = dataService.getMessageDataByUuid(uuid);
        MessageDataConverter messageDataConverter = new MessageDataConverter();
        return messageDataConverter.convert(messageData);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getMessageDataById(final @RequestParam(required = true) Integer id){
        DataService dataService = Context.getService(DataService.class);
        MessageData messageData = dataService.getMessageDataById(id);
        MessageDataConverter messageDataConverter = new MessageDataConverter();
        return messageDataConverter.convert(messageData);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void saveMessage(Map<String,Object> requestData){
        if (Context.isAuthenticated()){
            Integer id = Integer.valueOf(String.valueOf(requestData.get("id")));
            String uuid = String.valueOf(requestData.get("uuid"));
            Person sender = (Person)requestData.get("sender");
            Person receiver = (Person)requestData.get("receiver");
            String subject = String.valueOf(requestData.get("subject"));
            String payload = String.valueOf(requestData.get("payload"));
            String body = String.valueOf(requestData.get("body"));
            String senderDate = String.valueOf(requestData.get("senderDate"));
            String senderTime = String.valueOf(requestData.get("senderTime"));

            MessageData messageData = new MessageData();
            messageData.setId(id);
            messageData.setUuid(uuid);
            messageData.setSender(sender);
            messageData.setReceiver(receiver);
            messageData.setSubject(subject);
            messageData.setBody(body);
            messageData.setSenderDate(senderDate);
            messageData.setSenderTime(senderTime);

            DataService dataService = Context.getService(DataService.class);
            dataService.saveMessageData(messageData);
        }
    }

}
