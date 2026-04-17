package com.jean.superlanch.addon;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addons")
public class AddonController {

    private final AddonService service;

    public AddonController(AddonService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddonResponse create(@RequestBody @Valid CreateAddonRequest request){
        return service.create(request);
    }
}
