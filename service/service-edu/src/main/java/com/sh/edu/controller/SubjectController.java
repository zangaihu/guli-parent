package com.sh.edu.controller;


import com.sh.commonutils.R;
import com.sh.edu.entity.SubjectVO;
import com.sh.edu.service.SubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author sunhu
 * @since 2021-04-26
 */
@RestController
@CrossOrigin
@RequestMapping("/edu/subject")
public class SubjectController {


    @Autowired
    private SubjectService subjectService;


    @ApiOperation(value = "Excel批量导入")
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {
        //1 获取上传的excel文件 MultipartFile
        //返回错误提示信息
        subjectService.importSubjectData(file);
        //判断返回集合是否为空
        return R.ok();
    }

    @GetMapping("list")
    public R list(){
        List<SubjectVO> vos = subjectService.nestedList();
        return R.ok().data("item",vos);
    }


}

