package com.cybage.sonar.report.pdf.builder;

import java.util.ArrayList;
import java.util.List;


import com.cybage.sonar.report.pdf.entity.QualityProfile;
import org.sonarqube.ws.Qualityprofiles;
import org.sonarqube.ws.client.WsClient;
import org.sonarqube.ws.client.qualityprofiles.SearchRequest;

public class QualityProfileBuilder {


    private static QualityProfileBuilder builder;

    private final WsClient wsClient;

    public QualityProfileBuilder(final WsClient wsClient) {
        this.wsClient = wsClient;
    }

    public static QualityProfileBuilder getInstance(final WsClient wsClient) {
        if (builder == null) {
            return new QualityProfileBuilder(wsClient);
        }

        return builder;
    }

    public List<QualityProfile> initProjectQualityProfilesByProjectKey(final String key) {
        SearchRequest searchWsReq = new SearchRequest();
        searchWsReq.setProject(key);
        Qualityprofiles.SearchWsResponse searchWsRes = wsClient.qualityprofiles().search(searchWsReq);

        List<QualityProfile> profiles = new ArrayList<>();

        for (Qualityprofiles.SearchWsResponse.QualityProfile profile : searchWsRes.getProfilesList()) {
            final QualityProfile qualityProfile = new QualityProfileEntityBuilder()
                    .setKey(profile.getKey())
                    .setName(profile.getName())
                    .setLanguage(profile.getLanguage())
                    .setLanguageName(profile.getLanguageName())
                    .setIsInherited(profile.getIsInherited())
                    .setIsDefault(profile.getIsDefault())
                    .setActiveRuleCount(profile.getActiveRuleCount())
                    .setRulesUpdatedAt(profile.getRulesUpdatedAt())
                    .setProjectCount(profile.getProjectCount())
                    .createQualityProfile();
            profiles.add(qualityProfile);
        }

        return profiles;

    }
}
