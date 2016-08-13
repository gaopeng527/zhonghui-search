package com.zhonghui.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.zhonghui.search.pojo.SearchResult;

public interface SearchDao {
	SearchResult search(SolrQuery query) throws Exception;
}
