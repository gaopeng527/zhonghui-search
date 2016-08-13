package com.zhonghui.search.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * 商品查询Controller
 * @author DELL
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhonghui.common.pojo.ZhonghuiResult;
import com.zhonghui.common.utils.ExceptionUtil;
import com.zhonghui.search.pojo.SearchResult;
import com.zhonghui.search.service.SearchService;
@Controller
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping(value="/query", method=RequestMethod.GET)
	@ResponseBody
	public ZhonghuiResult search(@RequestParam("q") String queryString,
			@RequestParam(defaultValue="1") Integer page,
			@RequestParam(defaultValue="60") Integer rows){
		// 查询条件不能为空
		if(StringUtils.isBlank(queryString)){
			return ZhonghuiResult.build(400, "查询条件不能为空");
		}
		SearchResult result = null;
		try {
			// 避免get乱码
			queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
			result = searchService.search(queryString, page, rows);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ZhonghuiResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return ZhonghuiResult.ok(result);
	}
}
