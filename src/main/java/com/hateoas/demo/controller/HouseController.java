package com.hateoas.demo.controller;

import com.hateoas.demo.common.Poll;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

public class HouseController {

    @ExceptionHandler(SQLException.class)
    public ResponseEntity handleSQLException() {
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

    @GetMapping("/stream")
    public void streamMovie(HttpServletResponse response) throws SQLException {
        throw new SQLException();
    }

    @PostMapping
    public ResponseEntity<?> createPoll(@RequestBody Poll poll) {
        poll = new Poll();
        return new ResponseEntity<>((Object) null, HttpStatus.CREATED);
    }

    @PutMapping("/polls/{pollId}")
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {
        // save
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
