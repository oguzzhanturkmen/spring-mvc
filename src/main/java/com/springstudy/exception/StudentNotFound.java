package com.springstudy.exception;

public class StudentNotFound extends RuntimeException{

        public StudentNotFound(String message) {
            super(message);
        }
}
