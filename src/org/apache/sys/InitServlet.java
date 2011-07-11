package org.apache.sys;

import org.apache.model.GuestbookEntry;
import org.apache.model.DatabaseException;
import org.apache.model.GuestbookDB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet fuer die Suche im Gaestebuch.<br>
 * Nimmt eine Suchanfrage entgegen und liefert eine HTML-Seite mit den
 * Suchergebnissen.<br>
 * Die Eingabe-Parameter die im Request vorhanden sein muessen:<br>
 * <br>
 * tfSearchtext: Text nachdem gesucht wurde<br>
 * tfAuthor: Der Autor der gesucht wurde <br>
 * <br>
 * Beide Parameter sind Strings.
 * 
 */
public class InitServlet extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Anfrage einfach an handleRequest zur Bearbeitung weitergeben
		handleRequest(request, response);
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Anfrage einfach an handleRequest zur Bearbeitung weitergeben
		handleRequest(request, response);
	}

	/**
	 * Bearbeitet einen HTTP-Anfrage
	 * 
	 * @param request
	 *            Der HTTP-Request
	 * @param response
	 *            Der HTTP-Response
	 * @throws ServletException
	 *             Wenn ein Servlet-Fehler auftritt
	 * @throws IOException
	 *             Wenn ein I/O Fehler auftritt
	 */
	private void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ==========================================
		// HIER KOMMT EUER CODE REIN
		// ==========================================
		//
		// Hier sollt ihr folgendes machen:
		// Wie bei Teil 01 muesst ihr hier die Parameter auslesen und die
		// Suchtreffer berechnen. Allerdings schreibt ihr nichts direkt
		// in den Response heraus, sondern legt die Ergebnisse zunaechst
		// in einer ArrayList ab.
		// Diese Liste legt ihr dann am Ende als Attribut "result" im 
		// Request ab (request.setAttribute()) und leitet zuletzt
		// den Request an die JSP-Seite weiter. Dazu koennt ihr die Methode
		// request.getRequestDispatcher(jspname).forward() benutzen.
		// Auf der JSP-Seite steht euch dann die Ergebnisliste zur Verfuegung.
		// Dort wurde sie bereits im Geruest als Bean eingebunden.
		//
		// ==========================================

		// Lies Parameter aus Request aus
		String searchText = getParameterAsString("tfSearchtext", "", request);
		String author = getParameterAsString("tfAuthor", "", request);
		
		// Wandle in Kleinbuchstaben um, fuer Case-Insensitive Suche
		searchText = searchText.toLowerCase();
		author = author.toLowerCase();

		// Mindests ein Parameter wurde ausgefuellt, wir fuehren
		// also die Suche aus.
		// Zuerst holen wir eine Instanz der Gaestebuch DB-Schnittstelle
		GuestbookDB db = GuestbookDB.getInstance();
		try {

			// Liste fuer die Suchergebnisse, die wir spaeter an JSP
			// per Request-Attribut weitergeben werden
			ArrayList<GuestbookEntry> searchResult = 
				new ArrayList<GuestbookEntry>();

			// Hole alle Eintraege im Gaestebuch
			List<GuestbookEntry> allEntries = db.getAllEntries();
			
			// Gehe alle Eintraege durch und filtere die heraus,
			// die die Suchanfrage erfuellen
			for (GuestbookEntry entry : allEntries) {
				if (entry.getText().toLowerCase().contains(searchText)
						&& entry.getAuthor().toLowerCase().contains(author)) {
					// Treffer gefunden - Text und Author passen
					// Wir fuegen den Eintrag ins Ergebnis ein
					searchResult.add(entry);
				}
			}
			
			// Die Ergebnisliste haengen wir als Attribut in den Request
			// damit die JSP-Seite Zugriff auf die Liste bekommen kann.
			request.setAttribute("result", searchResult);
			
		} catch (DatabaseException e) {
			// Bei einem Datenbank-Fehler legen wir ein Request-Attribut
			// fuer den Fehler an. Dieses wird dann auf der JSP-Seite
			// ausgewertet.
			request.setAttribute("error", "FEHLER: " + e.getMessage());
		}

		// Am Schluss leiten wir an die JSP-Seite result.jsp weiter,
		// die dann die Anzeige des Ergebnisses uebernimmt.
		request.getRequestDispatcher("result.jsp").forward(request, response);

	}

	/**
	 * Hilfsmethode zum Auslesen von String-Parametern aus dem Request Gibt
	 * einen Request-Parameter als String zurueck. Wenn der Parameter im Request
	 * nicht enthalten war, wird ein Default-Wert zurueckgegeben. Leerzeichen am
	 * Anfang und Ende werden getrimmt.
	 * 
	 * @param name
	 *            Der Name des Request-Parameters
	 * @param defaultValue
	 *            Der Default-Wert fuer den Fehler-Fall
	 * @param request
	 *            Der HTTP-Request
	 * @return Der Wert des Parameters
	 */
	protected String getParameterAsString(String name, String defaultValue,
			HttpServletRequest request) {
		String value = request.getParameter(name);
		if (value == null) {
			value = defaultValue;
		}
		value = value.trim();
		return value;
	}

	/**
	 * Initialisiert die Gaestebuch-DB
	 */
	public void init() throws ServletException {
		GuestbookDB db = GuestbookDB.getInstance();
		db.init(getServletContext().getRealPath("/WEB-INF"));
	}

}
