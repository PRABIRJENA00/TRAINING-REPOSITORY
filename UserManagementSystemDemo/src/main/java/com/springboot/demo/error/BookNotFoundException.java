package com.springboot.demo.error;

import javax.validation.constraints.Min;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(@Min(1) Integer id) {
        super("Book id not found : " + id);
    }

}
