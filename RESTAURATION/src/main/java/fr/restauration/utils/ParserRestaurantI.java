package fr.restauration.utils;

import fr.restauration.model.Restaurant;

public interface ParserRestaurantI {

	public interface Command {
		void execute(Restaurant r);
	}

		public void setBody(String body);

		public void setCommand(Command cmd);

		public void parse() throws Exception;
	

}
