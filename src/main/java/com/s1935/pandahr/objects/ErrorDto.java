package com.s1935.pandahr.objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ErrorDto {

    //TODO iterate over this and provide more useful solution for exceptions handling
    private String message;
}