package com.zhonghui.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huizhong.search.dao.SearchDao;
import com.huizhong.search.pojo.SearchResult;
import com.zhonghui.search.service.SearchService;
/**
 * 搜索Service
 * @author DELL
 *
 */
@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchDao searchDao;
	
	@Override
	public SearchResult search(String queyString, int page, int rows) throws Exception {
		// 创建查询对象
		SolrQuery query = new SolrQuery();
		// 设置查询条件
		query.setQuery(queyString);
		query.setStart((page-1)*rows);
		query.setRows(rows);
		// 设置默认搜索域
		query.set("df", "item_keywords");
		// 设置高亮显示
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		// 执行查询
		SearchResult result = searchDao.search(query);
		// 计算查询结果总页数
		long recordCount = result.getRecordCount();
		long pageCount = recordCount / rows;
		if(recordCount % rows > 0) {
			pageCount++;
		}
		result.setPageCount(pageCount);
		result.setCurPage(page);
		return result;
	}
	
}
