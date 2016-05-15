package source_XML_parsing;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.fablix.moviedb.DAO.GenreInMvDAO;
import com.fablix.moviedb.DAO.GenresDAO;
import com.fablix.moviedb.DAO.MoviesDAO;
import com.fablix.moviedb.DAO.StarInMvDAO;
import com.fablix.moviedb.DAO.StarsDAO;
import com.fablix.moviedb.db.dbConnection;
import com.fablix.moviedb.model.Genres;
import com.fablix.moviedb.model.Movies;
import com.fablix.moviedb.model.Stars;

public class XmlParsing {
		
	private static XmlParserHandler xmlhandler;
	
	public static void initial(String xmlFile){
		try {
			//get paser factory instance
			SAXParser xmlparser = SAXParserFactory.newInstance().newSAXParser();
			xmlhandler = new XmlParserHandler();
			
			//define the input source from xmlfile
			InputSource input = new InputSource(xmlFile);
			//encode iso-8859-1
			input.setEncoding("ISO-8859-1");
			
			xmlparser.parse(input, xmlhandler);
			
			
		} catch (ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		
		System.out.println("Start parsing actors63.xml");
		initial("src/main/java/source_XML_parsing/stanford-movies/actors63.xml");
		StarsDAO sDAO = new StarsDAO();
		HashSet<Stars> starList = xmlhandler.getStarsList(); 
		System.out.println("starlist size is "+ starList.size());
		try {
			
			sDAO.insertStars(starList);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
		System.out.println("Finish parsing actors63.xml");
////////////////////////////////////////////////////////////////////		
		System.out.println("Start parsing mains243.xml");
		initial("src/main/java/source_XML_parsing/stanford-movies/mains243.xml");
		MoviesDAO mDAO = new MoviesDAO();
		GenresDAO gDAO = new GenresDAO();
		GenreInMvDAO gimDAO = new GenreInMvDAO();
		
		HashSet<Movies> movieList = xmlhandler.getMovieList();
		HashSet<Genres> genresList = xmlhandler.getGenresList();
		HashMap<Movies,HashSet<Genres>> genreInMovie = xmlhandler.getGenreInMovie();
		
		System.out.println("movielist size is "+ movieList.size());
		System.out.println("genresList size is "+ genresList.size());
		System.out.println("genreInMovie size is "+ genreInMovie.size());
		
		try {
			mDAO.insertMovies(movieList);
			gDAO.insertGenre(genresList);
			gimDAO.insertGenInMv(genreInMovie);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.err.println(e.getMessage());
		}

		System.out.println("Finish parsing mains243.xml");
////////////////////////////////////////////////////////////////////		
		System.out.println("Start parsing casts124.xml");
		initial("src/main/java/source_XML_parsing/stanford-movies/casts124.xml");
		StarInMvDAO simDAO = new StarInMvDAO(); 
		HashMap<Movies,HashSet<Stars>> starInMovie = xmlhandler.getStarInMovie();
		
		try {
			simDAO.insertStrInMv(starInMovie);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
		System.out.println("Finish parsing casts124.xml");
		
		dbConnection.connectionClose();
	}

	
	
}
