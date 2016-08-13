package com.huizhong.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.huizhong.search.dao.SearchDao;
import com.huizhong.search.pojo.Item;
import com.huizhong.search.pojo.SearchResult;
/**
 * 商品搜索Dao
 * @author DELL
 *
 */
@Repository
public class SearchDaoImpl implements SearchDao {

	@Autowired
	private SolrServer solrServer;
	
	@Override
	public SearchResult search(SolrQuery query) throws Exception {
		// 返回值对象
		SearchResult result = new SearchResult();
		// 根据查询条件查询索引库
		QueryResponse response = solrServer.query(query);
		// 取查询结果
		SolrDocumentList solrDocumentList = response.getResults();
		// 取查询结果总数量
		result.setRecordCount(solrDocumentList.getNumFound());
		// 商品列表
		List<Item> itemList = new ArrayList<>();
		// 取高亮显示
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		// 取商品列表
		for(SolrDocument document : solrDocumentList){
			// 创建一个商品对象
			Item item = new Item();
			item.setId((String)document.get("id"));
			// 取高亮显示结果
			List<String> list = highlighting.get(document.get("id")).get("item_title");
			String title = "";
			if (list != null && list.size() > 0) {
				title = list.get(0); 
			} else {
				title = (String)document.get("item_title");
			}
			item.setTitle(title);
			item.setImage((String)document.get("item_image"));
			item.setPrice((long)document.get("item_price"));
			item.setSell_point((String)document.get("item_sell_point"));
			item.setCategory_name((String)document.get("item_category_name"));
			// 添加到商品列表
			itemList.add(item);
		}
		result.setItemList(itemList);
		return result;
	}
	
}
