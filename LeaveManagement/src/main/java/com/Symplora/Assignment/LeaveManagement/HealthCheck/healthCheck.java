package com.Symplora.Assignment.LeaveManagement.HealthCheck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthCheck {

    @GetMapping("/health")
    public HelloWorld health() {
        return new HelloWorld("Hello World!");
    }

    @GetMapping("/health/{name}")
    public HelloWorld healthCheck(@PathVariable String name) {
        return new HelloWorld("Hello World! " + name);
    }
}
