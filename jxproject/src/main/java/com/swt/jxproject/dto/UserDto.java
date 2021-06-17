package com.swt.jxproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    @NotNull
    private Long id;
    @NotNull
    private String username;
    private String password;
    private String salt;
}
