package com.co.animalcentral.modulo2.services;

import com.co.animalcentral.modulo2.models.PetCredit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class); //para obtener los logs
    private final KafkaTemplate<String, String> kafkatemplate;


    public KafkaProducerService(KafkaTemplate<String, String> kafkatemplate) {
        this.kafkatemplate = kafkatemplate;
    }

    public void send(String topic, Integer key, PetCredit petcredit) {
        var future = kafkatemplate.send(topic, key.toString(), petcredit.toString());

        future.whenComplete((resultadoEnvio, excepcion) -> {
            if (excepcion != null) {
                LOGGER.error(excepcion.getMessage());
                future.completeExceptionally(excepcion);
            } else {
                future.complete(resultadoEnvio);
            }
            LOGGER.info("Credito enviado al tópico de Kafka" + petcredit);
        });


    }
}
