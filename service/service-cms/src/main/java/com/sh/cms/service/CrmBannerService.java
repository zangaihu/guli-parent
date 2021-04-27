package com.sh.cms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sh.cms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author sunhu
 * @since 2021-04-27
 */
public interface CrmBannerService extends IService<CrmBanner> {

    void pageBanner(Page<CrmBanner> pageParam, Object o);

    CrmBanner getBannerById(String id);

    void saveBanner(CrmBanner banner);

    void updateBannerById(CrmBanner banner);

    void removeBannerById(String id);

    List<CrmBanner> selectIndexList();
}
