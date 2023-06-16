package com.kadipe;

import com.kadipe.fw.controller.AbstractController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mock")
public class MockController extends AbstractController {

    @GetMapping("${apiprefix.v1}")
    public ResponseEntity<Object> mockV1() {

        return new ResponseEntity<>("MOCK V1..." + timeZoneHolder.getTimeZone(), HttpStatus.OK);
    }

    @GetMapping("${apiprefix.v2}")
    public ResponseEntity<Object> mockV2() {

        return new ResponseEntity<>("MOCK V2..." + timeZoneHolder.getTimeZone(), HttpStatus.OK);
    }

}