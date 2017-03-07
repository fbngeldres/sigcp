package org.jgrapht.graph;

public class TaggedEdge extends DefaultEdge {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8023187105116580091L;

	private String tag;

	public String getTag() {
		return tag;
	}

	public TaggedEdge(String tag) {
		super();
		this.tag = tag;
	}

	public TaggedEdge() {
		super();
	}

}
