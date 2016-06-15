package model;


public class Document {
	public String doc_type;
	public String authors;
	public String doc_title;
	public  int doc_year;
	
	public Document(){
		this.authors = "";
		this.doc_title = "";
		this.doc_year = 0;
	}
		public String getDoc_type() {
		return doc_type;
	}

	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}

	public String  getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getDoc_title() {
		return doc_title;
	}

	public void setDoc_title(String doc_title) {
		this.doc_title = doc_title;
	}

	public int getDoc_year() {
		return doc_year;
	}

	public void setDoc_year(int doc_year) {
		this.doc_year = doc_year;
	}

	public String toString(){
		return "type "+doc_type+" #authors: " + authors+
				" #title: " + doc_title + "#year: " + doc_year ;
	}
	
}
