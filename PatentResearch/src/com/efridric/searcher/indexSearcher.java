package com.efridric.searcher;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.index.*;

import com.efridric.util.FileDialogGUI;

public class indexSearcher {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws IOException, ParseException {
		//SearchEngine se = new SearchEngine("J:/DATA/WikipediaIndex");
		SearchEngine se = new SearchEngine("J:/DATA/PatentData/PatentDataFromSuraj/SurajIndexFulltext2012-13/index");
		se.performSearch("A new way to mine for gold");
	}
}
