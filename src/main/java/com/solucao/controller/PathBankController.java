package com.solucao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solucao.entity.RequestNodes;
import com.solucao.entity.ResponseNodes;
import com.solucao.service.PathBankService;

@RestController
@RequestMapping("/bank")
public class PathBankController {

	private final PathBankService service;

	@Autowired
	public PathBankController(PathBankService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Boolean> input(@RequestParam("a") int source, @RequestParam("b") int destiny,
			@RequestParam("q") int maxiNumberOfNodes) throws Exception {
		return new ResponseEntity<Boolean>(service.Input(source, destiny, maxiNumberOfNodes), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<List<ResponseNodes>> input(@RequestBody RequestNodes nodes) throws Exception {
		return new ResponseEntity<List<ResponseNodes>>(service.Input(nodes), HttpStatus.OK);
	}
}
