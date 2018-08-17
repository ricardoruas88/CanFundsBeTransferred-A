package com.solucao.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.solucao.entity.Node;
import com.solucao.entity.Topology;

@Service
public class ConfigurationService {

	private Scanner s;

	public Topology carregarInformacoesDosBancos() {

		File fileName = new File("training.txt");
		if (fileName.exists()) {
			System.out.println("this file exists");
			try {
				s = new Scanner(new FileInputStream(fileName));

				Topology root = new Topology();

				String linha = s.nextLine();
				System.out.println(linha);

				root.setNumberOfBanks(Integer.parseInt(linha));
				Node node = new Node(1,true);

				while (s.hasNextLine()) {

					linha = s.nextLine();
					System.out.println(linha);
					String[] itens = linha.split(",");
					percorrerTopology(node, itens);
				}
				root.setNode(node);
				return root;
			} catch (IOException e) {
				// catch exception
			}
		}

		return null;
	}

	private void percorrerTopology(Node node, String[] itens) {
		int item = Integer.parseInt(itens[1]);
		int pai = Integer.parseInt(itens[0]);

		if (!node.getSubNode().isEmpty() && pai != node.getValue()) {
			Optional<Node> nodeFilho = node.getSubNode().stream().filter(x -> x.getValue() == pai).findFirst();

			if (nodeFilho.isPresent())
				nodeFilho.get().AddNode(new Node(item));
			else
				node.getSubNode().forEach(x -> {
					percorrerTopology(x, itens);
				});

		} else if (pai == node.getValue())
			node.AddNode(new Node(item));
	}
}
