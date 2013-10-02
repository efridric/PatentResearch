import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

public class WikipediaIndexer {
	/* Author : Eric Fridrich
	 * This program will take the offical Wikipedia data and use lucene to create an index
	 * Currently, only the title, id and text fields are indexed. The actual text context is
	 * not stored but it is analyzed and indexed, so it is searchable 
	 * 
	 * Instructions: Enter the path to the Wikipedia XML you want to index as the first program arg
	 * The index will be created in the file path specified by the String indexDir
	 */
	
	private static IndexWriter writer;
	int count = 0;

	@SuppressWarnings("deprecation")
	public void addPage(Page page) throws IOException {
		System.out.println("Added: " + count +" Documents");
		count++;
	
		Document wikiDocument = new Document();
		wikiDocument.add(new Field("title", page.getTitle(), Field.Store.YES, Field.Index.NO));
		wikiDocument.add(new Field("id", page.getId(), Field.Store.YES, Field.Index.NO));
		wikiDocument.add(new Field("text", page.getText(), Field.Store.NO, Field.Index.ANALYZED));
		
		writer.addDocument(wikiDocument);
	}
	public static void main(String[] args) throws IOException, SAXException {
		
		String indexDir = "J:/DATA/WikipediaIndex";
		Directory dir = FSDirectory.open(new File(indexDir));
		
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);
		boolean createFlag = true;
		
		IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_CURRENT, analyzer);
		
		writer = new IndexWriter(dir, conf);
		
		Digester digester = new Digester();
		digester.setValidating(false);
		
		digester.addObjectCreate("mediawiki", WikipediaIndexer.class);
		digester.addObjectCreate("mediawiki/page", Page.class);
		
		digester.addCallMethod("mediawiki/page/title", "setTitle", 0);
		digester.addCallMethod("mediawiki/page/id", "setId", 0);
		digester.addCallMethod("mediawiki/page/revision/text", "setText", 0);
		
		digester.addSetNext("mediawiki/page", "addPage");
		
		WikipediaIndexer wp = (WikipediaIndexer) digester.parse(new File(args[0]));

		writer.close();
	}
	
	public static class Page {
		private String title;
		private String id;
		private String text;
		
		public void setTitle(String title) {
			this.title = title;
		}
		
		public String getTitle() {
			return title;
		}
		
		public void setId(String id) {
			this.id = id;
		}
		
		public String getId() {
			return id;
		}
		
		public void setText(String text) {
			this.text = text;
		}
		
		public String getText() {
			return text;
		}
	}
	
}
