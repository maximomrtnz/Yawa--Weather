package com.yawaweather.rss;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.yawaweather.model.Weather;

public class GetXMLFromWebServices {

	public Weather callYahooWeather(String woid, String scale) throws Exception {

		String urlToRssFeed = "http://weather.yahooapis.com/forecastrss?w="
				+ woid;

		// setup the url
		URL url = new URL(urlToRssFeed);

		// create the factory
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// create a parser
		SAXParser parser = factory.newSAXParser();

		// create the reader (scanner)
		XMLReader xmlreader = parser.getXMLReader();
		// instantiate our handler
		RSSHandler theRssHandler = new RSSHandler();
		// assign our handler
		xmlreader.setContentHandler(theRssHandler);
		// get our data via the url class
		InputSource is = new InputSource(url.openStream());
		// perform the synchronous parse
		xmlreader.parse(is);
		// get the results - should be a fully populated RSSFeed instance, or
		// null on error
		return theRssHandler.getWeather();

	}

}
