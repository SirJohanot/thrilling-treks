package com.patiun.thrillingtreks.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CampaignController {

    private final CampaignService campaignService;

    @Autowired
    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    public ModelAndView createCampaign(String name, String description) {
        Campaign newCampaign = campaignService.create(name, description, name);
        Long campaignId = newCampaign.getId();
        String redirectPath = String.format("redirect:/campaigns?id=%d", campaignId);
        return new ModelAndView(redirectPath);
    }

    @GetMapping("/campaigns")
    public String campaignsPage(final Model model) {
        List<Campaign> campaignList = campaignService.getAllCampaigns();
        model.addAttribute("campaigns", campaignList);
        return "campaigns";
    }
}
