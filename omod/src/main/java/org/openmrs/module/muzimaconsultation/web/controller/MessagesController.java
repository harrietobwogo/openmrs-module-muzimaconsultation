package org.openmrs.module.muzimaconsultation.web.controller;

import org.openmrs.Person;
import org.openmrs.api.context.Context;
import org.openmrs.module.muzima.api.service.DataService;
import org.openmrs.module.muzima.model.MessageData;
import org.openmrs.module.muzimaconsultation.web.utils.MessageDataConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessagesController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @RequestMapping("module/muzimaconsulation/messages.json")
    @ResponseBody
    public Map<String, Object> getMessagesForSender(final @RequestParam(value = "pageNumber", required = true) Integer pageNumber,
                                                    final @RequestParam(value = "pageSize", required = true) Integer pageSize,
                                                    final @RequestParam(value = "sender") Person sender) {

        DataService dataService = Context.getService(DataService.class);
        List<MessageData> messageDataList = new ArrayList<>();
        List<Object> objects = new ArrayList<>();

        messageDataList = dataService.getMessageDataBySender(sender);
        int pages = (messageDataList.size()) + pageSize - 1 / pageSize;


        for (MessageData messageData : messageDataList) {
            objects.add(MessageDataConverter.convert(messageData));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("objects", objects);
        response.put("pages", pages);
        return response;

    }

    @RequestMapping("module/muzimaconsultation/messages.json")
    @ResponseBody
    public Map<String, Object> getMessageByReceiver(final @RequestParam(value = "pageNumber", required = true) Integer pageNumber,
                                                    final @RequestParam(value = "pageSize", required = true) Integer pageSize,
                                                    final @RequestParam(value = "receiver") Person receiver) {
        DataService dataService = Context.getService(DataService.class);
        List<MessageData> messageDataList = new ArrayList<>();
        List<Object> objects = new ArrayList<>();

        messageDataList = dataService.getMessageDataByReceiver(receiver);
        int pages = (messageDataList.size()) + pageSize - 1 / pageNumber;

        for (MessageData messageData : messageDataList) {
            objects.add(MessageDataConverter.convert(messageData));
        }

        Map<String,Object> response = new HashMap<>();
        response.put("objects",objects);
        response.put("pages",pages);
        return response;
    }
}
