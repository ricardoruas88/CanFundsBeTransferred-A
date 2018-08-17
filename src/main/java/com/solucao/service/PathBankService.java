package com.solucao.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.solucao.entity.RequestNodes;
import com.solucao.entity.ResponseNodes;
import com.solucao.entity.StatusPosicao;
import com.solucao.entity.Topology;

@Service
public class PathBankService {

	@SuppressWarnings("unused")
	private ConfigurationService configurationService;
	private Topology topology;

	@Autowired
	public PathBankService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
		this.topology = configurationService.carregarInformacoesDosBancos();
	}

	public Boolean Input(int source, int destiny, int maxiNumberOfNodes) throws Exception {

		List<Integer> bancos = new ArrayList<Integer>();

		Map<Integer, StatusPosicao> maps = new HashMap<>();
		maps.put(source, new StatusPosicao("", false));
		maps.put(destiny, new StatusPosicao("", false));

		topology.Distance(maps, topology.getNode(), bancos, "");

		return bancos.size() <= maxiNumberOfNodes;
	}

	@Cacheable
	public List<ResponseNodes> Input(RequestNodes nodes) {
		List<ResponseNodes> responseNodes = new ArrayList<ResponseNodes>();

		nodes.getNodes().forEach(x -> {
			if (!x.equals("END")) {
				String[] dados = x.split(",");
				try {

					responseNodes.add(new ResponseNodes(x,
							Input(Integer.parseInt(dados[0]), Integer.parseInt(dados[1]), Integer.parseInt(dados[2]))));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		return responseNodes;

	}
}
