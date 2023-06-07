package com.hasib.bank.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UsersResponseDto {
    private List<UserDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
