package com.efridric.searcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.flexible.standard.parser.*;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.search.*;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class SearchEngine {
	private IndexSearcher searcher = null;
	private QueryParser parser = null;
	private String indexDir = null;
	
	public SearchEngine(String indexDir) throws IOException {
		this.indexDir = indexDir;
		Directory dir = FSDirectory.open(new File(indexDir));
		searcher = new IndexSearcher(IndexReader.open(dir));
		parser = new QueryParser(Version.LUCENE_CURRENT, "content", new StandardAnalyzer(Version.LUCENE_CURRENT));
	}
	
	public void performSearch(String queryString) throws IOException, ParseException {
		//Query query = parser.parse(queryString);
		TermQuery tq = (TermQuery) parser.parse("Class:455");
		TopDocs resultDocs = searcher.search(tq, 50000);
		
		ScoreDoc[] resultHits = resultDocs.scoreDocs;
		for (int i = 0; i < resultHits.length; i++) {
			int docId = resultHits[i].doc;
			Document d = searcher.doc(docId);
			System.out.println(d.get("Class"));
		}
		
		System.out.println("Returned " + resultHits.length);
	}	
	
}

