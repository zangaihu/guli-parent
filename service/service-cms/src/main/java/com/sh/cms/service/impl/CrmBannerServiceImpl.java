package com.sh.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sh.cms.entity.CrmBanner;
import com.sh.cms.mapper.CrmBannerMapper;
import com.sh.cms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author sunhu
 * @since 2021-04-27
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {


    @Override
    public void pageBanner(Page<CrmBanner> pageParam, Object o) {
        baseMapper.selectPage(pageParam,new QueryWrapper<>());
    }

    @Override
    public CrmBanner getBannerById(String id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void saveBanner(CrmBanner banner) {
        baseMapper.insert(banner);
    }

    @Override
    public void updateBannerById(CrmBanner banner) {
        baseMapper.update(banner,new QueryWrapper<>());
    }

    @Override
    public void removeBannerById(String id) {
        baseMapper.deleteById(id);
    }

    @Override
    public List<CrmBanner> selectIndexList() {
        return         baseMapper.selectList(new QueryWrapper<>());
    }
}
