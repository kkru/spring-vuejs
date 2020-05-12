package app.messages.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import app.messages.model.Message;
import app.messages.repository.MessageRepository;
import app.messages.security.SecurityCheck;

@Component
public class MessageService {

    private MessageRepository repository;

    private static final Logger log = LoggerFactory.getLogger(MessageService.class);

    public MessageService (MessageRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Message> getMessages() {
        return repository.getMessages();
    }

    @SecurityCheck
    @Transactional()
    public Message save(String text) {
        return repository.saveMessage(new Message(text));
    }

    private void updateStatistics() {
        throw new UnsupportedOperationException("This method is not implemented yet");
    }
}