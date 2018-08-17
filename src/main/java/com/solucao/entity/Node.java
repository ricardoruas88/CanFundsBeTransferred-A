package com.solucao.entity;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private List<Node> subNode = new ArrayList<Node>();
	private int value;
	private boolean pai;

	public Node(int node) {
		this.value = node;
	}

	public Node() {
		// TODO Auto-generated constructor stub
	}

	public Node(int value, boolean pai) {
		this.value = value;
		this.pai = pai;
	}

	public List<Node> getSubNode() {
		return subNode;
	}

	public void AddNode(Node node) {
		subNode.add(node);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isPai() {
		return pai;
	}
	
	

}
