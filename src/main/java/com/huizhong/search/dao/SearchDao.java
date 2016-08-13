package com.huizhong.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.huizhong.search.pojo.SearchResult;

public interface SearchDao {
	SearchResult search(SolrQuery query) throws Exception;
}
