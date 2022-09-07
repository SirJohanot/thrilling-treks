package com.patiun.thrillingtreks.campaign;

import com.patiun.thrillingtreks.user.User;
import com.patiun.thrillingtreks.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final UserRepository userRepository;

    @Autowired
    public CampaignService(CampaignRepository campaignRepository, UserRepository userRepository) {
        this.campaignRepository = campaignRepository;
        this.userRepository = userRepository;
    }

    public Campaign create(String name, String description, String username) {
        User author = userRepository
                .findByName(username);
        return campaignRepository.save(new Campaign(name, description, author, null));
    }
}
