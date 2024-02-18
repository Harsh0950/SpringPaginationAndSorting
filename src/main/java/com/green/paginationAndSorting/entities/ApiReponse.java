package com.green.paginationAndSorting.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiReponse<T> {
    private int recordCount;
    T records;
}
