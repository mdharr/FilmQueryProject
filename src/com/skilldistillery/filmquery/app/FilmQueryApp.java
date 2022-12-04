package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
	

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

	@SuppressWarnings("unused")
	private void test() throws SQLException {
		Film film = db.findFilmById(1);
		System.out.println(film);
	}

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) throws SQLException {
		printTitleScreen();
		// present a menu in a loop with the following 3 options:

		String popcorn = "\uD83C\uDF7F";
		String welcomeStr = " The one stop shop for moviegoers!" + "\n";
		
		for(int i = 0; i < welcomeStr.length(); i++) {
			System.out.print(welcomeStr.charAt(i) + "");
			try {
				Thread.sleep(50);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
		try {
			Thread.sleep(500);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}		
		
		while (true) {
			String firstLineStr = "+=======================================+\n"; 
			                        
			for(int i = 0; i < firstLineStr.length(); i++) {

				System.out.print(firstLineStr.charAt(i) + "");
				try {
					Thread.sleep(10);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("|                  Menu                 |");
			System.out.println("+---------------------------------------+");
			try {
				Thread.sleep(50);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(50);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("| 1) Look up by film id                 |");
			System.out.println("+---------------------------------------+");
			try {
				Thread.sleep(50);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("| 2) Look up by search keyword          |");
			System.out.println("+---------------------------------------+");
			try {
				Thread.sleep(50);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("| 3) Exit the application               |");
			System.out.println("+=======================================+");
			System.out.println();
			
			try {
				Thread.sleep(500);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}	

			String pleaseChooseStr = "Please enter a number from the menu above: ";
			
			for(int i = 0; i < pleaseChooseStr.length(); i++) {
				System.out.print(pleaseChooseStr.charAt(i) + "");
				try {
					Thread.sleep(50);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				
				int userInput = input.nextInt();
				input.nextLine();
				
				if (userInput == 1) {
					System.out.println();
					String enterFilmIdStr = "Enter the film id you wish to reference: ";
					for(int i = 0; i < enterFilmIdStr.length(); i++) {
						System.out.print(enterFilmIdStr.charAt(i) + "");
						try {
							Thread.sleep(50);
						} catch(InterruptedException e) {
							e.printStackTrace();
						}
					}
					int userFilmId = input.nextInt();
					System.out.println();
					input.nextLine();
					Film film = db.findFilmById(userFilmId);
					printFilmParameters(film);
					continue;
					
				} else if (userInput == 2) {
					System.out.println();
					String enterKeywordStr = "Enter a keyword to search our catalogue: ";
					for(int i = 0; i < enterKeywordStr.length(); i++) {
						System.out.print(enterKeywordStr.charAt(i) + "");
						try {
							Thread.sleep(50);
						} catch(InterruptedException e) {
							e.printStackTrace();
						}
					}
					String userSearchTerm = input.nextLine();
					
					System.out.println();
					List<Film> films = db.findFilmsBySearchWord(userSearchTerm);
					printFilmListParameters(films);
					continue;
					
				} else {
					System.out.println();
					String exitStr = "Exiting application...";
					String byeStr = popcorn + "Goodbye" + popcorn;
					
					for(int i = 0; i < exitStr.length(); i++) {
						System.out.print(exitStr.charAt(i) + "");
						try {
							Thread.sleep(50);
						} catch(InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("\n");
					for(int i = 0; i < byeStr.length(); i++) {
						System.out.print(byeStr.charAt(i) + "");
						try {
							Thread.sleep(50);
						} catch(InterruptedException e) {
							e.printStackTrace();
						}
					}
					return;
				}
			} catch(InputMismatchException e) {
				System.out.println();
				System.out.println("Input mismatch, try again.");
				System.out.println();
				input.nextLine();
				continue;
			}
		}

	}
	
	public void printFilmParameters(Film film) throws SQLException {
		if(film != null) {
			System.out.println("Title: " + film.getTitle());
			try {
				Thread.sleep(50);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println();
			System.out.println("Year: " + film.getReleaseYear());
			try {
				Thread.sleep(50);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println();
			System.out.println("Rating: " + film.getRating());
			try {
				Thread.sleep(50);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println();
			System.out.println("Language: " + languageIdConverter(film.getLanguageId()));
			try {
				Thread.sleep(50);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println();
			System.out.println("Description: " + film.getDescription());
			try {
				Thread.sleep(50);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println();
			System.out.print("Starring: ");
			try {
				Thread.sleep(50);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			List<Actor> actors = db.findActorsByFilmId(film.getId());
			int counter = 1;

			for(Actor actor : actors) {
				String str = counter + ") " + actor.getFirstName() + " " + actor.getLastName() + ", ";
				if (str.endsWith(", ") && counter == actors.size()) {
					str = str.substring(0, str.length() - 2) + ".";
					System.out.print(str);
				} else {
				System.out.print(str);
				}
				counter++;
			}
			} else {
			String noFilmIdMatch = "No film was found matching that numeric id.";
			for(int i = 0; i < noFilmIdMatch.length(); i++) {
				System.out.print(noFilmIdMatch.charAt(i) + "");
				try {
					Thread.sleep(50);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println();
		try {
			Thread.sleep(1000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	public void printFilmListParameters(List<Film> films) throws SQLException {
		if(!(films.isEmpty())) {
			for(Film film : films) {
				System.out.println("Title: " + film.getTitle());
				try {
					Thread.sleep(5);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println();
				System.out.println("Year: " + film.getReleaseYear());
				try {
					Thread.sleep(5);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println();
				System.out.println("Rating: " + film.getRating());
				try {
					Thread.sleep(5);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println();
				System.out.println("Language: " + languageIdConverter(film.getLanguageId()));
				try {
					Thread.sleep(5);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println();
				System.out.println("Description: " + film.getDescription());
				try {
					Thread.sleep(5);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println();
				System.out.print("Starring: ");
				List<Actor> actors = db.findActorsByFilmId(film.getId());
				int counter = 1;

				for(Actor actor : actors) {
					String str = counter + ") " + actor.getFirstName() + " " + actor.getLastName() + ", ";
					if (str.endsWith(", ") && counter == actors.size()) {
						str = str.substring(0, str.length() - 2) + ".";
						System.out.print(str);
						System.out.println();
					} else {
					System.out.print(str);
					}
					counter++;
				}
				System.out.println();

			}
		} else {
			String noMatchingResults = "No matching results found.";
			
			for(int i = 0; i < noMatchingResults.length(); i++) {
				System.out.print(noMatchingResults.charAt(i) + "");
				try {
					Thread.sleep(50);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println();
		}
		try {
			Thread.sleep(500);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	public String languageIdConverter(int languageId) {
		String languageName = ""; 
		
		if(languageId == 1) {
			languageName = "English";
			
		} else if(languageId == 2) {
			languageName = "Italian";

		} else if(languageId == 3) {
			languageName = "Japanese";

		} else if(languageId == 4) {
			languageName = "Mandarin";

		} else if(languageId == 5) {
			languageName = "French";

		} else if(languageId == 6) {
			languageName = "German";

		}
		return languageName;
	}
	
	public void printTitleScreen() {

		String[] bigScreen = {
				"",
				"                                     |",
				"                          ___________I____________",
				"                         ( _____________________ ()",
				"                       _.-'|                    ||",
				"                   _.-'   ||       WELCOME      ||",
				"  ______       _.-'       ||                    ||",
				" |      |_ _.-'           ||       TO           ||",
				" |      |_|_              ||                    ||",
				" |______|   `-._          ||       MOVIE        ||",
				"    /\\          `-._      ||                    ||",
				"   /  \\             `-._  ||       NIGHT!       ||",
				"  /    \\                `-.I____________________||",
				" /      \\                 ------------------------",
				"/________\\___________________/________________\\______",
				""
		};
		
		for(String line: bigScreen) {
			System.out.println(line);
			try {
				Thread.sleep(50);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
