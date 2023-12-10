package br.com.npml.aluno.microsservicos;

import info.schnatterer.mobynamesgenerator.MobyNamesGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;
import java.util.Random;

@SpringBootApplication
public class AlunoBackendApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(AlunoBackendApplication.class);
        Properties properties = new Properties();
        int serverPort = new Random().nextInt(8050, 8070);
        properties.put("spring.application.serverNick", MobyNamesGenerator.getRandomName()/*appNickName*/);
        properties.put("serverPort", serverPort);
        application.setDefaultProperties(properties);
        application.run(args);
    }
}
