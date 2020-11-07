package io.DarinLina.virustracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.DarinLina.virustracker.LocationStats;
import io.DarinLina.virustracker.coronavirusDataService;

@Controller
public class PageController {

	@Autowired
	coronavirusDataService virusTracker;
	
	@GetMapping("/")
	public String homePage(Model model) {
		
		List<LocationStats> allStats = virusTracker.getAllStats();
		int totalReportedCasesGlobaly = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totaldailyCasesGlobaly = allStats.stream().mapToInt(stat -> stat.getDailyIncreasedCases()).sum();
		
		model.addAttribute("messageData", allStats);
		model.addAttribute("totalReportedCases", totalReportedCasesGlobaly);
		model.addAttribute("dailyReportedCases", totaldailyCasesGlobaly);
		
		return "home";
	}

}
