package com.solucao.entity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Topology {

	private Node node;

	private int numberOfBanks = 0;

	public Topology(int numberOfBanks) {
		this.numberOfBanks = numberOfBanks;
	}

	public Topology() {

	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public int getNumberOfBanks() {
		return numberOfBanks;
	}

	public void setNumberOfBanks(int numberOfBanks) {
		this.numberOfBanks = numberOfBanks;
	}

	public boolean Distance(Map<Integer, StatusPosicao> sourcePathDestiny, Node node, List<Integer> filters,
			String lado) {

		boolean result = false;

		if (sourcePathDestiny.values().stream().anyMatch(x -> x.isStatus() == false)) {
			if (sourcePathDestiny.containsKey(node.getValue())) {
				filters.add(node.getValue());
				sourcePathDestiny.put(node.getValue(), new StatusPosicao(lado, true));

				if (!sourcePathDestiny.values().stream().anyMatch(x -> x.isStatus() == false))
					return true;
				result = true;
			}
			for (int i = 0; i < node.getSubNode().size(); i++) {

				result = Distance(sourcePathDestiny, node.getSubNode().get(i), filters,
						node.isPai() ? lado += lado + "_" + i : lado + "");

				if (!sourcePathDestiny.values().stream().anyMatch(x -> x.isStatus() == false))
					i = node.getSubNode().size();

				if (result) {
					if (!filters.contains(node.getValue()))
						filters.add(node.getValue());
					result = true;
				}
			}
		}
		if (!sourcePathDestiny.values().stream().anyMatch(x -> x.isStatus() == false)
				&& UsouRaiz(sourcePathDestiny, node.getValue()))
			filters.remove(new Integer(1));

		return result;
	}

	private boolean UsouRaiz(Map<Integer, StatusPosicao> sourcePathDestiny, int value) {
		if (value == 1 && !sourcePathDestiny.containsKey(1)) {
			List<String> posicao = sourcePathDestiny.values().stream().map(x -> x.getPosicao())
					.collect(Collectors.toList());
			return posicao.get(0).equals(posicao.get(1));
		}
		return false;
	}
}
