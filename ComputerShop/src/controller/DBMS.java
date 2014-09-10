package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

import model.CategoryBean;
import model.ComponentBean;
import model.DeviceBean;
import model.UserBean;
import exceptions.ElementExist;

public class DBMS {

	private static DBMS instance;
		
	private Integer userID = 0;
	private Integer categoryID = 0;
	private Integer componentID = 0;
	private Integer deviceID = 0;
	
	//public static Integer episodesID = 0;

	private ArrayList<UserBean> sessionUsers = new ArrayList<UserBean>();
	
	private ArrayList<UserBean> usersDatabase = new ArrayList<UserBean>();
	private ArrayList<CategoryBean> categoriesDatabase = new ArrayList<CategoryBean>();
	private ArrayList<ComponentBean> componentsDatabase = new ArrayList<ComponentBean>();
	private ArrayList<DeviceBean> devicesDatabase = new ArrayList<DeviceBean>();
	
	//private HashMap<Integer, Movie> moviesDatabase = new HashMap<Integer, Movie>();
		

	public Integer getUserID() { return userID; }	
	public Integer getCategoriesID() { return categoryID; }	
	public Integer getComponentID() { return componentID; }
	public Integer getDeviceID() {	return deviceID;}
	
	public void increaseUserID() { userID++; }
	public void increaseCategoriesID() { categoryID++; }
	public void increaseComponentID() {	componentID++; }
	public void increaseDeviceID() { deviceID++; }
	
	
	public ArrayList<UserBean> getSessionUsers() {return sessionUsers;}
	
	public void setSessionUsers(ArrayList<UserBean> sessionUsers) {	this.sessionUsers = sessionUsers; }
	
	public synchronized ArrayList<UserBean> getUsersDatabase() { return usersDatabase; }
	public synchronized ArrayList<CategoryBean> getCategoriesDatabase() { return categoriesDatabase; }
	public synchronized ArrayList<ComponentBean> getComponentsDatabase() { return componentsDatabase; }
	public synchronized ArrayList<DeviceBean> getDevicesDatabase() { return devicesDatabase; }

	//public synchronized Map<Integer, Movie> getMoviesDatabase() { return moviesDatabase; }
	//public synchronized Map<Integer, Series> getSeriesDatabase() { return seriesDatabase; }

	
	/*public synchronized Map<Integer, User> getUsers() {
		Map<Integer, User> retVal = new HashMap<Integer, User>();
		for (User value : usersDatabase.values()) {
				retVal.put(value.getId(), (User) value);
		}
		return retVal;
	}
	
	public String usersToS() {
		String ret = "";
		for (User value : usersDatabase.values()) {
			ret += value.getId() + " | " + value;
			ret += "\n";
		}
		return ret;
	}

	public synchronized Map<Integer, Movie> getMovies() {
		Map<Integer, Movie> retVal = new HashMap<Integer, Movie>();
		for (Movie value : moviesDatabase.values()) {
				retVal.put(value.getId(), (Movie) value);
		}
		return retVal;
	}

	public synchronized Map<Integer, Series> getSeries() {
		Map<Integer, Series> retVal = new HashMap<Integer, Series>();
		for (Series value : seriesDatabase.values()) {
				retVal.put(value.getId(), (Series) value);
		}
		return retVal;
	}
	
	public synchronized Map<Integer, Season> getSeasons() {
		Map<Integer, Season> retVal = new HashMap<Integer, Season>();
		for (Season value : seasonsDatabase.values()) {
				retVal.put(value.getId(), (Season) value);
		}
		return retVal;
	}

	public synchronized Map<Integer, Episode> getEpisodes() {
		Map<Integer, Episode> retVal = new HashMap<Integer, Episode>();
		for (Episode value : episodesDatabase.values()) {
				retVal.put(value.getId(), (Episode) value);
		}
		return retVal;
	}*/


	private DBMS() {}

