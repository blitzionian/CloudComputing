package edu.qaware.cc.reactive.actors;

import java.util.List;

import akka.actor.UntypedAbstractActor;
import edu.qaware.cc.reactive.connectors.wikipedia.WikipediaConnector;

public class WikipediaActor extends UntypedAbstractActor {

	private WikipediaConnector connector = new WikipediaConnector();

	@Override
	public void onReceive(Object message) throws Exception {
		// System.out.println("Entry: " + WikipediaActor.class.getName() + "; Param: " +
		// message);

		List<String> response = connector.getArticleTitlesFor((String) message);
		getSender().tell(response, self());
	}
}
