package tech.jonathangonzalez.coronavirustracker.controllers;

import tech.jonathangonzalez.coronavirustracker.models.Global_LocationStats;
import tech.jonathangonzalez.coronavirustracker.models.LocationStats;
import tech.jonathangonzalez.coronavirustracker.services.CoronaVirusDataService;
import tech.jonathangonzalez.coronavirustracker.services.Global_CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String index(Model model) {
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "index";
    }

    @Autowired
    Global_CoronaVirusDataService Global_CoronaVirusDataService;

    @GetMapping("/global")
    public String global_VirusData(Model model) {
        List<Global_LocationStats> allStats = Global_CoronaVirusDataService.getAllGlobalStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("Global_locationStats", allStats);
        model.addAttribute("Global_totalReportedCases", totalReportedCases);
        model.addAttribute("Global_totalNewCases", totalNewCases);

        return "global";
    }
}