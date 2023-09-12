package com.api.school.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600) // o controlador irá aceitar todas as solicitações
@RequestMapping("/school")
public class SchoolController {

}

//A anotação @CrossOrigin(origins = "*", maxAge = 3600) é usada quando você está criando uma API web. Ela define as regras para quem pode acessar sua API e quanto tempo essas regras são lembradas.
//
//origins = "*": Isso significa que qualquer site na internet pode usar sua API. É como deixar a porta aberta para todos.
//
//maxAge = 3600: Isso diz ao navegador que ele pode se lembrar dessas regras por 1 hora. Depois disso, ele verifica novamente.
//
//Basicamente, essa anotação permite que qualquer site acesse sua API, mas o navegador só guarda essas regras por um tempo limitado, para manter a segurança. É útil quando você quer compartilhar dados da sua API com outros sites.