	public static DBMS getInstance() {
		if (instance == null)
			instance = new DBMS();
		return instance;
	}

	
	@SuppressWarnings("unchecked")
	public void loadUsers() {
		try {
			File file = new File("Users.dat");
			if (file.exists()) {
				ObjectInputStream in = new ObjectInputStream(
						new FileInputStream("Users.dat"));
				usersDatabase = (ArrayList<UserBean>) in.readObject();
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void loadCategories() {
		try {
			File file = new File("Categories.dat");
			if (file.exists()) {
				ObjectInputStream in = new ObjectInputStream(
						new FileInputStream("Categories.dat"));
				categoriesDatabase = (ArrayList<CategoryBean>) in.readObject();
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void loadComponents() {
		try {
			File file = new File("Components.dat");
			if (file.exists()) {
				ObjectInputStream in = new ObjectInputStream(
						new FileInputStream("Components.dat"));
				componentsDatabase = (ArrayList<ComponentBean>) in.readObject();
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void loadDevices() {
		try {
			File file = new File("Devices.dat");
			if (file.exists()) {
				ObjectInputStream in = new ObjectInputStream(
						new FileInputStream("Devices.dat"));
				devicesDatabase = (ArrayList<DeviceBean>) in.readObject();
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
		
		
	/*@SuppressWarnings("unchecked")
	public void loadMovies() {
		try {
			File file = new File("Movies.dat");
			if (file.exists()) {
				ObjectInputStream in = new ObjectInputStream(
						new FileInputStream("Movies.dat"));
				moviesDatabase = (HashMap<Integer, Movie>) in.readObject();
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "DBMS [" + usersID + " " + moviesID + " " + seriesID + " " + seasonsID + " " + episodesID + "]";
	}
	@SuppressWarnings("unchecked")
	public void loadSeries() {
		try {
			File file = new File("Series.dat");
			if (file.exists()) {
				ObjectInputStream in = new ObjectInputStream(
						new FileInputStream("Series.dat"));
				seriesDatabase = (HashMap<Integer, Series>) in.readObject();
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	*/
	
	
	/* CRUD sa korisnicima // BCDEF zapravo */	
/*	public synchronized void addUser(User newUser) throws ElementExist {
		if (usersDatabase.containsKey(newUser.getId())) {
			throw new ElementExist("Korisnik vec postoji u bazi!");
		}
		newUser.setId(usersID);
		usersDatabase.put(usersID, newUser);
		saveUsers();
	}

	public synchronized void removeUser(User removee) throws ElementDontExist {
		if (!usersDatabase.containsKey(removee.getId()))
			throw new ElementDontExist("Trazeni korisnik ne postoji u bazi!");
		usersDatabase.remove(removee.getId());
		saveUsers();
	}

	public synchronized void removeUser(Integer id) throws ElementDontExist {
		if (!usersDatabase.containsKey(id))
			throw new ElementDontExist("Trazeni korisnik ne postoji u bazi!");
		usersDatabase.remove(id);
		saveUsers();
	}

	public User getUser(Integer key) throws ElementDontExist {
		if (!usersDatabase.containsKey(key))
			throw new ElementDontExist("Trazeni korisnik ne postoji u bazi!");
		return usersDatabase.get(key);
	}

	public synchronized void editUser(User forChange) throws ElementDontExist {
		if (!usersDatabase.containsKey(forChange.getId()))
			throw new ElementDontExist("Trazeni korisnik ne postoji u bazi!");
		usersDatabase.remove(forChange.getId());
		usersDatabase.put(forChange.getId(), forChange);
		saveUsers();
	}
	
	public synchronized void addMovieToFavourites(User user, Movie movie) throws ElementExist {
		if (usersDatabase.get(user.getId()).getFavourites().contains(movie)) {
			throw new ElementExist("Korisnik vec ima zadati film u listi omiljenih filmova!");
		}
		user.getFavourites().add(movie);
		usersDatabase.remove(user.getId());
		usersDatabase.put(user.getId(), user);
		saveUsers();
	}
	
	public synchronized void removeMovieToFavourites(User user, Movie movie) throws ElementExist {
		if (!usersDatabase.get(user.getId()).getFavourites().contains(movie)) {
			throw new ElementExist("Korisnik ne sadrzi zadati film u listi omiljenih filmova!");
		}
		user.getFavourites().remove(movie);
		usersDatabase.remove(user.getId());
		usersDatabase.put(user.getId(), user);
		saveUsers();
	}
	

	public Iterator<Integer> getUsersIterator() {
		Set<Integer> oznakeUAutor = usersDatabase.keySet();
		return oznakeUAutor.iterator();
	}
*/
	
	public synchronized void addUser(UserBean newUser) throws ElementExist {
		if (usersDatabase.contains(newUser)) {
			throw new ElementExist("Korisnik vec postoji u bazi!");
		}
		newUser.setId(userID);
		usersDatabase.add(userID++, newUser);
		saveUsers();
	}

	
	public synchronized void saveUsers() {
		saveDBMS();
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("Users.dat"));
			out.writeObject(usersDatabase);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void addCategory(CategoryBean newCategory) throws ElementExist {
		if (categoriesDatabase.contains(newCategory)) {
			throw new ElementExist("Kategorija vec postoji u bazi!");
		}
		newCategory.setId(categoryID);
		categoriesDatabase.add(categoryID++, newCategory);
		saveCategories();
	}

	
	public synchronized void saveCategories() {
		saveDBMS();
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("Categories.dat"));
			out.writeObject(categoriesDatabase);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void addComponent(ComponentBean newComponent) throws ElementExist {
		if (componentsDatabase.contains(newComponent)) {
			throw new ElementExist("Komponenta vec postoji u bazi!");
		}
		newComponent.setId(componentID);
		componentsDatabase.add(componentID++, newComponent);
		saveComponents();
	}

	
	public synchronized void saveComponents() {
		saveDBMS();
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("Components.dat"));
			out.writeObject(componentsDatabase);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void addDevice(DeviceBean newDevice) throws ElementExist {
		if (devicesDatabase.contains(newDevice)) {
			throw new ElementExist("Uredjaj vec postoji u bazi!");
		}
		newDevice.setId(deviceID);
		devicesDatabase.add(deviceID++, newDevice);
		saveDevices();
	}

	
	public synchronized void saveDevices() {
		saveDBMS();
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("Devices.dat"));
			out.writeObject(devicesDatabase);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public DeviceBean findDevice(String _name, int _id) {
		
		if (!_name.equals("pretraga_po_id")) {
			for (DeviceBean d : devicesDatabase) {
				if (d.getName().equals(_name)) {
					return d;
				}
			}
		} else {
			for (DeviceBean d : devicesDatabase) {
				if (d.getId() == _id) {
					return d;
				}
			}
		}
		
		return null;

	}
	
/*	
	public synchronized void saveFavourites() {
		saveDBMS();
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("Favourites.dat"));
			out.writeObject(favouritesDatabase);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

*/		
	
	/* CRUD sa filmovima // BCDEF zapravo */
/*	public void addMovie(Movie newMovie) throws ElementExist {
		if (moviesDatabase.containsKey(newMovie.getId()))
			throw new ElementExist("Ovaj film vec postoji u bazi!");
		moviesDatabase.put(newMovie.getId(), newMovie);
		saveMovies();
	}

	public synchronized void removeMovie(Movie removee) throws ElementDontExist {
		if (!moviesDatabase.containsKey(removee.getId()))
			throw new ElementDontExist("Trazeni film ne postoji u bazi!");
		moviesDatabase.remove(removee.getId());
		saveMovies();
	}
	
	public synchronized void removeMovie(Integer id) throws ElementDontExist {
		if (!moviesDatabase.containsKey(id))
			throw new ElementDontExist("Trazeni film ne postoji u bazi!");
		moviesDatabase.remove(id);
		saveMovies();
	}


	public Movie getMovie(Integer key) throws ElementDontExist {
		if (!moviesDatabase.containsKey(key))
			throw new ElementDontExist("Trazeni film ne postoji u bazi!");
		return moviesDatabase.get(key);
	}

	public synchronized void editMovie(Movie forChange) throws ElementDontExist {
		if (!moviesDatabase.containsKey(forChange.getId()))
			throw new ElementDontExist("Trazeni film ne postoji u bazi!");
		moviesDatabase.remove(forChange.getId());
		moviesDatabase.put(forChange.getId(), forChange);
		saveMovies();
	}

	public Iterator<Integer> getMovieIterator() {
		Set<Integer> oznakeUmetnickihDela = moviesDatabase.keySet();
		return oznakeUmetnickihDela.iterator();
	}

	
	
	
	public synchronized void commentOnMovie(Movie m, Comment c) throws ElementDontExist {
		if (!moviesDatabase.containsKey(m.getId()))
			throw new ElementDontExist("Trazeni film ne postoji u bazi!");
		if (!m.getComments().contains(c)) {
			m.getComments().add(c);
		}
		saveMovies();
	}
	
	public synchronized void rateMovie(Movie m, Integer userID, Integer rating) throws ElementDontExist {
		if (!moviesDatabase.containsKey(m.getId()))
			throw new ElementDontExist("Trazeni film ne postoji u bazi!");
		m.getRatings().put(userID, rating);
		saveMovies();
	}
	
	
	public synchronized void saveMovies() {
		saveDBMS();
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("Movies.dat"));
			out.writeObject(moviesDatabase);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
*/	

	
	
	/* CRUD sa serijama // BCDEF zapravo */
/*	public void addSeries(Series newSeries) throws ElementExist {
		if (seriesDatabase.containsKey(newSeries.getId()))
			throw new ElementExist("Ova serija vec postoji u bazi!");
		seriesDatabase.put(newSeries.getId(), newSeries);
		saveSeries();
	}

	public synchronized void removeSeries(Series removee) throws ElementDontExist {
		if (!seriesDatabase.containsKey(removee.getId()))
			throw new ElementDontExist("Trazena serija ne postoji u bazi!");
		seriesDatabase.remove(removee.getId());
		saveSeries();
	}
	
	public synchronized void removeSeries(Integer id) throws ElementDontExist {
		if (!seriesDatabase.containsKey(id))
			throw new ElementDontExist("Trazena serija ne postoji u bazi!");
		seriesDatabase.remove(id);
		saveSeries();
	}

	public Series getSeries(Integer key) throws ElementDontExist {
		if (!seriesDatabase.containsKey(key))
			throw new ElementDontExist("Trazena serija ne postoji u bazi!");
		return seriesDatabase.get(key);
	}

	public synchronized void editSeries(Series forChange) throws ElementDontExist {
		if (!seriesDatabase.containsKey(forChange.getId()))
			throw new ElementDontExist("Trazena serija ne postoji u bazi!");
		seriesDatabase.remove(forChange.getId());
		seriesDatabase.put(forChange.getId(), forChange);
		saveSeries();
	}

	public Iterator<Integer> getSeriesIterator() {
		Set<Integer> oznakeUmetnickihDela = seriesDatabase.keySet();
		return oznakeUmetnickihDela.iterator();
	}

	
	public synchronized void commentOnSeries(Series s, Comment c) throws ElementDontExist {
		if (!seriesDatabase.containsKey(s.getId()))
			throw new ElementDontExist("Trazena serija ne postoji u bazi!");
		if (!s.getComments().contains(c)) {
			s.getComments().add(c);
		}
		saveSeries();
	}
	
	public synchronized void rateSeries(Series s, Integer userID, Integer rating) throws ElementDontExist {
		if (!seriesDatabase.containsKey(s.getId()))
			throw new ElementDontExist("Trazena serija ne postoji u bazi!");
		s.getRatings().put(userID, rating);
		saveSeries();
	}
	
	public synchronized void saveSeries() {
		saveDBMS();
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("Series.dat"));
			out.writeObject(seriesDatabase);
			out.close();
		} catch (IOException e) {
			// e.printStackTrace();
		}
	}
	
	
	
	
	
*/	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/* CRUD sa sezonama // BCDEF zapravo */
/*	public void addSeason(Season newSeason) throws ElementExist {
		if (seasonsDatabase.containsKey(newSeason.getId()))
			throw new ElementExist("Ova sezona vec postoji u bazi!");
		seasonsDatabase.put(newSeason.getId(), newSeason);
		saveSeasons();
	}

	public synchronized void removeSeason(Season removee) throws ElementDontExist {
		if (!seasonsDatabase.containsKey(removee.getId()))
			throw new ElementDontExist("Trazena sezona ne postoji u bazi!");
		seasonsDatabase.remove(removee.getId());
		saveSeasons();
	}

	public Season getSeason(Integer key) throws ElementDontExist {
		if (!seasonsDatabase.containsKey(key))
			throw new ElementDontExist("Trazena sezona ne postoji u bazi!");
		return seasonsDatabase.get(key);
	}

	public synchronized void editSeason(Season forChange) throws ElementDontExist {
		if (!seasonsDatabase.containsKey(forChange.getId()))
			throw new ElementDontExist("Trazena sezona ne postoji u bazi!");
		seasonsDatabase.remove(forChange.getId());
		seasonsDatabase.put(forChange.getId(), forChange);
		saveSeasons();
	}

	public Iterator<Integer> getSeasonsIterator() {
		Set<Integer> temp = seasonsDatabase.keySet();
		return temp.iterator();
	}
	
	public synchronized void saveSeasons() {
		saveDBMS();
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("Seasons.dat"));
			out.writeObject(seasonsDatabase);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
*/	
	
	
	
	/* CRUD sa epizodama // BCDEF zapravo */
/*	public void addEpisode(Episode newEpisode) throws ElementExist {
		if (episodesDatabase.containsKey(newEpisode.getId()))
			throw new ElementExist("Ova epizoda vec postoji u bazi!");
		episodesDatabase.put(newEpisode.getId(), newEpisode);
		saveEpisodes();
	}

	public synchronized void removeEpisode(Episode removee) throws ElementDontExist {
		if (!episodesDatabase.containsKey(removee.getId()))
			throw new ElementDontExist("Trazena epizoda ne postoji u bazi!");
		episodesDatabase.remove(removee.getId());
		saveEpisodes();
	}
	
	public synchronized void removeEpisode(Integer id) throws ElementDontExist {
		if (!episodesDatabase.containsKey(id))
			throw new ElementDontExist("Trazena epizoda ne postoji u bazi!");
		episodesDatabase.remove(id);
		saveEpisodes();
	}

	public Episode getEpisode(Integer key) throws ElementDontExist {
		if (!episodesDatabase.containsKey(key))
			throw new ElementDontExist("Trazena epizoda ne postoji u bazi!");
		return episodesDatabase.get(key);
	}

	public synchronized void editMovie(Episode forChange) throws ElementDontExist {
		if (!episodesDatabase.containsKey(forChange.getId()))
			throw new ElementDontExist("Trazena epizoda ne postoji u bazi!");
		episodesDatabase.remove(forChange.getId());
		episodesDatabase.put(forChange.getId(), forChange);
		saveEpisodes();
	}

	public Iterator<Integer> getEpisodesIterator() {
		Set<Integer> temp = episodesDatabase.keySet();
		return temp.iterator();
	}

	
	public synchronized void commentOnEpisode(Episode e, Comment c) throws ElementDontExist {
		if (!episodesDatabase.containsKey(e.getId()))
			throw new ElementDontExist("Trazena epizoda ne postoji u bazi!");
		if (!e.getComments().contains(c)) {
			e.getComments().add(c);
		}
		saveEpisodes();
	}
	
	public synchronized void rateEpisode(Episode e, Integer userID, Integer rating) throws ElementDontExist {
		if (!episodesDatabase.containsKey(e.getId()))
			throw new ElementDontExist("Trazena epizoda ne postoji u bazi!");
		e.getRatings().put(userID, rating);
		saveEpisodes();
	}
	
	
	public synchronized void saveEpisodes() {
		saveDBMS();
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("Episodes.dat"));
			out.writeObject(episodesDatabase);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
*/	
		
	
	

	public void loadDBMS() {

		try {
			loadUsers();
			loadCategories();
			loadComponents();
			loadDevices();
			/*loadEpisodes();
			loadSeasons();
			loadSeries();
			loadMovies();*/
			
			File file = new File("DBMS.txt");
			if (file.exists()) {
				BufferedReader br = new BufferedReader(new FileReader("DBMS.txt"));
				String tmp = br.readLine();
				StringTokenizer st = new StringTokenizer(tmp);
				userID = Integer.parseInt(st.nextToken());
				categoryID = Integer.parseInt(st.nextToken());
				componentID = Integer.parseInt(st.nextToken());
				deviceID = Integer.parseInt(st.nextToken());
			/*	moviesID = Integer.parseInt(st.nextToken());
				seriesID = Integer.parseInt(st.nextToken());
				seasonsID = Integer.parseInt(st.nextToken());
				episodesID = Integer.parseInt(st.nextToken());*/
				br.close(); // ???
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void saveDBMS() {		
		
		String temp = userID.toString() + " " + 
					  categoryID.toString() + " " + 
					  componentID.toString() + " " + 
					  deviceID.toString(); /*+ " " + seriesID + " " + seasonsID + " " + episodesID*/

		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter("DBMS.txt"));
			bf.write(temp);
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

	/*private boolean stringContains(String original, String substring) {
		if (substring.equals(""))
			return true;
		else {
			return original.toLowerCase().contains(substring.toLowerCase());
		}
	}*/

	/*public List<Integer> findUsers(String _id, String _username, String _prezime) {
		List<Integer> retVal = new ArrayList<Integer>();

		if (!_id.equals("")) {
			try {
				Integer _iid = Integer.parseInt(_id);
				Autor a;
				try {
					a = getAutor(_iid);
					if ((stringContains(a.getIme(), _ime))
							&& (stringContains(a.getPrezime(), _prezime))) {
						retVal.add(a.getId());
						return retVal;
					} else {
						return retVal;
					}

				} catch (ElementDontExist e) { 
					e.printStackTrace();
					return retVal;
				}

			} catch (NumberFormatException e) {
				e.printStackTrace();
				return retVal;
			}
		} else {
			for (Autor a : skladisteAutora.values()) {
				if ((stringContains(a.getIme(), _ime))
						&& (stringContains(a.getPrezime(), _prezime))) {
					retVal.add(a.getId());
				}
			}
			return retVal;
		}

		// return retVal;

	}*/
/*
	public List<Integer> findAuthors(String _stil, String _tehnika) {
		List<Integer> retVal = new ArrayList<Integer>();

		for (UmetnickoDelo a : skladisteUmetnickihDela.values()) {
			if ((stringContains(a.getTehnika(), _tehnika))
					&& (stringContains(a.getStil(), _stil))) {
				if (!retVal.contains(a.getAutorID()))
					retVal.add(a.getAutorID());
			}
		}

		return retVal;
	}
*/
	
	
	// TODO
	// TODO
	// TODO

	// TODO
	// TODO
	// TODO
	
	/*
	 *  Pretraga filmova i serija po naslovu, godini, 
	 *  trajanju, jeziku, žanru, opisu, članovima postave,
	 *  ključnim rečima i komentarima. Omogućiti sortiranje
	 *   pronađenih filmova i serija po godini, naslovu, oceni i žanru. 
	 */
/*	public List<Integer> findMovies(String _ime, String _prezime) {
		List<Integer> retVal = new ArrayList<Integer>();

		for (Autor a : skladisteAutora.values()) {
			if ((stringContains(a.getIme(), _ime))
					&& (stringContains(a.getPrezime(), _prezime))) {
				if (!retVal.contains(a.getId()))
					retVal.add(a.getId());
			}
		} 

		return retVal;
	}

	public List<Integer> findDela(String _id, String _naslov, String _tehnika,
			String _stil) {
		List<Integer> retVal = new ArrayList<Integer>();

		if (!_id.equals("")) {
			try {
				Integer _iid = Integer.parseInt(_id);
				UmetnickoDelo a;
				try {
					a = getUmetnickoDelo(_iid);
					if ((stringContains(a.getNaslov(), _naslov))
							&& (stringContains(a.getStil(), _stil))
							&& (stringContains(a.getTehnika(), _tehnika))) {
						retVal.add(a.getId());
						return retVal;
					} else {
						return retVal;
					}

				} catch (ElementDontExist e) { // TODO Auto-generated catch
												// block
					e.printStackTrace();
					return retVal;
				}

			} catch (NumberFormatException e) {
				e.printStackTrace();
				return retVal;
			}
		} else {
			for (UmetnickoDelo a : skladisteUmetnickihDela.values()) {
				if ((stringContains(a.getNaslov(), _naslov))
						&& (stringContains(a.getStil(), _stil))
						&& (stringContains(a.getTehnika(), _tehnika))) {
					retVal.add(a.getId());
				}
			}
			return retVal;
		}

		// return retVal;

	}*/
	
	
	
	
/*
	public synchronized void podesiAutoreUDelima(Integer id) {
		for (UmetnickoDelo delo : skladisteUmetnickihDela.values()) {
			if (id.equals(delo.getAutorID()))
				skladisteUmetnickihDela.get(delo.getId()).setAutorID(-1);
		}
	}
*/
	
}
