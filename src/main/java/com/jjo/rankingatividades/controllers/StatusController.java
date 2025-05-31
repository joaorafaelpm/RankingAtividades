package com.jjo.rankingatividades.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atividade/{id}")
public class StatusController {

    @PutMapping
    public void concluirAtividade (@PathVariable Long id) {

    }

    @DeleteMapping
    public void deletarAtividade (@PathVariable Long id) {

    }

}
