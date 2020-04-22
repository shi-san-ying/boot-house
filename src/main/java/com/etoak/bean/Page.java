package com.etoak.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
<<<<<<< HEAD
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> {
    private int pageNum;
    private int pageSize;
    private long total;
    private List<T> rows;
=======

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> {
    // 页码，当前页
    private int pageNum;

    // 每页记录数
    private int pageSize;

    // 数据
    private List<T> rows;

    // 总记录数
    private long total;

    // 总页数
>>>>>>> dev
    private int pageCount;
}
