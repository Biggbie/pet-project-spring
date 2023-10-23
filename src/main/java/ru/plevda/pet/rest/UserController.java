package ru.plevda.pet.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.plevda.pet.entity.rest.User;
import ru.plevda.pet.entity.rest.UserResponse;
import ru.plevda.pet.service.UserService;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/api/test/user")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserResponse save(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public UserResponse upload(@PathVariable Long id, MultipartFile file) throws IOException {
        return userService.upload(id, file);
    }

    @GetMapping("/{name}")
    @ResponseBody
    public User get(@PathVariable String name) {
        return userService.get(name);
    }

}

