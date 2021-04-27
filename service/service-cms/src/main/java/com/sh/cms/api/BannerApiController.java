package com.sh.cms.api;

import com.sh.cms.entity.CrmBanner;
import com.sh.cms.service.CrmBannerService;
import com.sh.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cms/banner")
@CrossOrigin
public class BannerApiController {

	@Autowired
	private CrmBannerService bannerService;

	@ApiOperation(value = "获取首页banner")
	@GetMapping("getAllBanner")
	public R index() {
		List<CrmBanner> list = bannerService.selectIndexList();
		return R.ok().data("bannerList", list);
	}

}