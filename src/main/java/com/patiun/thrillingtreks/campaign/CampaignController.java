package com.patiun.thrillingtreks.campaign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CampaignController {

    private final CampaignService campaignService;

    @Autowired
    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    public ModelAndView createCampaign(String name, String description) {
        campaignService.create(name, description, name);
        return new ModelAndView("redirect:/campaigns");
    }
}
