package idx.kafka.producer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
class TokenBodyResponsesController {

    private final TokenBodyResponsesInterface repository;
    private final IdxKafkaProducer idxKafkaProducer;


    TokenBodyResponsesController(TokenBodyResponsesInterface repository, IdxKafkaProducer idxKafkaProducer) {
        this.repository = repository;
        this.idxKafkaProducer = idxKafkaProducer;
    }


    @PostMapping("/v1/idx/cron")
    TokenBodyResponses newItem(@RequestBody TokenBodyResponses data) throws IOException {
        idxKafkaProducer.sendToken(data.getToken(), data.getTopic());
        return data;
    }

}
