package com.zhonghui.search.service;

import com.huizhong.search.pojo.SearchResult;

public interface SearchService {
	
	SearchResult search(String queyString, int page, int rows) throws Exception;
}
