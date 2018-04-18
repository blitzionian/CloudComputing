package edu.qaware.cc.reactive.actors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;

public class MessageCollectorActor extends UntypedAbstractActor {

	private ActorRef wikipedia;
	private ActorRef openlibrary;

	private ActorRef caller;

	private boolean wikiFinished = false;
	private boolean openFinished = false;

	private List<String> result = new ArrayList<>();

	@Override
	public void preStart() throws Exception {
		wikipedia = getContext().actorOf(Props.create(WikipediaActor.class), "Wikipedia");
		openlibrary = getContext().actorOf(Props.create(OpenLibraryActor.class), "OpenLibrary");
	}

	@Override
	public void onReceive(Object message) throws Exception {
//		System.out.println("Entry: " + MessageCollectorActor.class.getName() + "; Param: " + message);

		if (message instanceof String) {
			caller = getSender();
			wikipedia.tell(message, self());
			openlibrary.tell(message, self());
		} else if (message instanceof List) {
			if (getSender() == wikipedia) {
				System.out.println("Sender was wiki: " + getSender().toString());
				wikiFinished = true;
				result.addAll((Collection<? extends String>) message);
			} else if (getSender() == openlibrary) {
				System.out.println("Sender was open: " + getSender().toString());
				openFinished = true;
				result.addAll((Collection<? extends String>) message);
			}

			if (wikiFinished && openFinished) {
				caller.tell(result, self());
			}
		}

		// futureWiki.
		// TODO
	}

}
