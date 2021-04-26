package com.sh.edu.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunhu
 * @date 2021/4/26 10:55
 */
@Data
public class SubjectVO {

    private String id;
    private String title;
    private String parentId;
    private List<SubjectVO> children;
}
