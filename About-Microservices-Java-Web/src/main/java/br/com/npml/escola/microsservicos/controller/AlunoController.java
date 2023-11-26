package br.com.npml.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AlunoController {

    Logger LOGGER = LoggerFactory.getLogger(AlunoController.class);

    public void getAll() {

    }
}
