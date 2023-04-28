package com.kadipe;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mock")
public class MockController {

    @GetMapping("${apiprefix.v1}")
    public ResponseEntity<Object> mockV1() {

        return new ResponseEntity<>("MOCK V1...", HttpStatus.OK);
    }

    @GetMapping("${apiprefix.v2}")
    public ResponseEntity<Object> mockV2() {

        return new ResponseEntity<>("MOCK V2...", HttpStatus.OK);
    }

}