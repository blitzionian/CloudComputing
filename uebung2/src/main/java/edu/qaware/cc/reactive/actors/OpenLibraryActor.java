package edu.qaware.cc.reactive.actors;

import java.util.List;

import akka.actor.UntypedAbstractActor;
import edu.qaware.cc.reactive.connectors.openlibrary.OpenLibraryConnector;

public class OpenLibraryActor extends UntypedAbstractActor {

	private OpenLibraryConnector connector = new OpenLibraryConnector();

	@Override
	public void onReceive(Object message) throws Exception {
		// System.out.println("Entry: " + OpenLibraryActor.class.getName() + "; Param: "
		// + message);

		List<String> response = connector.getBooksWithTitleContaining((String) message);
		getSender().tell(response, self());
	}

}
