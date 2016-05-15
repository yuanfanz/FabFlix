package source_XML_parsing;

import java.util.Date;
import java.util.HashMap;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.fablix.moviedb.model.Genres;
import com.fablix.moviedb.model.Movies;
import com.fablix.moviedb.model.Stars;

public class XmlParserHandler extends DefaultHandler {
	
	String value = null;
	
	Movies movie = null;
	Stars star   = null;
	Genres genre = null;
	HashSet<Movies> movieList = new HashSet<Movies>();
	HashSet<Stars> starsList = new HashSet<Stars>();
	HashSet<Genres> genresList = new HashSet<Genres>();
	
	
	HashMap<Movies,HashSet<Genres>> genreInMovie = new HashMap<Movies,HashSet<Genres>>();
	private HashSet<Genres> genreInMov;
	HashMap<Movies,HashSet<Stars>> starInMovie = new HashMap<Movies,HashSet<Stars>>();
	private HashSet<Stars> starInMov;
	
	String currentDirect = null;
	
		
	public HashSet<Genres> getGenresList() {
		return genresList;
	}

	public HashSet<Movies> getMovieList() {
		return movieList;
	}

	public HashSet<Stars> getStarsList() {
		return starsList;
	}

	public HashMap<Movies, HashSet<Genres>> getGenreInMovie() {
		return genreInMovie;
	}


	public HashMap<Movies, HashSet<Stars>> getStarInMovie() {
		return starInMovie;
	}


	/**
	 * iterate all start tag of XML file
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		if (qName.equals("actor") || qName.equals("a")){
			star = new Stars();
		
		}else if (qName.equals("film")){
			
			movie = new Movies();
			genreInMov = new HashSet<Genres>();
		
		}else if (qName.equals("cat")){
			
			genre = new Genres();
		
		}else if (qName.equals("filmc")){
			
			starInMov = new HashSet<Stars>();
			
		}else if (qName.equals("m")){
			movie = new Movies();
		}
	}

	/**
	 * iterate all end tag of XML file
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		
		// for actors63.xml
		if (qName.equals("actor")){
			if (!starsList.add(star)){
				//System.out.println("Duplicate stars!");
			}
				
		}else if (qName.equals("familyname")){
			
			star.setLast_name(value);
		
		}else if (qName.equals("firstname")){
			
			star.setFirst_name(value);
		
		}else if (qName.equals("dob")){
			
			DateFormat fmt =new SimpleDateFormat("yyyy");
			Date date = null;
			try {
				
				date = fmt.parse(value);
					
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				//System.err.println(e.getMessage());
			}
			star.setDob(date);
		}
		
		// for mains243.xml
		else if (qName.equals("dirname")){
			currentDirect = value;
		}
		else if (qName.equals("film")){
			
			movie.setDirector(currentDirect);
			// add movie into movie list
			if (!movieList.add(movie)){
				//System.out.println("Duplicate movies!");
			}
			// add movie into genre in movie
			if (!genreInMovie.containsKey(movie)){
				genreInMovie.put(movie, genreInMov);
			}			
			
		
		}else if (qName.equals("t")){
			
			movie.setTitle(value);
		
		}else if (qName.equals("year")){
			try{
				movie.setYear(Integer.valueOf(value));
			}catch (NumberFormatException e){
				movie.setYear(-1);
			}
		
		}else if (qName.equals("cat")){
			
			genre.setName(value);
			genreInMov.add(genre);
			if (!genresList.add(genre)){
				//System.out.println("Duplicate genres!");
			}
		}else if (qName.equals("a")){
			String[] names = value.split(" ");
			if (names.length<2){
				star.setFirst_name(names[0]);
				star.setLast_name("");
			}else{
				star.setFirst_name(names[0]);
				star.setLast_name(names[names.length-1]);
			}
			
			starInMov.add(star);
		
		}else if (qName.equals("filmc")){
			
			if (!starInMovie.containsKey(movie)){
				starInMovie.put(movie, starInMov);
			}
		}
	}
	


	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		
		this.value = new String(ch, start, length);
		if (value == null) this.value = "";
	}

	/**
	 * the start of the parsing
	 */
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		System.out.println("Starting SAX parsing...");
	}
	/**
	 * end of the parsing
	 */
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
		System.out.println("Finished SAX parsing.");
	}
	
}
