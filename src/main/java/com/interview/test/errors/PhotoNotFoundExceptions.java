package com.interview.test.errors;

/**
 * @author Chandra Bhushan Verma
 * Custom implementation of Not found exception from REST Endpoints
 */
public class PhotoNotFoundExceptions extends RuntimeException {

    public PhotoNotFoundExceptions(String exception){
        super(exception);
    }
}
