package com.sh.oss.controller;

import com.sh.commonutils.R;
import com.sh.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin //跨域
@RestController
@RequestMapping("/admin/oss/file")
public class FileController {

	@Autowired
	private FileService fileService;

	/**
	 * 文件上传
	 *
	 * @param file
	 */
	@ApiOperation(value = "文件上传")
	@PostMapping("upload")
	public R upload(
			@ApiParam(name = "file", value = "文件", required = true)
			@RequestParam("file") MultipartFile file) {

		String uploadUrl = fileService.upload(file);
		//返回r对象
		return R.ok().message("文件上传成功").data("url", uploadUrl);

	}
}