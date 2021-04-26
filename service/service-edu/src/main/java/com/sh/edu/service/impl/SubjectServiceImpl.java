package com.sh.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sh.edu.entity.ExcelSubjectData;
import com.sh.edu.entity.Subject;
import com.sh.edu.entity.SubjectVO;
import com.sh.edu.listener.SubjectExcelListener;
import com.sh.edu.mapper.SubjectMapper;
import com.sh.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.servicebase.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author sunhu
 * @since 2021-04-26
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public void importSubjectData(MultipartFile file) {

        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, ExcelSubjectData.class,new SubjectExcelListener()).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20002,"添加课程分类失败");
        }
    }


    /**
     * 课程分类列表
     * @author sunhu
     * @date 2021/4/26 10:57
     * @return java.util.List<com.sh.edu.entity.SubjectVO>
     */
    @Override
    public List<SubjectVO> nestedList() {
//
//        //获取一级分类数据记录
//        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("parent_id", 0);
//        queryWrapper.orderByAsc("sort", "id");
//        List<Subject> subjects = baseMapper.selectList(queryWrapper);




//        //获取二级分类数据记录
//        QueryWrapper<Subject> queryWrapper2 = new QueryWrapper<>();
//        queryWrapper2.ne("parent_id", 0);
//        queryWrapper2.orderByAsc("sort", "id");
//        List<Subject> subSubjects = baseMapper.selectList(queryWrapper2);



        List<SubjectVO> vos = baseMapper.getAll();




        List resultList=new ArrayList();
        // 一：递归
        for (SubjectVO subjectVO : vos) {
            if (subjectVO.getParentId().equals("0")){
                 resultList.add(getChildren(subjectVO, vos));
            }
        }

        // 二
//        SubjectVO rootVO=new SubjectVO();
//        rootVO.setId("0");
//        rootVO.setParentId("-1");
//        vos.add(rootVO);
//        Map<String, SubjectVO> dataMap = new HashMap<>();
////        for (SubjectVO vo : vos) {
////            dataMap.put(vo.getId(),vo);
////        }
////        List root=new ArrayList();
////        for (SubjectVO vo : vos) {
////            String parentId = vo.getParentId();
////            if (dataMap.containsKey(parentId)){
////
////                SubjectVO subjectVO = dataMap.get(parentId);
////                if (subjectVO.getChildren()==null){
////                    subjectVO.setChildren(new ArrayList<>());
////                }
////                if (!vo.getId().equals("0")){
////                    subjectVO.getChildren().add(vo);
////                }
////            }
////            if("0".equals(vo.getId())){
////                root.add(vo);
////            }
////        }

//        SubjectVO rootVO=new SubjectVO();
//        rootVO.setId("0");
//        rootVO.setParentId("-1");
//        vos.add(0,rootVO);
//        List root=new ArrayList();
//        for (SubjectVO vo : vos) {
//            if (vo.getChildren()==null){
//                vo.setChildren(new ArrayList<>());
//            }
//            vo.setChildren(vos.stream().filter(item->item.getParentId().equals(vo.getId())).collect(Collectors.toList()));
//            if (vo.getId().equals("0")){
//                root.add(vo);
//            }
//        }
        return resultList;
    }

    // 递归
    private SubjectVO getChildren(SubjectVO subject, List<SubjectVO> vos) {
        for (SubjectVO item : vos) {
            if (subject.getId().equals(item.getParentId())){
                if (subject.getChildren()==null){
                    subject.setChildren(new ArrayList<>());
                }
                subject.getChildren().add(getChildren(item,vos));
            }
        }
        return subject;
    }
}
