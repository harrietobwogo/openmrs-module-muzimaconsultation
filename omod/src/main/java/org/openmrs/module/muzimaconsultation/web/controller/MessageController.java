package org.openmrs.module.muzimaconsultation.web.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.muzima.api.service.DataService;
import org.openmrs.module.muzimaconsultation.api.MessageService;
import org.openmrs.module.muzimaconsultation.api.model.Message;
import org.openmrs.module.muzimaconsultation.web.utils.MessageDataConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("module/muzimaconsulation/message.json")
public class MessageController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    //crude

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
    public Map<String,Object> getMessageDataById(Integer id){
        DataService dataService = Context.getService(DataService.class);
        MessageData messageData = dataService.getMessageDataById(id);
        MessageDataConverter messageDataConverter = new MessageDataConverter();
        return messageDataConverter.convert(messageData);
    }
}
