package org.jetlag.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
@Getter
public class UserDTO {
    private final String username;
    private final int age;
}
