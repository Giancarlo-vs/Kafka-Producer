package com.co.animalcentral.modulo2;


import com.co.animalcentral.modulo2.models.PetCredit;
import com.co.animalcentral.modulo2.services.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {
    @Autowired
    private final KafkaProducerService kafkaProducerService;//Inyectamos servicio creado

    public MainApplication(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;    //inyectamos serv en constructor
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    //hacemos sobrecarga del metodo run para crear objetos quemados:
    @Override
    public void run(String... args) throws Exception {
        producerData();

    }

    private void producerData() {
        PetCredit petCredit1 = new PetCredit(23, Boolean.TRUE, 200.00, 0.0, "I wanna buy a zebra");
        PetCredit petCredit2 = new PetCredit(24, Boolean.TRUE, 200.00, 0.0, "I wanna buy a tiger");
        PetCredit petCredit3 = new PetCredit(25, Boolean.TRUE, 200.00, 0.0, "I wanna buy a dog");

        String topico = "Creditos-para-adquirir-mascotas-2023-07";

        kafkaProducerService.send(topico, petCredit1.getIdPet(), petCredit1);
        kafkaProducerService.send(topico, petCredit2.getIdPet(), petCredit2);
        kafkaProducerService.send(topico, petCredit3.getIdPet(), petCredit3);

    }


}
