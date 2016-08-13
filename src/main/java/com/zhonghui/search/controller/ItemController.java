package com.zhonghui.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhonghui.common.pojo.ZhonghuiResult;
import com.zhonghui.search.service.ItemService;
/**
 * solr索引库维护
 * @author DELL
 *
 */
@Controller
@RequestMapping("/manager")
public class ItemController {
	
	@Autowired
	private ItemService ItemService;
	
	/**
	 * 导入商品数据到索引库
	 */
	@RequestMapping("/importall")
	@ResponseBody
	public ZhonghuiResult importAllItems() {
		ZhonghuiResult result = ItemService.importAllItems();
		return result;
	}
}
